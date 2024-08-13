package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Schedule {
    private Long scheduleId;    // id
    private String createdate;  // 작성일
    private String updatedate;  // 수정일
    private String content;     // 할일(내용)
    private Long managerId;

    public Schedule(ScheduleRequestDto scheduleRequestDto) {
        this.scheduleId = scheduleRequestDto.getScheduleId();
        this.content = scheduleRequestDto.getContent();
        this.managerId = scheduleRequestDto.getManagerId();
    }
}
