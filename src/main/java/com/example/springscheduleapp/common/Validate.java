package com.example.springscheduleapp.common;

import org.springframework.stereotype.Component;

@Component
public class Validate {

    // 일정 관리 검증 메서드
    public void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("일정 제목을 입력해주세요");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("일정 제목은 최대 30자까지만 가능합니다.");
        }
    }

    public void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("일정 내용을 입력해주세요");
        }
        if (content.length() > 200) {
            throw new IllegalArgumentException("일정 내용은 최대 200자까지만 가능합니다.");
        }
    }

    public void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
    }

    public void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("작성자는 필수입니다.");
        }
    }

    // 댓글 검증 메서드
    public void validateCommentContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용을 입력해주세요");
        }
        if (content.length() > 100) {
            throw new IllegalArgumentException("내용은 100자를 넘을 수 없습니다.");
        }
    }

}
