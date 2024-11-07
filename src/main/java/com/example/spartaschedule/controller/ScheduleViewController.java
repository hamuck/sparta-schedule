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
    // 모든 스케줄 목록 조회 (HTML 페이지로 출력)
    @GetMapping("/view")
    public String viewSchedules() {
        List<ScheduleResponseDto> schedules = scheduleService.findAllSchedules();
        return "schedule";  // 'sparta.html' 템플릿 반환
    }



}
