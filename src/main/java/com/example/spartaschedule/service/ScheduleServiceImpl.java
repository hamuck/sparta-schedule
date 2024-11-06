package com.example.spartaschedule.service;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;
import com.example.spartaschedule.repository.ScheduleRepository;
import com.example.spartaschedule.repository.ScheduleRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto.getPassword(), requestDto.getUserName(),requestDto.getTitle(),requestDto.getContents());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(){
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();
        return allSchedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id){
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByDate(int days){
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findScheduleByDate(days);
        return allSchedules;
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents){
        if (title == null || contents == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The title and contents are not required values.");
        }
        int updateRow = scheduleRepository.updateSchedule(id, title, contents);
        if (updateRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No date has been modified");
        }
        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }

    @Override
    public void deleteSchedule(Long id){
        int deleteRow = scheduleRepository.deleteSchedule(id);
        if (deleteRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No date has been modified");
        }
    }
}
