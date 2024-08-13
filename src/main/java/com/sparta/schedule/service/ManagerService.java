package com.sparta.schedule.service;

import com.sparta.schedule.dto.ManagerRequestDto;
import com.sparta.schedule.dto.ManagerResponseDto;
import com.sparta.schedule.entity.Manager;
import com.sparta.schedule.repository.ManagerRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class ManagerService {
    private final JdbcTemplate jdbcTemplate;

    public ManagerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ManagerResponseDto createManager(ManagerRequestDto managerRequestDto) {
        Manager manager = new Manager(managerRequestDto);

        ManagerRepository managerRepository = new ManagerRepository(jdbcTemplate);
        Manager savedManager = managerRepository.save(manager);

        ManagerResponseDto managerResponseDto = new ManagerResponseDto(manager);
        return managerResponseDto;
    }
}
