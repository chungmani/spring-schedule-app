package com.example.springscheduleapp.schedule.dto;

import com.example.springscheduleapp.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record UpdateScheduleResponse (
        String title,
        String content,
        String username,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static UpdateScheduleResponse from(Schedule schedule) {
        return new UpdateScheduleResponse(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
