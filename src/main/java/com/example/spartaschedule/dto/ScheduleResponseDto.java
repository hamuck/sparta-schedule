package com.example.spartaschedule.dto;

import com.example.spartaschedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private String password;
    private String userName;
    private String title;
    private String contents;

    public ScheduleResponseDto(Schedule schedule){
        this.password = schedule.getPassword();
    }
}
