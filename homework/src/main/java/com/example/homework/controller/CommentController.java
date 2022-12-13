package com.example.homework.controller;

import com.example.homework.dto.CommentRequestDto;
import com.example.homework.dto.CommentResponseDto;
import com.example.homework.dto.ResponseDto;
import com.example.homework.security.UserDetailsImpl;
import com.example.homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{postid}")
    public CommentResponseDto saveComment(@PathVariable Long postid, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.saveComment(postid, commentRequestDto, userDetails.getUser());
    }


    @PutMapping("/commentUpdate/{commentid}")
    public CommentResponseDto updateComment( @PathVariable Long commentid, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment( commentid, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/commentDelete/{commentid}")
    public ResponseDto deleteComment( @PathVariable Long commentid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment( commentid, userDetails.getUser());
    }
}