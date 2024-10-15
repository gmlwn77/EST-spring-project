package com.estsoft.springproject.blog.service;

import org.springframework.stereotype.Service;

import com.estsoft.springproject.blog.domain.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.repository.BlogRepository;

@Service
public class BlogService {
	BlogRepository repository;

	public BlogService(BlogRepository repository){
		this.repository = repository;
	}
	// blog 게시글 저장
	// repository.save(Article)

	public Article saveArticle(AddArticleRequest request){
		return repository.save(request.toEntity());
	}
}
