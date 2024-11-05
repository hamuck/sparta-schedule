package com.example.spartaschedule.repository;

import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
}
