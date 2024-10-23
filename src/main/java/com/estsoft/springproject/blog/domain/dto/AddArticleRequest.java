package com.estsoft.springproject.blog.domain.dto;

import java.time.LocalDateTime;

import com.estsoft.springproject.blog.domain.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
	private String content;
	private String title;
	private LocalDateTime createdAt;

	public Article toEntity() {
		return Article.builder()
			.title(this.title)
			.content(this.content)
			.createdAt(this.createdAt)
			.build();
	}
}
