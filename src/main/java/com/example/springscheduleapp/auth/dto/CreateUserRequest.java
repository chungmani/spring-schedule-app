package com.example.springscheduleapp.auth.dto;

import jakarta.validation.constraints.*;

public record CreateUserRequest(

        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()-+=]).{8,}$",
                message = "영문 대/소문자와 숫자, 특수문자를 1개 이상 포함한 8자 이상을 입력해주세요.")
        String password
) {
}
