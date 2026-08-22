package com.example.springscheduleapp.user.controller;

import com.example.springscheduleapp.auth.dto.SessionUser;
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
    @PatchMapping
    public ResponseEntity<UpdateUserResponse> update(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @RequestBody UpdateUserRequest request) {
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userService.updateUser(sessionUser.id(), request));
    }

    // 유저 삭제
    @DeleteMapping
    public ResponseEntity<Void> delete(@SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        userService.deleteUser(sessionUser.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
