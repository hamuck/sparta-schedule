package com.example.spartaschedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
public class Schedule {

    private Long id;
    private String password;
    private String userId;
    private String userName;
    private String title;
    private String contents;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Schedule(String password, String userName, String title, String contents){
        this.password = password;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }

    public Schedule(Long id, String userName, String title, String contents, Timestamp createAt, Timestamp updateAt){
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
