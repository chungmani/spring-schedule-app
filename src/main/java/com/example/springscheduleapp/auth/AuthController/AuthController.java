package com.example.springscheduleapp.auth.AuthController;

import com.example.springscheduleapp.auth.dto.LoginRequest;
import com.example.springscheduleapp.auth.dto.LoginResponse;
import com.example.springscheduleapp.auth.dto.SessionUser;
import com.example.springscheduleapp.user.dto.CreateUserRequest;
import com.example.springscheduleapp.user.dto.CreateUserResponse;
import com.example.springscheduleapp.user.entity.User;
import com.example.springscheduleapp.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request, HttpSession session) {
        User user = userService.login(request);
        session.setAttribute("loginUser", SessionUser.from(user));
        return ResponseEntity.ok(LoginResponse.from(user));

    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            HttpSession session) {
        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
