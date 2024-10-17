package com.estsoft.springproject.blog.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Content;
import com.estsoft.springproject.blog.service.BlogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ExternalApiController {

	private final BlogService blogService;

	public ExternalApiController(BlogService blogService) {
		this.blogService = blogService;
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
}
