package com.example.springscheduleapp.schedule.service;

import com.example.springscheduleapp.comment.dto.CommentResponse;
import com.example.springscheduleapp.comment.repository.CommentRepository;
import com.example.springscheduleapp.comment.service.CommentService;
import com.example.springscheduleapp.schedule.dto.*;
import com.example.springscheduleapp.schedule.entity.Schedule;
import com.example.springscheduleapp.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentService commentService;

    @Transactional
    public CreateScheduleResponse create(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(), request.getContent(), request.getAuthor(), request.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(), savedSchedule.getTitle(),
                savedSchedule.getContent(), savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(), savedSchedule.getModifiedAt()
        );
    }

    // 전체 조회 메서드
    @Transactional(readOnly = true)
    public List<GetSchedulesResponse> getAll(String author) {
        List<Schedule> schedules = scheduleRepository.findAllByAuthor(author);
        return schedules.stream()
                .map(schedule -> new GetSchedulesResponse(
                        schedule.getId(), schedule.getTitle(), schedule.getContent(),
                        schedule.getAuthor(), schedule.getCreatedAt(), schedule.getModifiedAt()
                ))
                .toList();
    }

    // 단건 조회 + 댓글까지 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = getOrThrow(scheduleId);
        List<CommentResponse> comments = commentService.getAllComments(scheduleId);

        return new GetScheduleResponse(schedule.getId(), schedule.getTitle(),
                schedule.getContent(), schedule.getAuthor(),
                schedule.getCreatedAt(), schedule.getModifiedAt(), comments
        );
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = getOrThrow(scheduleId);
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        schedule.updateSchedule(request.getTitle(), request.getAuthor());
        return new UpdateScheduleResponse(schedule.getTitle(), schedule.getAuthor(), schedule.getModifiedAt());
    }

    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = getOrThrow(scheduleId);
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.delete(schedule);
    }


    // DB에서 id찾는 공통 처리 메서드
    private Schedule getOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 스케줄입니다.")
        );
    }


}
