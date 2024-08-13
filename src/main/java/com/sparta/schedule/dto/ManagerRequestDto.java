package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ManagerRequestDto {
    private long managerId;
    private String username;
    private String pw;
    private String email;
    private String createdate;  // 작성일
    private String updatedate;  // 수정일


}
