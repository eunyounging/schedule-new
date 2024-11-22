package com.example.schedulenew.service;

import com.example.schedulenew.dto.ScheduleRequestDto;
import com.example.schedulenew.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface ScheduleService {
    List<ScheduleResponseDto> findAll(String authorName, String modifyDate);

    ScheduleResponseDto findById(Long id);

    ScheduleRequestDto saveSchedule();

    void deleteSchedule(Long id, String password);

    HttpStatusCode updateSchedule(Long id, String task, String authorName, String password);
}
