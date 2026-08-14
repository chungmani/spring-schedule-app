package com.example.springscheduleapp.comment.service;

import com.example.springscheduleapp.comment.dto.CreateCommentRequest;
import com.example.springscheduleapp.comment.dto.CreateCommentResponse;
import com.example.springscheduleapp.comment.entity.Comment;
import com.example.springscheduleapp.comment.repository.CommentRepository;
import com.example.springscheduleapp.schedule.entity.Schedule;
import com.example.springscheduleapp.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성 (1스케줄당 10개까지만 가능)
    public CreateCommentResponse create(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 스케줄입니다.")
        );
        int count = commentRepository.findByCommentCount(scheduleId);
        if (count >= 10) {
            throw new IllegalArgumentException("댓글은 10개까지만 생성가능합니다.");
        }
        Comment comment = new Comment(request.getContent(), request.getName(), request.getPassword(), schedule);
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(savedComment.getId(),
                savedComment.getContent(), savedComment.getName(),
                savedComment.getCreatedAt(), savedComment.getModifiedAt()
        );
    }
}
