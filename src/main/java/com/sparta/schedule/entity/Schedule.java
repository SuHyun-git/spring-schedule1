package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private Long id;            // id
    private String username;    // 담당자 명
    private String pw;          // 비밀번호
    private String createdate;  // 작성일
    private String updatedate;  // 수정일
    private String content;     // 할일(내용)

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.id = scheduleRequestDto.getId();
        this.username = scheduleRequestDto.getUsername();
        this.pw = scheduleRequestDto.getPw();
        this.content = scheduleRequestDto.getContent();
    }
}
