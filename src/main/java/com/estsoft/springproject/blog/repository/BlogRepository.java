package com.estsoft.springproject.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estsoft.springproject.blog.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
