package com.example.springscheduleapp.schedule.controller;

import com.example.springscheduleapp.auth.dto.SessionUser;
import com.example.springscheduleapp.common.exception.UnauthorizedException;
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

    // 일정 생성
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @RequestBody CreateScheduleRequest request) {
        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.create(sessionUser.id(), request));
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<GetSchedulesResponse>> getAll(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser
    ) {
        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAll(sessionUser.id()));
    }

    // 단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOne(
            @PathVariable Long scheduleId,
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getOne(scheduleId, sessionUser.id()));
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> update(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request) {
        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(sessionUser.id(), scheduleId, request));
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId) {
        if (sessionUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        scheduleService.delete(scheduleId, sessionUser.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
