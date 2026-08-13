package com.example.springscheduleapp.controller;

import com.example.springscheduleapp.dto.CreateScheduleRequest;
import com.example.springscheduleapp.dto.CreateScheduleResponse;
import com.example.springscheduleapp.dto.GetScheduleResponse;
import com.example.springscheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(request));
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<GetScheduleResponse>> getAll(@RequestParam(required = false) String author) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll(author));
    }

    // 단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }
}
