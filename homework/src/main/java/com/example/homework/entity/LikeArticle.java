package com.example.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class LikeArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public LikeArticle(Article article, User user) {
        this.article = article;
        this.user = user;
    }
}
