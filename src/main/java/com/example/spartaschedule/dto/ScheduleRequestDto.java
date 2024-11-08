package com.example.spartaschedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {
    //요청받을때 사용하는 dto
    private Long userId;
    private String password;
    private String userName;
    private String title;
    private String contents;
}