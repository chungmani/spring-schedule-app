package com.example.springscheduleapp.user.dto;

import jakarta.validation.constraints.*;

public record CreateUserRequest(

        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotNull(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, message = "비밀번호는 8자리 이상이어야 합니다.")
        String password
) {
}
