package com.example.schedulenew.repository;

import com.example.schedulenew.dto.ScheduleRequestDto;
import com.example.schedulenew.dto.ScheduleResponseDto;
import com.example.schedulenew.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<ScheduleResponseDto> findAll(String authorName, String modifyDate);

    ScheduleRequestDto saveSchedule(Schedule schedule);

    int deleteSchedule(Long id, String password);

    int updateSchedule(Long id, String task, String authorName, String password);

}
