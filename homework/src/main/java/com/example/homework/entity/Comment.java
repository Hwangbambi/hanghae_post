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

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;


    public Comment(CommentRequestDto commentRequestDto, Article article, User user){
        this.content = commentRequestDto.getComment();
        this.article = article;
        this.user = user;
    }
    public void update(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getComment();
    }
}
