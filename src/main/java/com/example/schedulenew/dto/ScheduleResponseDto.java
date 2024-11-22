package com.example.schedulenew.dto;

import com.example.schedulenew.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String task;
    private String authorName;
    private LocalDateTime writeDate;
    private LocalDateTime modifyDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.task = schedule.getTask();
        this.authorName = schedule.getAuthorName();
        this.writeDate = schedule.getWriteDate();
        this.modifyDate = schedule.getModifyDate();
    }

    public ScheduleResponseDto(Long id, String task, String authorName, LocalDateTime writeDate, LocalDateTime modifyDate) {
        this.id = id;
        this.task = task;
        this.authorName = authorName;
        this.writeDate = writeDate;
        this.modifyDate = modifyDate;
    }
}
