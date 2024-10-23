package com.estsoft.springproject.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.AddCommentRequest;
import com.estsoft.springproject.blog.domain.dto.ArticleCommentResponse;
import com.estsoft.springproject.blog.domain.dto.CommentIdResponse;
import com.estsoft.springproject.blog.domain.dto.CommentResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateCommentRequest;
import com.estsoft.springproject.blog.service.BlogService;
import com.estsoft.springproject.blog.service.CommentService;

@Controller
public class BlogCommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private BlogService blogService;

	@PostMapping("/api/articles/{articleId}/comments")
	public ResponseEntity<CommentResponse> wrtieComment(@PathVariable Long articleId,
																@RequestBody AddCommentRequest request) {
		Comment comment = commentService.saveComment(articleId, request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new CommentResponse(comment));
	}

	@GetMapping("/api/comments/{commentId}")
	public ResponseEntity<CommentResponse> findByID(@PathVariable Long commentId){
		Comment comment = commentService.findByID(commentId);
		return ResponseEntity.ok(new CommentResponse(comment));
	}

	@PutMapping("/api/comments/{commentId}")
	public ResponseEntity<CommentResponse> updateById(@PathVariable Long commentId,
																@RequestBody UpdateCommentRequest request){
		Comment comment = commentService.updateById(commentId, request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new CommentResponse(comment));
	}

	@DeleteMapping("/api/comments/{commentId}")
	public ResponseEntity<Void> deleteById(@PathVariable long commentId){
		commentService.deleteById(commentId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/api/articels/{articleId}/comments")
	public ResponseEntity<ArticleCommentResponse> findByArticleId(@PathVariable Long articleId){
		ArticleCommentResponse response
			= new ArticleCommentResponse(blogService.findBy(articleId), commentService.findByArticleId(articleId));
		return ResponseEntity.ok().body(response);
	}

}
