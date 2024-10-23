package com.estsoft.springproject.blog.domain.dto;

import static com.estsoft.springproject.blog.DateFormatUtil.*;

import java.time.LocalDateTime;

import com.estsoft.springproject.blog.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentIdResponse {
	private Long comment_id;
	private Long article_id;
	private String body;
	private String created_at;

	public CommentIdResponse(Comment comment){
		comment_id = comment.getComment_id();
		article_id = comment.getArticle().getArticle_id();
		body = comment.getBody();
		created_at = comment.getCreated_at().format(formatter);
	}
}
