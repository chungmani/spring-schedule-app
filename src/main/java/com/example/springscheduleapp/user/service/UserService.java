package com.example.springscheduleapp.user.service;

import com.example.springscheduleapp.auth.dto.CreateUserRequest;
import com.example.springscheduleapp.auth.dto.CreateUserResponse;
import com.example.springscheduleapp.auth.dto.LoginRequest;
import com.example.springscheduleapp.common.config.PasswordEncoder;
import com.example.springscheduleapp.common.exception.UnauthorizedException;
import com.example.springscheduleapp.common.exception.UserNotFoundException;
import com.example.springscheduleapp.user.dto.*;
import com.example.springscheduleapp.user.entity.User;
import com.example.springscheduleapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public CreateUserResponse create(CreateUserRequest request) {
        boolean existEmail = userRepository.existsByEmail(request.email());
        if (existEmail) {
            throw new IllegalStateException("중복된 이메일입니다.");
        }
        String passwordHashed = passwordEncoder.encode(request.password());
        User user = new User(request.name(), request.email(), passwordHashed);
        User savedUser = userRepository.save(user);
        return CreateUserResponse.from(savedUser);
    }

    // 일정 생성용 유저 조회 (api 응답 x)
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
    }

    // 유저 전체 조회
    public List<GetUserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(GetUserResponse::from)
                .toList();
    }

    // 유저 단건 조회 (이메일로 조회)
    public GetUserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        return GetUserResponse.from(user);
    }

    // 유저 수정
    @Transactional
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = getOrThrow(userId);
        user.updateUser(request.name());
        return UpdateUserResponse.from(user);
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long userId) {
        User user = getOrThrow(userId);
        userRepository.delete(user);
    }

    // 로그인
    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(
                () -> new UnauthorizedException("이메일 또는 비밀번호가 일치하지 않습니다.")
        );
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    // 공통메서드 분리
    private User getOrThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("해당 유저를 찾을 수 없습니다.")
        );
    }



}
