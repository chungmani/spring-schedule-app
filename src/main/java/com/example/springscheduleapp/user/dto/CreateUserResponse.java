package com.example.springscheduleapp.user.dto;

import com.example.springscheduleapp.user.entity.User;

public record CreateUserResponse(
        Long id,
        String name,
        String email
) {
    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
