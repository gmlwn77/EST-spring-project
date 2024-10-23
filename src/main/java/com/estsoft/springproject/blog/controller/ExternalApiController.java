package com.estsoft.springproject.blog.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.Content;
import com.estsoft.springproject.blog.domain.ContentCommnet;
import com.estsoft.springproject.blog.service.BlogService;
import com.estsoft.springproject.blog.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ExternalApiController {

	private final BlogService blogService;
	private final CommentService commentService;

	public ExternalApiController(BlogService blogService, CommentService commentService) {
		this.blogService = blogService;
		this.commentService = commentService;
	}

	@GetMapping("/api/external")
	public String callApi(){
		// 외부 API 호출
		RestTemplate restTemplate = new RestTemplate();

		String url = "https://jsonplaceholder.typicode.com/posts";

		// 외부 API 호출, 역직렬화
		ResponseEntity<List<Content>> resultList =
			restTemplate.exchange(url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Content>>() {});

		List<Content> contents = resultList.getBody();
		for(Content c : contents){
			blogService.saveExternal(c);
		}

		log.info("code: {}", resultList.getStatusCode());
		log.info("{}", resultList.getBody());

		return "ok";
	}

	@GetMapping("/api/external-comment")
	public String callApi2(){
		RestTemplate restTemplate = new RestTemplate();

		String url = "https://jsonplaceholder.typicode.com/comments";

		ResponseEntity<List<ContentCommnet>> resultList = restTemplate.exchange(url,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<ContentCommnet>>() {
			});

		List<ContentCommnet> contentCommnetList = resultList.getBody();
		for(ContentCommnet m : contentCommnetList){
			commentService.saveExternal(m);
		}
		return "ok";
	}
}
