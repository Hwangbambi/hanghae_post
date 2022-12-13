package com.example.homework.dto;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String comment;

    public CommentResponseDto(String comment) {
        this.comment = comment;
    }
}