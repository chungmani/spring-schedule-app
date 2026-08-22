package com.example.springscheduleapp.auth.dto;

import com.example.springscheduleapp.user.entity.User;

import java.time.LocalDateTime;

public record CreateUserResponse(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
