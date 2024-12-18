package com.estsoft.springproject.blog.domain.dto;

import static com.estsoft.springproject.blog.DateFormatUtil.*;

import java.time.LocalDateTime;
import java.util.List;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCommentResponse {
	private Long article_id;
	private String content;
	private String created_at;
	private String updated_at;
	private List<CommentIdResponse> commentList;

	public ArticleCommentResponse(Article article, List<CommentIdResponse> commentList){
		article_id = article.getArticle_id();
		content = article.getContent();
		created_at = article.getCreatedAt().format(formatter);
		updated_at = article.getUpdatedAt().format(formatter);
		this.commentList = commentList;
	}
}
