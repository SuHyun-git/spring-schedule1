package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ManagerRequestDto;
import com.sparta.schedule.dto.ManagerResponseDto;
import com.sparta.schedule.service.ManagerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MnangerController {

    private final JdbcTemplate jdbcTemplate;

    public MnangerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // manager 생성
    @PostMapping("/manager")
    public ManagerResponseDto createManager(@RequestBody ManagerRequestDto managerRequestDto) {
        ManagerService managerService = new ManagerService(jdbcTemplate);
        return managerService.createManager(managerRequestDto);
    }
}
