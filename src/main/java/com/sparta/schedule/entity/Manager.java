package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ManagerRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Manager {
    private Long managerId;
    private String username;
    private String pw;
    private String email;
    private String createdate;  // 작성일
    private String updatedate;  // 수정일


    public Manager(ManagerRequestDto managerRequestDto) {
        this.managerId = managerRequestDto.getManagerId();
        this.username = managerRequestDto.getUsername();
        this.pw = managerRequestDto.getPw();
        this.email = managerRequestDto.getEmail();
        this.createdate = managerRequestDto.getCreatedate();
        this.updatedate = managerRequestDto.getUpdatedate();

    }
}
