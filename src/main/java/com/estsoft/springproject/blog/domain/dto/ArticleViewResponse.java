package com.estsoft.springproject.blog.domain.dto;

import java.time.LocalDateTime;

import com.estsoft.springproject.blog.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// view 구성을 위한 DTO
public class ArticleViewResponse {
	private Long article_id;
	private String title;
	private String content;
	private LocalDateTime createdAt;

	public ArticleViewResponse(Article article){
		this.article_id = article.getArticle_id();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.createdAt = article.getCreatedAt();
	}
}
