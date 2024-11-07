package com.example.spartaschedule.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class UserRequestDto {
    private Long id;
    private String name;
    private String maill;
    private Timestamp createAt;
    private Timestamp updateAt;
}
