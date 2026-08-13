package com.example.springscheduleapp.comment.service;

import com.example.springscheduleapp.comment.dto.CreateCommentRequest;
import com.example.springscheduleapp.comment.dto.CreateCommentResponse;
import com.example.springscheduleapp.comment.entity.Comment;
import com.example.springscheduleapp.comment.repository.CommentRepository;
import com.example.springscheduleapp.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CreateCommentResponse create(Long scheduleId, CreateCommentRequest request) {

    }
}
