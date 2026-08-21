package com.example.springscheduleapp.schedule.controller;

import com.example.springscheduleapp.common.Validate;
import com.example.springscheduleapp.schedule.dto.*;
import com.example.springscheduleapp.schedule.service.ScheduleService;
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
    private final Validate validate;

    // 일정 생성
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(Long userId, @RequestBody CreateScheduleRequest request) {
        validate.validateTitle(request.title());
        validate.validateContent(request.content());
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(userId, request));
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<GetSchedulesResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll());
    }

    // 단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId));
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> update(
            @PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request) {
        validate.validateTitle(request.title());
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(@PathVariable Long scheduleId) {
        scheduleService.delete(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
