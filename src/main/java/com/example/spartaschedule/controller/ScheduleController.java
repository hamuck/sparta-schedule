package com.example.spartaschedule.controller;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto){
        return new ResponseEntity<>(scheduleService.saveSchedule(dto),HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules(){
        return scheduleService.findAllSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleById(id),HttpStatus.OK);
    }

    @GetMapping("/date/{days}")
    public List<ScheduleResponseDto> findScheduleByDate(@PathVariable int days){
        return scheduleService.findScheduleByDate(days);
    }

    //비밀번호추가
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id, @RequestBody ScheduleRequestDto dto
    ){
        return new ResponseEntity<>(scheduleService.updateSchedule(id,dto.getPassword(), dto.getTitle(),dto.getContents()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        scheduleService.deleteSchedule(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
