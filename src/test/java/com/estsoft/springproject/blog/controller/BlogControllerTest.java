package com.estsoft.springproject.blog.controller;

import java.util.List;

import static java.time.LocalDateTime.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class BlogControllerTest {
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private DefaultMockMvcBuilder mockMvcBuilder;

	@Autowired
	BlogRepository repository;
	@Autowired
	private BlogService blogService;

	@BeforeEach
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		repository.deleteAll();
	}

	//POST /articles API테스트
	@Test
	public void addArticle() throws Exception {
		// given: article 저장
		AddArticleRequest request = new AddArticleRequest("제목","내용", now());
		// 직렬화 (object -> json)
		String json = objectMapper.writeValueAsString(request);

		// when: POST /articles API 호출
		ResultActions resultActions = mockMvc.perform(post("/api")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json));

		// then: 호출 결과 검증
		resultActions.andExpect(status().isCreated())
			.andExpect(jsonPath("title").value(request.getTitle()))
			.andExpect(jsonPath("content").value(request.getContent()));

		List<Article> articleList = repository.findAll();
		Assertions.assertThat(articleList.size()).isEqualTo(2);
	}

	@Test
	public void findAll() throws Exception {
		// given: 조회 API 값 세팅
		Article article = repository.save(new Article("title","content", now()));

		// when: 조회 API
		ResultActions resultActions = mockMvc.perform(get("/api")
			.accept(MediaType.APPLICATION_JSON));

		// then: 호출 결과 검증
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].title").value(article.getTitle()))
			.andExpect(jsonPath("$[0].content").value(article.getContent()));
	}

	// 블로그 단건 조회 테스트
	@Test
	public void findBy() throws Exception {
		// given
		Article article = repository.save(new Article("title1", "content1", now()));
		Long id = article.getId();

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/{id}", id)
			.accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(id))
			.andExpect(jsonPath("$.title").value(article.getTitle()))
			.andExpect(jsonPath("$.content").value(article.getContent()));
	}

	@Test
	public void deleteBy() throws Exception {
		// given
		Article article = repository.save(new Article("title2", "content2", now()));
		Long id = article.getId();

		// when
		ResultActions resultActions = mockMvc.perform(delete("/api/{id}", id));

		// then
		resultActions.andExpect(status().isOk());
		List<Article> articleList = repository.findAll();
		Assertions.assertThat(articleList).isEmpty();
	}

	// 단건 조회 API - id에 해당하는 자원 없을 경우 4XX 예외처리 검증
	@Test
	public void findOneException() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/{id}", 1L)
			.accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isBadRequest());

		org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
			() -> blogService.findBy(1L));
	}

	// 업데이트 API 검증
	// PUT /articles/{id} body(json content)
	@Test
	public void updateArticle() throws Exception {
		Article article = repository.save(new Article("before title", "before content", now()));
		Long id = article.getId();

		// 수정 데이터(object) -> json
		UpdateArticleRequest request = new UpdateArticleRequest("after title", "after content");
		String updateJson = objectMapper.writeValueAsString(request);
		ResultActions resultActions = mockMvc.perform(put("/api/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)
			.content(updateJson));

		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value(request.getTitle()))
			.andExpect(jsonPath("$.content").value(request.getContent()));
	}

	// 수정 API 4XX예외처리 검증
	@Test
	public void updateException() throws Exception {
		Article article = repository.save(new Article("title","content", now()));
		UpdateArticleRequest request = new UpdateArticleRequest("after title", "after content");
		String updateJson = objectMapper.writeValueAsString(request);
		Long id = 100L;

		ResultActions resultActions = mockMvc.perform(put("/api/{id}", id)
			.contentType(MediaType.APPLICATION_JSON)
			.content(updateJson));
		resultActions.andExpect(status().isBadRequest());

		org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
			() -> blogService.update(id, request));
	}
}