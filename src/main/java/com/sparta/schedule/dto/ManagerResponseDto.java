package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Manager;
import lombok.Getter;

@Getter
public class ManagerResponseDto {
    private long managerId;
    private String username;
    private String pw;
    private String email;

    public ManagerResponseDto(Manager manager) {
        this.username = manager.getUsername();
        this.pw = manager.getPw();
        this.email = manager.getEmail();
    }
}
