package com.example.schedulenew.controller;

import com.example.schedulenew.dto.ScheduleRequestDto;
import com.example.schedulenew.dto.ScheduleResponseDto;
import com.example.schedulenew.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ScheduleController {

    private final ScheduleService scheduleService;
    private ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleRequestDto>> findAll(@RequestParam(value = "authorName", required = false) String authorName,
                                                            @RequestParam(value = "modifyDate", required = false) String modifyDate) {
        return new ResponseEntity<>(scheduleService.findAll(authorName, modifyDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleRequestDto> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>((scheduleService.findById(id), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ScheduleRequestDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               @RequestBody String password) {
        scheduleService.deleteSchedule(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id,
                                                              @RequestBody ScheduleRequestDto scheduleRequestDto){
        return new ResponseEntity<>(scheduleService.updateSchedule(id, scheduleRequestDto.getTask(), scheduleRequestDto.getAuthorName(), scheduleRequestDto.getPassword())
    }
}
