package com.example.spartaschedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    private String password;
    private String userName;
    private String title;
    private String contents;
}