package com.example.homework.service;

import com.example.homework.dto.CommentRequestDto;
import com.example.homework.dto.CommentResponseDto;
import com.example.homework.dto.ResponseDto;
import com.example.homework.entity.Article;
import com.example.homework.entity.Comment;
import com.example.homework.entity.User;
import com.example.homework.entity.UserRoleEnum;
import com.example.homework.repository.ArticleRepository;
import com.example.homework.repository.CommentLikeRepository;
import com.example.homework.repository.CommentRepository;
import com.example.homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public CommentResponseDto saveComment(Long postid, CommentRequestDto requestDto, User user){
        Article article = articleRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        Comment comment = commentRepository.saveAndFlush(new Comment(requestDto,article,user));
        int cnt = 0;
        return new CommentResponseDto(comment,cnt);
    }
    @Transactional
    public CommentResponseDto updateComment(Long commentid, CommentRequestDto requestDto, User user) {
        Comment comment;
        if(user.getRole()== UserRoleEnum.USER){
            comment = commentRepository.findByIdAndUser(commentid, user).orElseThrow(
                    () -> new IllegalArgumentException("해당 댓글이 없습니다."));
        }else{
            comment = commentRepository.findById(commentid).orElseThrow(
                    () -> new IllegalArgumentException("해당 댓글이 없습니다.")
            );
        }
        comment.update(requestDto);
        return new CommentResponseDto(comment, commentLikeRepository.countAllByComment_Id(comment.getId()));

    }

    @Transactional
    public ResponseDto deleteComment(Long commentid, User user) {
        if(user.getRole()== UserRoleEnum.USER){
                commentRepository.deleteByIdAndUser(commentid,user);
        }else{
            commentRepository.deleteById(commentid);
        }
        return new ResponseDto("댓글 삭제 성공", 200);
    }



}
