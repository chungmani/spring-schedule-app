package com.example.springscheduleapp.schedule.repository;

import com.example.springscheduleapp.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByUser_Id(Long userId);

    Optional<Schedule> findByIdAndUser_Id(Long scheduleId, Long userId);
}