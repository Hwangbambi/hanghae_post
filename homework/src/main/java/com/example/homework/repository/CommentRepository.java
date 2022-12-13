package com.example.homework.repository;

import com.example.homework.entity.Comment;
import com.example.homework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUser(Long commentid, User user);

    void deleteByIdAndUser(Long commentid, User user);
}
