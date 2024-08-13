package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Manager;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(Long managerId, String pw, ScheduleRequestDto scheduleRequestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Manager manager = scheduleRepository.findManager(managerId, pw);

        if(manager != null) {
            Schedule schedule = new Schedule(scheduleRequestDto);
            schedule.setManagerId(managerId);

            // 데이터 저장
            Schedule saveSchedule = scheduleRepository.save(schedule);

            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("선택한 담당자는 존재하지 않습니다. Id나 pw를 다시 입력해주세요.");
        }
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findAll();
    }

    public ScheduleResponseDto getIdSchedules(Long scheduleId) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule schedule = scheduleRepository.findById(scheduleId);

        if(schedule != null) {
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public List<ScheduleResponseDto> getDateSchedules(String username, String updatedate) {
        updatedate = updatedate.substring(0, 8);

        ScheduleRepository scheduleRepository  = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findWithUserDate(username, updatedate);


    }

    public Long updateSchedule(Long scheduleId, Long managerId, String pw, ScheduleRequestDto scheduleRequestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Manager manager = scheduleRepository.findManager(managerId, pw);
        if(manager != null) {
            Schedule schedule = scheduleRepository.findById(scheduleId);
            if(schedule != null) {
                scheduleRepository.update(scheduleId, managerId, pw, scheduleRequestDto);
                return scheduleId;
            } else {
                throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 담당자는 존재하지 않습니다. Id나 pw를 다시 입력해주세요.");
        }
    }

    public long deleteSchedule(Long scheduleId, Long managerId, String pw) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Manager manager = scheduleRepository.findManager(managerId, pw);
        if(manager != null) {
            Schedule schedule = scheduleRepository.findById(scheduleId);
            if (schedule != null) {
                scheduleRepository.delete(scheduleId, managerId, pw);
                return scheduleId;
            }else {
                throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 담당자는 존재하지 않습니다. Id나 pw를 다시 입력해주세요.");
        }
    }
}
