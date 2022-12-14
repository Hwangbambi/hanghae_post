package com.example.homework.repository;
import com.example.homework.entity.LikeArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<LikeArticle, Long> {
    boolean existsByUserIdAndArticleId(Long id, Long articleId);

    void deleteByUserIdAndArticleId(Long id, Long articleId);
}
