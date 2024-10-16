package com.estsoft.springproject.blog.controller;

import static java.util.Arrays.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BlogController {
	private final BlogService service;

	public BlogController(BlogService service) {
		this.service = service;
	}

	// RequestMapping (특정 url   POST /articles)
	// 입력
	@PostMapping("/articles")
	public ResponseEntity<ArticleResponse> writeArticle(@RequestBody AddArticleRequest request) {
		//System.out.println(request.getTitle());
		//System.out.println(request.getContent());
		//
		// // logging level
		// // trace, debug, info, warn, error
		//log.warn("{}, {}", request.getTitle(), request.getContent());

		//service.saveArticle(request);

		Article article = service.saveArticle(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(article.convert());
	}

	// RequestMapping
	// 조회
	@GetMapping("/articles")
	public ResponseEntity<List<ArticleResponse>> findAll() {
		// Article -> List<ArticlesResponse>
		List<ArticleResponse> articleResponses = service.findAll().stream()
			.map(Article::convert)
			.toList();
		return ResponseEntity.ok(articleResponses);
	}

	@GetMapping("/articles/{id}")
	public ResponseEntity<ArticleResponse> findById(@PathVariable Long id){
		Article article = service.findBy(id);
		// Article -> ArticleResponse
		return ResponseEntity.ok(article.convert());
	}

	// 게시물 삭제
	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteBy(id);
		return ResponseEntity.ok().build();
	}

	// article/{id} 수정 API body
	@PutMapping("/articles/{id}")
	public ResponseEntity<ArticleResponse> updateById(@PathVariable Long id,
														@RequestBody UpdateArticleRequest request){
		Article updateArticle = service.update(id, request);
		return ResponseEntity.ok(updateArticle.convert());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getMessage());
	}
}