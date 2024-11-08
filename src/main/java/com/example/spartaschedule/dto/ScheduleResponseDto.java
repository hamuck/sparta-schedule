package com.example.spartaschedule.dto;

import com.example.spartaschedule.entity.Schedule;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ScheduleResponseDto {
    //정보를 보낼때 사용하는 dto
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String contents;
    private Timestamp createAt;
    private Timestamp updateAt;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.userName = schedule.getUserName();
        this.userId = schedule.getUserId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }

    public ScheduleResponseDto(Long id, String userName, String title, String contents, Timestamp timestamp){
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
        this.createAt = timestamp;
        this.updateAt = timestamp;  //스케쥴을 생성할때 쓰는 timestamp이므로 updateAt은 createAt과 같다
    }

    public ScheduleResponseDto(Long id, String userName, Long userId, String title, String contents, Timestamp timestamp){
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.createAt = timestamp;
        this.updateAt = timestamp; //스케쥴을 생성할때 쓰는 timestamp이므로 updateAt은 createAt과 같다
    }
}
