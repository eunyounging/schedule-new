package com.example.schedulenew.service;

import com.example.schedulenew.dto.ScheduleRequestDto;
import com.example.schedulenew.dto.ScheduleResponseDto;
import com.example.schedulenew.entity.Schedule;
import com.example.schedulenew.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    @Override
    public List<ScheduleResponseDto> findAll(String authorName, String modifyDate) {
        return scheduleRepository.findAll(authorName, modifyDate);
    }

    @Override
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }


    @Override
    public ScheduleRequestDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto.getTask(), scheduleRequestDto.getAuthorName(), scheduleRequestDto.getPassword())
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        int deletedRow = scheduleRepository.deleteSchedule(id, password);
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + ": 아이디에 맞는 데이터가 없거나 비밀번호가 틀렸습니다.");
        }
    }

    @Override
    public HttpStatusCode updateSchedule(Long id, String task, String authorName, String password) {
        if(task == null || authorName == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "할일과 작업자명은 필수입니다.");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, task, authorName, password);
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + ": 아이디에 맞는 데이터가 없거나 비밀번호가 틀렸습니다.");

            Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

            return new ScheduleResponseDto(schedule);
        }

    }
}
