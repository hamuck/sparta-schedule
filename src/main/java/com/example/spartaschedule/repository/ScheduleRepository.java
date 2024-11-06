package com.example.spartaschedule.repository;

import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedules();
    Schedule findScheduleByIdOrElseThrow(Long id);
    List<ScheduleResponseDto> findScheduleByDate(int days);
    Optional<Schedule> findScheduleById(Long id);
    int updateSchedule(Long id, String title, String contents);
    int deleteSchedule(Long id);
}
