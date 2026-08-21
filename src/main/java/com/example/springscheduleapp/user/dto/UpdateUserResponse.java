package com.example.springscheduleapp.user.dto;

import com.example.springscheduleapp.user.entity.User;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record UpdateUserResponse(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static UpdateUserResponse from(User user) {
        return new UpdateUserResponse(
                user.getId(),
                user.getName(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
