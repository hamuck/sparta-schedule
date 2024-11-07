package com.example.spartaschedule.service;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> findAllSchedules();
    ScheduleResponseDto findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleByDate(int days);
    ScheduleResponseDto updateSchedule(Long id, String password, String title, String contents);
    void deleteSchedule(Long id, String password);
}
