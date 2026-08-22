package com.example.springscheduleapp.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateScheduleRequest (
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(max = 30, message = "일정 제목은 최대 30자까지만 입력 가능합니다.")
        String title,

        @NotBlank(message = "내용을 입력해주세요.")
        @Size(max = 200, message = "일정 내용은 최대 200자까지만 입력 가능합니다.")
        String content
) {}
