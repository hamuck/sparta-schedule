package com.example.spartaschedule.service;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
}
