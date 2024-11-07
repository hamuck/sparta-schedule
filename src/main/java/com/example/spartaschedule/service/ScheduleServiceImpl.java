package com.example.spartaschedule.service;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;
import com.example.spartaschedule.repository.ScheduleRepository;
import com.example.spartaschedule.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto.getPassword(), requestDto.getUserId(),requestDto.getUserName(),requestDto.getTitle(),requestDto.getContents());
        if (
                schedule.getUserName() == null || schedule.getPassword() == null || schedule.getTitle() == null || schedule.getContents() == null
        ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The username, password, title and contents are not required values.");
        }
        Number userId = userRepository.saveUser(schedule);
        return scheduleRepository.saveSchedule(schedule, userId);
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
    public ScheduleResponseDto updateSchedule(Long id,String password, String title, String contents){
        if (title == null || contents == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The title and contents are not required values.");
        }
        int updateRow = scheduleRepository.updateSchedule(id, password, title, contents);
        if (updateRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No date has been modified");
        }
        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }

    @Override
    public void deleteSchedule(Long id, String password){
        int deleteRow = scheduleRepository.deleteSchedule(id, password);
        if (deleteRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No date has been modified");
        }
    }
}
