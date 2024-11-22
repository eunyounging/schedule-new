package com.example.schedulenew.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    // ID, 할 일, 작성자명, 비밀번호, 작성일, 수정일
    private Long id;
    private String task;
    private String authorName;
    private String password;
    private LocalDateTime writeDate;
    private LocalDateTime modifyDate;

    public Schedule(Long id, String task, String authorName, LocalDateTime writeDate, LocalDateTime modifyDate) {
        this.id = id;
        this.task = task;
        this.authorName = authorName;
        this.writeDate = writeDate;
        this.modifyDate = modifyDate;
    }
    public Schedule(String task, String authorName, String password) {
        this.task = task;
        this.authorName = authorName;
        this.password = password;
        this.writeDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
    }

}
