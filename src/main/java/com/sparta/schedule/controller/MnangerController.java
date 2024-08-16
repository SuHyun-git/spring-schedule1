package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ManagerRequestDto;
import com.sparta.schedule.dto.ManagerResponseDto;
import com.sparta.schedule.service.ManagerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MnangerController {

    private final ManagerService managerService;

    public MnangerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    // manager 생성
    @PostMapping("/manager")
    public ManagerResponseDto createManager(@RequestBody ManagerRequestDto managerRequestDto) {
        return managerService.createManager(managerRequestDto);
    }
}
