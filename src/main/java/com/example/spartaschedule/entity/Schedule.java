package com.example.spartaschedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Schedule {

    private String password;
    private String userName;
    private String title;
    private String contents;

    public Schedule(String password, String userName, String title, String contents){
        this.password = password;
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }
}
