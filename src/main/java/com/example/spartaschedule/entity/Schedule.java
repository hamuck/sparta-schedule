package com.example.spartaschedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
public class Schedule {
    private Long id;
    private String password;
    private Long userId;
    private String userName;
    private String title;
    private String contents;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Schedule(String password,Long userId, String userName, String title, String contents){
        this.password = password;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }

    public Schedule(Long id, String userName, Long userId, String title, String contents, Timestamp createAt, Timestamp updateAt){
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
