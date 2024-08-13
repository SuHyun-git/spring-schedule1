package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long scheduleId;
    private String content;
    private Long managerId;
    private String createdate;  // 작성일
    private String updatedate;  // 수정일

    private String username;    // 담당자 이름 저장

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.content = schedule.getContent();
        this.managerId = schedule.getManagerId();
        this.createdate = schedule.getCreatedate();
        this.updatedate = schedule.getUpdatedate();
    }

    public ScheduleResponseDto(Long scheduleId, Long managerId,String username,  String content, String createdate, String updatedate) {
        this.scheduleId = scheduleId;
        this.managerId = managerId;
        this.username = username;
        this.content = content;
        this.createdate = createdate;
        this.updatedate = updatedate;
    }

    public ScheduleResponseDto(Long scheduleId, String username, String content, String createdate, String updatedate) {
        this.scheduleId = scheduleId;
        this.username = username;
        this.content = content;
        this.createdate = createdate;
        this.updatedate = updatedate;
    }
}
