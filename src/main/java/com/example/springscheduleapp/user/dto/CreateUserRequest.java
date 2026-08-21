package com.example.springscheduleapp.user.dto;

import com.example.springscheduleapp.user.entity.User;

public record CreateUserRequest(
        String name,
        String email,
        String password
) {
}
