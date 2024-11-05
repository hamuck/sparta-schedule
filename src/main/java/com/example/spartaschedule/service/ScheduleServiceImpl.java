package com.example.spartaschedule.service;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;
import com.example.spartaschedule.repository.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;

public class ScheduleServiceImpl implements ScheduleService{
    private ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto.getPassword(), requestDto.getUserName(),requestDto.getTitle(),requestDto.getContents());
        return scheduleRepository.saveSchedule(schedule);
    }
}
