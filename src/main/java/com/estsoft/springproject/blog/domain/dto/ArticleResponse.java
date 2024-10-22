package com.estsoft.springproject.blog.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private Long id;

	@Schema(description = "블로그 제목", type = "String")
	private String title;

	@Schema(description = "블로그 내용", type = "String")
	private String content;

	@Schema(description = "블로그 작성일", type = "LocalDateTime")
	private LocalDateTime createdAt;
}
