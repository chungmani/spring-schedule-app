package com.example.springscheduleapp.schedule.dto;

import com.example.springscheduleapp.comment.dto.CommentResponse;
import com.example.springscheduleapp.schedule.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;


public record GetScheduleResponse (
        Long id,
        String title,
        String content,
        String username,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        List<CommentResponse> comments
) {
    public static GetScheduleResponse from(Schedule schedule, List<CommentResponse> comments) {
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                comments
        );
    }
}
