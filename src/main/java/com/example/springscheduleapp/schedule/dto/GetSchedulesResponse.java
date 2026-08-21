package com.example.springscheduleapp.schedule.dto;

import com.example.springscheduleapp.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record GetSchedulesResponse (
        Long id,
        String title,
        String content,
        String username,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static GetSchedulesResponse from(Schedule schedule) {
        return new GetSchedulesResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
