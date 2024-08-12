package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (username, pw, content) VALUES (?, ?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, schedule.getUsername());
            preparedStatement.setString(2, schedule.getPw());
            preparedStatement.setString(3, schedule.getContent());
            return preparedStatement;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getAllSchedules() {
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String content = rs.getString("content");
                String createdate = rs.getString("createdate");
                String updatedate = rs.getString("updatedate");
                return new ScheduleResponseDto(id, username, content, createdate,updatedate);
            }
        });
    }

    @GetMapping("/schedule/{id}")
    public ScheduleResponseDto getIdSchedules(@PathVariable Long id) {
        Schedule schedule = findById(id);

        if(schedule != null) {
            return new ScheduleResponseDto(schedule);
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @GetMapping("/schedule/users/{username}/dates/{updatedate}")
    public List<ScheduleResponseDto> getDateSchedules(@PathVariable String username, @PathVariable String updatedate) {
        String sql = "SELECT * FROM schedule WHERE username = ? and DATE_FORMAT(updatedate, '%Y%m%d') = ?";
        updatedate = updatedate.substring(0, 8);
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String content = rs.getString("content");
                String createdate = rs.getString("createdate");
                String updatedate = rs.getString("updatedate");
                return new ScheduleResponseDto(id, username, content, createdate,updatedate);
            }
        }, username, updatedate);
    }

    @PutMapping("schedule/{id}/{pw}")
    public Long updateSchedule(@PathVariable Long id, @PathVariable String pw, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = findById(id);
        if(schedule != null) {
            String sql = "UPDATE schedule SET username = ?, content = ? WHERE id = ? and pw = ?";
            jdbcTemplate.update(sql, scheduleRequestDto.getUsername(), scheduleRequestDto.getContent(), id, pw);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("schedule/{id}/{pw}")
    public long deleteSchedule(@PathVariable Long id, @PathVariable String pw) {

        Schedule schedule = findById(id);
        if(schedule != null) {
            String sql = "DELETE FROM schedule WHERE id = ? and pw = ?";
            jdbcTemplate.update(sql, id, pw);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    private Schedule findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setUsername(resultSet.getString("username"));
                schedule.setCreatedate(resultSet.getString("createdate"));
                schedule.setUpdatedate(resultSet.getString("updatedate"));
                schedule.setContent(resultSet.getString("content"));
                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
