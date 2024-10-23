package com.estsoft.springproject.blog.domain.dto;

import java.time.LocalDateTime;

import com.estsoft.springproject.blog.domain.Article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "블로그 결과 조회")
public class ArticleResponse {
	@Schema(description = "블로그ID", type = "Long")
	private Long article_id;

	@Schema(description = "블로그 제목", type = "String")
	private String title;

	@Schema(description = "블로그 내용", type = "String")
	private String content;

	@Schema(description = "블로그 작성일", type = "LocalDateTime")
	private LocalDateTime createdAt;

	private LocalDateTime updateAt;

	public ArticleResponse(Article article){
		article_id = article.getArticle_id();
		title = article.getTitle();
		content = article.getContent();
		createdAt = article.getCreatedAt();
		updateAt = article.getUpdatedAt();
	}

	public ArticleResponse(Long article_id, String title, String content, LocalDateTime createdAt){
		article_id = this.article_id;
		title = this.title;
		content = this.content;
		createdAt = this.createdAt;
	}
}
