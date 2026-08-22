package com.example.springscheduleapp.auth.dto;

import com.example.springscheduleapp.user.entity.User;

public record SessionUser(
        Long id,
        String email
) {
    public static SessionUser from(User user) {
        return new SessionUser(
                user.getId(),
                user.getEmail()
        );
    }
}
