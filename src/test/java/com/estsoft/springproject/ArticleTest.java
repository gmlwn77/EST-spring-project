package com.estsoft.springproject;

import org.junit.jupiter.api.Test;

import com.estsoft.springproject.blog.domain.Article;

public class ArticleTest {

	@Test
	public void test() {
		Article article = new Article("제목", "내용");

		Article articleBuilder = Article.builder()
			.title("")
			.content("")
			.build();
	}
}
