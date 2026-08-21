package com.example.springscheduleapp.user.controller;

import com.example.springscheduleapp.user.dto.*;
import com.example.springscheduleapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    // 유저 단건 조회 (이메일로 조회)
    @GetMapping(params = "email")
    public ResponseEntity<GetUserResponse> getUser(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // 유저 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> update(
            @PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
