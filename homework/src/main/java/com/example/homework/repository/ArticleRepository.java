package com.example.homework.repository;

import com.example.homework.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByIdAndPassword(Long id, String password);
    Optional<Article> findByIdAndUsername(Long id, String Username);
}

