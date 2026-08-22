package com.example.springscheduleapp.common.exception;

public class UnauthorizedException extends IllegalStateException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
