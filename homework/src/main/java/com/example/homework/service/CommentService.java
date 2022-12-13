package com.example.homework.service;

import com.example.homework.dto.CommentRequestDto;
import com.example.homework.dto.CommentResponseDto;
import com.example.homework.dto.ResponseDto;
import com.example.homework.entity.Article;
import com.example.homework.entity.Comment;
import com.example.homework.entity.User;
import com.example.homework.repository.ArticleRepository;
import com.example.homework.repository.CommentRepository;
import com.example.homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    public CommentResponseDto saveComment(Long postid, CommentRequestDto commentRequestDto,  User user) {
        Article article = articleRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다!")
        );
        //댓글 저장
        Comment comment = new Comment(commentRequestDto,  article, user.getUsername());
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getContent());
    }

    public CommentResponseDto updateComment(Long postid, Long commentid, CommentRequestDto commentRequestDto, User user) {
        Article article = articleRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다!")
        );
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다!")
        );
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment.getContent());
    }

    public ResponseDto deleteComment(Long postid, Long commentid, User user) {

        Article article = articleRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다!")
        );
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다!")
        );

        commentRepository.deleteById(commentid);

        return new ResponseDto("삭제 완료", HttpStatus.OK.value());
    }
}
