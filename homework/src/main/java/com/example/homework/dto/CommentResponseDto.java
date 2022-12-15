package com.example.homework.dto;
import com.example.homework.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String username;
    private String content;
    private int good;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.good = comment.getLikeComments().size();
    }

    public CommentResponseDto(Comment comment, int cnt) {
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.good = cnt;
    }

}