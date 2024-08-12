package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String username;
    private String pw;
    private String createdate;  // 작성일
    private String updatedate;  // 수정일
    private String content;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUsername();
        this.pw = schedule.getPw();
        this.createdate = schedule.getCreatedate();
        this.updatedate = schedule.getUpdatedate();
        this.content = schedule.getContent();
    }

    public ScheduleResponseDto(Long id, String username, String content, String createdate, String updatedate) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.createdate = createdate;
        this.updatedate = updatedate;
    }
}
