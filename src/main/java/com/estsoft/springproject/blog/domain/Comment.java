package com.estsoft.springproject.blog.domain;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long comment_id;

	@Column
	private String body;

	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
	@CreatedDate
	private LocalDateTime created_at;

	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

	public Comment (String body, Article article){
		this.body = body;
		this.article = article;
	}

	public void update(String body){
		this.body = body;
	}

	public Comment(Long comment_id ,Long article_id, String body){
		this.comment_id = comment_id;
		this.article = new Article(article_id);
		this.body = body;
	}

}
