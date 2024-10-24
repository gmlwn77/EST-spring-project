package com.estsoft.springproject.blog.domain.dto;

import static com.estsoft.springproject.blog.DateFormatUtil.*;

import java.time.LocalDateTime;

import com.estsoft.springproject.blog.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class CommentResponse {
	private Long comment_id;
	private String body;
	private String created_at;
	private ArticleResponse article;

	public CommentResponse(Comment comment){
		comment_id = comment.getComment_id();
		body = comment.getBody();
		created_at = comment.getCreated_at().format(formatter);
		article = new ArticleResponse(comment.getArticle());
	}

}