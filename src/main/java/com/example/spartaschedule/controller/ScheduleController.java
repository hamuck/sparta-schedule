package com.example.spartaschedule.controller;

import com.example.spartaschedule.dto.ScheduleRequestDto;
import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id, @RequestBody ScheduleRequestDto dto
    ){
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getTitle(),dto.getContents()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}