package com.example.spartaschedule.dto;

import com.example.spartaschedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ScheduleResponseDto {
    Long id;
    private String userName;
    private String title;
    private String contents;
    private Timestamp createAt;
    private Timestamp updateAt;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.userName = schedule.getUserName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }

    public ScheduleResponseDto(Long id,String userName, String title, String contents){
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }
    public ScheduleResponseDto(Long id, String userName, String title, String contents, Timestamp timestamp){
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
        this.createAt = timestamp;
        this.updateAt = timestamp;
    }
}
