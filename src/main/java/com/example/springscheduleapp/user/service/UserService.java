package com.example.springscheduleapp.user.service;

import com.example.springscheduleapp.user.dto.CreateUserRequest;
import com.example.springscheduleapp.user.dto.CreateUserResponse;
import com.example.springscheduleapp.user.dto.GetUserResponse;
import com.example.springscheduleapp.user.entity.User;
import com.example.springscheduleapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 유저 생성
    @Transactional
    public CreateUserResponse create(CreateUserRequest request) {
        User user = new User(request.name(), request.email(), request.password());
        User savedUser = userRepository.save(user);
        return CreateUserResponse.from(savedUser);
    }

    // 일정 생성용 유저 조회 (api 응답 x)
    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
    }
}
