package com.example.homework.dto;

import com.example.homework.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int good;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public ArticleResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.username = article.getUser().getUsername();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getModifiedAt();
        this.good = article.getLikeArticles().size();
        this.commentList = article.getCommentList().stream().map(CommentResponseDto::new).collect(Collectors.toList());

    }

}
