package com.estsoft.springproject.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Content {
	private Long id;
	private String title;
	private String body;

	@Override
	public String toString() {
		return "#title: " + title + "body: " + body + "\n";
	}

	public Article toA() {
		return Article.builder()
			.article_id(this.id)
			.title(this.title)
			.content(this.body)
			.build();
	}

}
