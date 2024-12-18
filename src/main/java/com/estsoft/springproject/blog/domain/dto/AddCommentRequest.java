package com.estsoft.springproject.blog.domain.dto;

import java.time.LocalDateTime;

import com.estsoft.springproject.blog.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCommentRequest {
	private String body;
}
