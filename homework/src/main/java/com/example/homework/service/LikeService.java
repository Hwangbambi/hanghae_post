package com.example.homework.service;

import com.example.homework.config.ServiceConfig;
import com.example.homework.dto.ResponseDto;
import com.example.homework.entity.*;
import com.example.homework.jwt.JwtUtil;
import com.example.homework.repository.ArticleLikeRepository;
import com.example.homework.repository.ArticleRepository;
import com.example.homework.repository.CommentLikeRepository;
import com.example.homework.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final ArticleLikeRepository articleLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final ServiceConfig serviceConfig;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public ResponseDto likeArticle(Long articleId, User user) {

        Article article = articleRepository.findById(articleId).orElseThrow(
                ()-> new NullPointerException("게시글이 존재하지 않습니다")
        );

        if (articleLikeRepository.existsByUserIdAndArticleId(user.getId(), articleId)) {
            articleLikeRepository.deleteByUserIdAndArticleId(user.getId(), articleId);

            return new ResponseDto("좋아요 취소 완료", HttpStatus.OK.value());
        }

        else {
            LikeArticle likeArticle = new LikeArticle(article, user);
            articleLikeRepository.saveAndFlush(likeArticle);
            return new ResponseDto("좋아요 완료", HttpStatus.OK.value());
        }
    }
    @Transactional
    public ResponseDto likeComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if (commentLikeRepository.existsByUserIdAndCommentId(user.getId(), commentId)) {
            commentLikeRepository.deleteByUserIdAndCommentId(user.getId(), commentId);

            return new ResponseDto("댓글 좋아요 취소 완료", HttpStatus.OK.value());
        }

        else {
            LikeComment likeComment = new LikeComment(comment, user);
            commentLikeRepository.saveAndFlush(likeComment);
            return new ResponseDto("댓글 좋아요 완료", HttpStatus.OK.value());
        }
    }

}
