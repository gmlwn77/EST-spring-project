package com.estsoft.springproject.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.Content;
import com.estsoft.springproject.blog.domain.ContentCommnet;
import com.estsoft.springproject.blog.domain.dto.AddCommentRequest;
import com.estsoft.springproject.blog.domain.dto.ArticleCommentResponse;
import com.estsoft.springproject.blog.domain.dto.CommentIdResponse;
import com.estsoft.springproject.blog.domain.dto.CommentResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateCommentRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final BlogRepository blogRepository;

	public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
		this.commentRepository = commentRepository;
		this.blogRepository = blogRepository;
	}

	// 댓글 저장
	public Comment saveComment(Long articleId, AddCommentRequest request){
		Article article = blogRepository.findById(articleId).orElseThrow();
		return commentRepository.save(new Comment(request.getBody(), article));
	}

	// 댓글 조회
	public Comment findByID(Long commentId){
		return commentRepository.findById(commentId).orElseThrow();
	}

	// 댓글 수정
	@Transactional
	public Comment updateById(Long commentId, UpdateCommentRequest request) {
		Comment comment = findByID(commentId);
		comment.update(request.getBody());
		return comment;
	}

	// 댓글 삭제
	public void deleteById(Long commentId){
		commentRepository.deleteById(commentId);
	}

	public List<Long> findByComment_id(Long articleId){
		List<Long> longList = new ArrayList<>();
		List<Comment> commentList = commentRepository.findAll();
		for(Comment m : commentList){
			if(articleId == m.getArticle().getArticle_id()){
				longList.add(m.getComment_id());
			}
		}
		return longList;
	}

	public List<CommentIdResponse> findByArticleId(Long articleId){
		List<CommentIdResponse> commentList = new ArrayList<>();
		List<Long> longList = findByComment_id(articleId);
		for(Long l : longList){
			commentList.add(new CommentIdResponse(commentRepository.getById(l)));
		}
		return commentList;
	}

	public Comment saveExternal(ContentCommnet contentCommnet){
		return commentRepository.save(new Comment(contentCommnet.getId(), contentCommnet.getPostId(), contentCommnet.getBody()));
	}
}
