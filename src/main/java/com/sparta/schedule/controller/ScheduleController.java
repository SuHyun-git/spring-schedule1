package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService =scheduleService;
    }

    // schedule 생성
    @PostMapping("/schedule/{managerId}/{pw}")
    public ScheduleResponseDto createSchedule(@PathVariable Long managerId, @PathVariable String pw ,@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.createSchedule(managerId, pw, scheduleRequestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto getIdSchedules(@PathVariable Long scheduleId) {
        return scheduleService.getIdSchedules(scheduleId);
    }

    @GetMapping("/schedule/users/{username}/dates/{updatedate}")
    public List<ScheduleResponseDto> getDateSchedules(@PathVariable String username, @PathVariable String updatedate) {
        return scheduleService.getDateSchedules(username, updatedate);
    }

    @PutMapping("schedule/{scheduleId}/{managerId}/{pw}")
    public Long updateSchedule(@PathVariable Long scheduleId, @PathVariable Long managerId, @PathVariable String pw , @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.updateSchedule(scheduleId, managerId, pw, scheduleRequestDto);
    }

    @DeleteMapping("schedule/{scheduleId}/{managerId}/{pw}")
    public long deleteSchedule(@PathVariable Long scheduleId, @PathVariable Long managerId, @PathVariable String pw) {
        return scheduleService.deleteSchedule(scheduleId, managerId, pw);
    }
}
