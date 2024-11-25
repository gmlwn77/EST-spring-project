package com.estsoft.springproject.blog.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest2 {
	@InjectMocks
	BlogController blogController;

	@Mock
	BlogService blogService;
	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
	}

	@Test
	public void testSaveArticle() throws Exception {
		// given
		String title = "mock_title";
		String content = "mock_content";

		AddArticleRequest request = new AddArticleRequest(title, content);
		ObjectMapper objectMapper = new ObjectMapper();
		String articleJson = objectMapper.writeValueAsString(request);

		Mockito.when(blogService.saveArticle(any()))
			.thenReturn(new Article(title, content));

		// when
		ResultActions resultActions = mockMvc.perform(post("/api/articles")
			.content(articleJson)
			.contentType(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isCreated())
			.andExpect(jsonPath("title").value(title))
			.andExpect(jsonPath("content").value(content));
	}

	@Test
	public void testDelete() throws Exception {
		// given
		Long id = 1L;

		// when
		ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", id));

		// then
		resultActions.andExpect(status().isOk());
		verify(blogService, times(1)).deleteBy(id);
	}

	@Test
	public void testFindOne() throws Exception {
		// given
		Long id = 1L;
		String title = "mock_title";
		String content = "mock_content";

		Mockito.doReturn(new Article(title, content))
			.when(blogService).findBy(id);

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/articles/{id}", 1L));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("title").value(title))
			.andExpect(jsonPath("content").value(content));
	}

}