package com.estsoft.springproject.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.springproject.blog.domain.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
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
	@PostMapping("/articles")
	public ResponseEntity<Article> writeArticle(@RequestBody AddArticleRequest request) {
		System.out.println(request.getTitle());
		System.out.println(request.getContent());
		//
		// // logging level
		// // trace, debug, info, warn, error
		log.warn("{}, {}", request.getTitle(), request.getContent());

		service.saveArticle(request);

		Article article = service.saveArticle(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(article);
	}
}
