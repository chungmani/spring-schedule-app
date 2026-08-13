package com.example.springscheduleapp.schedule.repository;

import com.example.springscheduleapp.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s WHERE s.author = :author OR :author IS NULL ORDER BY s.modifiedAt DESC")
    List<Schedule> findAllByAuthor(@Param("author") String author);
}