package com.example.homework.entity;

import com.example.homework.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Comment(CommentRequestDto commentRequestDto, Article article, String username){
        this.content = commentRequestDto.getComment();
        this.article = article;
        this.username = username;
    }
    public void update(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getComment();
    }
}
