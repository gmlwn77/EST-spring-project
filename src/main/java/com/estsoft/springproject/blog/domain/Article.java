package com.estsoft.springproject.blog.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.estsoft.springproject.blog.domain.dto.ArticleResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long article_id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@CreatedDate
	@Column
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column
	private LocalDateTime updatedAt;

	@Builder
	public Article(Long article_id,String title, String content, LocalDateTime createdAt){
		this.article_id = article_id;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}

	@Builder
	public Article(String title, String content){
		this.title = title;
		this.content = content;
	}

	// @Builder
	// public Article(Long article_id, String title, String content){
	// 	this.article_id = article_id;
	// 	this.title = title;
	// 	this.content = content;
	// }

	public ArticleResponse convert() {
		return new ArticleResponse(article_id, title, content, createdAt);
	}

	public void update(String title, String content){
		this.title = title;
		this.content = content;
	}

	public Article(Long article_id){
		this.article_id = article_id;
	}
}
