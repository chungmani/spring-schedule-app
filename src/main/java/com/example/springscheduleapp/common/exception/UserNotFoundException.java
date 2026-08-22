package com.example.springscheduleapp.common.exception;

public class UserNotFoundException extends IllegalStateException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
