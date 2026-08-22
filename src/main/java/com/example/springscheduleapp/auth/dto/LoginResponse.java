package com.example.springscheduleapp.auth.dto;

import com.example.springscheduleapp.user.entity.User;

public record LoginResponse(
        Long id,
        String email
) {
    public static LoginResponse from(User user) {
        return new LoginResponse(
                user.getId(),
                user.getEmail()
        );
    }
}
