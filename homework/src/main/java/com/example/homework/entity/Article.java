package com.example.homework.entity;


import com.example.homework.dto.ArticleRequestDto;
import com.example.homework.dto.ArticleResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    @OrderBy("id desc")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<LikeArticle> likeArticles = new ArrayList<>();

    public Article(ArticleRequestDto requestDto , User user){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
    }
    public void update(ArticleResponseDto responseDto){
        this.title = responseDto.getTitle();
        this.content = responseDto.getContent();

    }
}
