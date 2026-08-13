package com.example.springscheduleapp.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {

    private final String title;
    private final String author;
    private final LocalDateTime modifiedAt;

    public UpdateScheduleResponse(String title, String author, LocalDateTime modifiedAt) {
        this.title = title;
        this.author = author;
        this.modifiedAt = modifiedAt;
    }
}
