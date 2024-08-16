package com.sparta.schedule.service;

import com.sparta.schedule.dto.ManagerRequestDto;
import com.sparta.schedule.dto.ManagerResponseDto;
import com.sparta.schedule.entity.Manager;
import com.sparta.schedule.repository.ManagerRepository;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public ManagerResponseDto createManager(ManagerRequestDto managerRequestDto) {
        Manager manager = new Manager(managerRequestDto);

        Manager savedManager = managerRepository.save(manager);

        ManagerResponseDto managerResponseDto = new ManagerResponseDto(manager);
        return managerResponseDto;
    }
}
