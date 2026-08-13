package com.example.springscheduleapp.service;

import com.example.springscheduleapp.dto.*;
import com.example.springscheduleapp.entity.Schedule;
import com.example.springscheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

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
    public List<GetScheduleResponse> getAll(String author) {
        List<Schedule> schedules = scheduleRepository.findAllByAuthor(author);
        return schedules.stream()
                .map(schedule -> new GetScheduleResponse(
                        schedule.getId(), schedule.getTitle(), schedule.getContent(),
                        schedule.getAuthor(), schedule.getCreatedAt(), schedule.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = getOrThrow(scheduleId);

        return new GetScheduleResponse(schedule.getId(), schedule.getTitle(),
                schedule.getContent(), schedule.getAuthor(),
                schedule.getCreatedAt(), schedule.getModifiedAt());
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
