package com.estsoft.springproject.blog.domain;

import org.hibernate.dialect.function.array.ArrayToStringFunction;

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

	public Article toEntity() {
		return Article.builder()
			.title(this.title)
			.content(this.content)
			.build();
	}
}
