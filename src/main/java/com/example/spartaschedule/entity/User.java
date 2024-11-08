package com.example.spartaschedule.entity;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class User {
    private Long id;
    private String name;
    private String mail;
    private Timestamp createAt;
    private Timestamp updateAt;

    public User(String name, String mail, Timestamp createAt){
        this.name = name;
        this.mail = mail;
        this.createAt = createAt;
        this.updateAt = createAt;
    }
}
