package com.example.springscheduleapp.repository;

import com.example.springscheduleapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
