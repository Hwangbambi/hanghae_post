package com.example.homework.repository;


import com.example.homework.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<LikeComment, Long> {
    boolean existsByUserIdAndCommentId(Long id, Long commentId);

    void deleteByUserIdAndCommentId(Long id, Long commentId);

    int countAllByComment_Id(Long commentid);
}
