package com.example.springscheduleapp.schedule.service;

import com.example.springscheduleapp.comment.dto.CommentResponse;
import com.example.springscheduleapp.comment.service.CommentService;
import com.example.springscheduleapp.common.exception.ScheduleNotFoundException;
import com.example.springscheduleapp.schedule.dto.*;
import com.example.springscheduleapp.schedule.entity.Schedule;
import com.example.springscheduleapp.schedule.repository.ScheduleRepository;
import com.example.springscheduleapp.user.entity.User;
import com.example.springscheduleapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentService commentService;
    private final UserService userService;

    // 일정 생성
    @Transactional
    public CreateScheduleResponse create(Long userId, CreateScheduleRequest request) {
        User user = userService.findById(userId);
        Schedule schedule = new Schedule(request.title(), request.content(), user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return CreateScheduleResponse.from(savedSchedule);
    }

    // 전체 조회 메서드
    @Transactional(readOnly = true)
    public List<GetSchedulesResponse> getAll(Long userId) {
        List<Schedule> schedules = scheduleRepository.findAllByUser_Id(userId);
        return schedules.stream()
                .map(GetSchedulesResponse::from)
                .toList();
    }

    // 단건 조회 + 댓글까지 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId, Long userId) {
        Schedule schedule = getOrThrow(scheduleId, userId);
        List<CommentResponse> comments = commentService.getAllComments(scheduleId);
        return GetScheduleResponse.from(schedule, comments);
    }

    // 일정 수정
    @Transactional
    public UpdateScheduleResponse update(Long userId, Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = getOrThrow(scheduleId, userId);
        schedule.updateSchedule(request.title(), request.content());
        return UpdateScheduleResponse.from(schedule);
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId, Long userId) {
        Schedule schedule = getOrThrow(scheduleId, userId);
        scheduleRepository.delete(schedule);
    }


    // DB에서 id찾는 공통 처리 메서드
    private Schedule getOrThrow(Long scheduleId, Long userId) {
        return scheduleRepository.findByIdAndUser_Id(scheduleId, userId)
                .orElseThrow(() -> new ScheduleNotFoundException("일정을 찾을 수 없습니다."));
    }
}
