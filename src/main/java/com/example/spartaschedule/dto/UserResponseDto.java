package com.example.spartaschedule.dto;

import lombok.Getter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
public class UserResponseDto {
    //user 정보를 전달할때 사용하는 dto
    private Long id;
    private String name;
    private String mail;
    private Timestamp createAt;
    private Timestamp updateAt;

    public UserResponseDto(Long id, String name, String mail, Timestamp time){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.createAt = time;
        this.updateAt = time;
    }
}
