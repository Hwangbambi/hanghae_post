package com.example.homework.controller;

import com.example.homework.dto.ResponseDto;
import com.example.homework.security.UserDetailsImpl;
import com.example.homework.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    @PostMapping(value = "/postGood/{articleId}")
    public ResponseDto likeArticle(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likeArticle(articleId,userDetails.getUser());
    }

    @PostMapping(value = "/commentGood/{commentId}")
    public ResponseDto likeComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails ){
        return likeService.likeComment(commentId, userDetails.getUser());
    }
}
