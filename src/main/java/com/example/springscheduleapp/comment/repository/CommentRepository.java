package com.example.springscheduleapp.comment.repository;

import com.example.springscheduleapp.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
