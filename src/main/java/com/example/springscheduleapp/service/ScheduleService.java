package com.example.springscheduleapp.service;

import com.example.springscheduleapp.dto.CreateScheduleRequest;
import com.example.springscheduleapp.dto.CreateScheduleResponse;
import com.example.springscheduleapp.dto.GetScheduleResponse;
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
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 스케줄입니다.")
        );

        return new GetScheduleResponse(schedule.getId(), schedule.getTitle(),
                schedule.getContent(), schedule.getAuthor(),
                schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
