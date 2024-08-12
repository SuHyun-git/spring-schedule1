package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private String username;
    private String pw;
    private String content;

}
