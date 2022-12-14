package com.example.homework.controller;

import com.example.homework.dto.ResponseDto;
import com.example.homework.security.UserDetailsImpl;
import com.example.homework.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    @RequestMapping(value = "/postGood/{articleId}", method = RequestMethod.POST)
    public ResponseDto likeArticle(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likeArticle(articleId,userDetails.getUser());
    }

    @RequestMapping(value = "/commentGood/{commentId}", method = RequestMethod.POST)
    public ResponseDto likeComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails ){
        return likeService.likeComment(commentId, userDetails.getUser());
    }
}
