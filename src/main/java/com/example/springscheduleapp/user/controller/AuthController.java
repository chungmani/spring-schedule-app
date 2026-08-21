package com.example.springscheduleapp.user.controller;

import com.example.springscheduleapp.user.dto.CreateUserRequest;
import com.example.springscheduleapp.user.dto.CreateUserResponse;
import com.example.springscheduleapp.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    // 회원가입 (유저생성)
    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> signUp(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }
}
