package com.example.springscheduleapp.common.exception;

public class ScheduleNotFoundException extends IllegalStateException{
    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
