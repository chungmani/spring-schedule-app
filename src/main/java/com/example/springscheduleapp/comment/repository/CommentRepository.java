package com.example.springscheduleapp.comment.repository;

import com.example.springscheduleapp.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT COUNT(c.schedule) FROM Comment c WHERE c.schedule.id = :scheduleId")
    Integer findByCommentCount(@Param("scheduleId") Long scheduleId);
}
