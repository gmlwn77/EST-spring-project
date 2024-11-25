package com.estsoft.springproject.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.Content;
import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.AddCommentRequest;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;

import jakarta.transaction.Transactional;

@Service
public class BlogService {
	BlogRepository repository;
	Article article;
	Comment comment;

	public BlogService(BlogRepository repository){
		this.repository = repository;
	}
	// blog 게시글 저장
	// repository.save(Article)

	@Transactional
	public Article saveArticle(AddArticleRequest request){

		return repository.save(request.toEntity());
	}

	public Article saveExternal(Content content){
		return repository.save(content.toA());
	}

	// blog 게시글 전체 조회
	public List<Article> findAll() {
		return repository.findAll();
	}

	// blog 게시글 단건 조회 id 기준
	public Article findBy(Long id){
		//Optional - findById
		//return repository.findById(id).orElse(new Article());
		//return repository.findById(id).orElseGet(Article::new);
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found id: "+ id));
	}

	// 블로그 게시물 삭제 (id)
	public void deleteBy(Long id){
		repository.deleteById(id);
	}

	@Transactional
	public Article update(Long id, UpdateArticleRequest request){
		Article article = findBy(id); // 수정하고 싶은 row 가져오기
		article.update(request.getTitle(), request.getContent());
		return article;
	}

}
