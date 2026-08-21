package com.example.springscheduleapp.user.dto;

import com.example.springscheduleapp.user.entity.User;

public record GetUserResponse(
        Long id,
        String name,
        String email
) {
    public static GetUserResponse from(User user) {
        return new GetUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
