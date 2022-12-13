package com.example.homework.dto;
import com.example.homework.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String username;
    private String comment;


    public CommentResponseDto(Comment comment) {
        this.id= comment.getId();
        this.username = comment.getUser().getUsername();
        this.comment = comment.getContent();
    }
}