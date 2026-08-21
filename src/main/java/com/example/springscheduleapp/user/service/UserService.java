package com.example.springscheduleapp.user.service;

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

    // 회원가입
    @Transactional
    public CreateUserResponse create(CreateUserRequest request) {
        boolean existEmail = userRepository.existsByEmail(request.email());
        if (existEmail) {
            throw new IllegalStateException("중복된 이메일입니다.");
        }
        User user = new User(request.name(), request.email(), request.password());
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

    // 공통메서드 분리
    private User getOrThrow(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
    }


}
