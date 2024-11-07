package com.example.spartaschedule.repository;

import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule, Number userId);
    List<ScheduleResponseDto> findAllSchedules();
    Schedule findScheduleByUserIdOrElseThrow(Long id);
    List<ScheduleResponseDto> findScheduleByDate(int days);
    Optional<Schedule> findScheduleById(Long id);
    int updateSchedule(Long id,String password, String title, String contents);
    int deleteSchedule(Long id, String password);
}
