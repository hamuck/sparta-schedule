package com.example.spartaschedule.controller;

import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/schedules")
public class ScheduleViewController {
    private final ScheduleService scheduleService;

    public ScheduleViewController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }
    //스케쥴을 입력하는 schedule.html로 연결
    @GetMapping("/view")
    public String viewSchedules() {
        List<ScheduleResponseDto> schedules = scheduleService.findAllSchedules();
        return "schedule";
    }



}
