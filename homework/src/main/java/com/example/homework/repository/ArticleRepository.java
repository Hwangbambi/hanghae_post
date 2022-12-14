package com.example.homework.repository;

import com.example.homework.entity.Article;
import com.example.homework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    //boolean existsByIdAndPassword(Long id, String password);
    Optional<Article> findByIdAndUser(Long id, User user);
    List<Article> findAllByOrderByCreatedAtDesc();
}

