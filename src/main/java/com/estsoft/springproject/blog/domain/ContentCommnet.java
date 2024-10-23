package com.estsoft.springproject.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ContentCommnet {
	private Long id;
	private Long postId;
	private String body;
}
