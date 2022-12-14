package com.example.homework.service;

import com.example.homework.config.ServiceConfig;
import com.example.homework.dto.*;
import com.example.homework.entity.Article;
import com.example.homework.entity.User;
import com.example.homework.jwt.JwtUtil;
import com.example.homework.repository.ArticleRepository;
import com.example.homework.repository.UserRepository;
import com.example.homework.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final ServiceConfig serviceConfig;

    @Transactional
    public ResponseDto saveArticle(ArticleRequestDto requestDto, User user) {
        Article article = articleRepository.saveAndFlush(new Article(requestDto , user));
            articleRepository.save(article);
            return new ResponseDto("글 등록 완료", HttpStatus.OK.value());
    }
    @Transactional(readOnly = true)
    public ArticleListResponseDto getArticles() {
        ArticleListResponseDto articleListResponseDto = new ArticleListResponseDto();

        List<Article> articles = articleRepository.findAllByOrderByCreatedAtDesc();

        for(Article article : articles){
            articleListResponseDto.addArticle(new ArticleResponseDto(article));
        }

        return articleListResponseDto;
    }
    @Transactional(readOnly = true)
    public ArticleResponseDto getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("글이 없거나 본인 글이 아닙니다")
        );
        return new ArticleResponseDto(article);
    }
    @Transactional
    public ArticleResponseDto updateArticle(Long id, ArticleResponseDto requestDto, UserDetailsImpl userDetails) {

        Article article = articleRepository.findByIdAndUser(id, userDetails.getUser()).orElseThrow(
                ()-> new RuntimeException("글이 없거나 본인 글이 아닙니다")
        );
        article.update(requestDto);
        return new ArticleResponseDto(article);
    }

    @Transactional
    public boolean deleteArticle(Long id , ArticleDeleteRequestDto requestDto , UserDetailsImpl userDetails){

        Article article = articleRepository.findByIdAndUser(id, userDetails.getUser()).orElseThrow(
                ()-> new RuntimeException("글이 없다")
        );
        articleRepository.delete(article);
        return true;
    }
}


