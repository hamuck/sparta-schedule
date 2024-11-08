package com.example.spartaschedule.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UserRequestDto {
    //user를 요청받을 때 사용하는 dto
    private Long id;
    private String name;
    private String maill;
    private Timestamp createAt;
    private Timestamp updateAt;
}
