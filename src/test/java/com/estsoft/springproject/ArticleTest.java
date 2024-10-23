package com.estsoft.springproject;

import static java.time.LocalDateTime.*;

import org.junit.jupiter.api.Test;

import com.estsoft.springproject.blog.domain.Article;

public class ArticleTest {

	@Test
	public void test() {
		Article article = new Article("제목", "내용", now());

		Article articleBuilder = Article.builder()
			.title("")
			.content("")
			.build();
	}
}
