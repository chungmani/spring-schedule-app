package com.example.springscheduleapp.schedule.dto;

import com.example.springscheduleapp.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record GetSchedulesResponse (
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static GetSchedulesResponse from(Schedule schedule) {
        return new GetSchedulesResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );

    }
}
