package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Manager;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Schedule save(Schedule schedule) {
        String sql = "INSERT INTO schedule (content, managerId) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, schedule.getContent());
            preparedStatement.setLong(2, schedule.getManagerId());
            return preparedStatement;
        });
        return schedule;
    }


    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT * FROM schedule s left join manager m on m.managerId = s.managerId";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long scheduleId = rs.getLong("scheduleId");
                Long managerId = rs.getLong("managerId");
                String username = rs.getString("username");
                String content = rs.getString("content");
                String createdate = rs.getString("createdate");
                String updatedate = rs.getString("updatedate");
                return new ScheduleResponseDto(scheduleId, managerId, username,content, createdate,updatedate);
            }
        });
    }


    public List<ScheduleResponseDto> findWithUserDate(String username, String updatedate) {
        String sql = "SELECT * FROM schedule s join manager m on s.managerId = m.managerId where username = ? and DATE_FORMAT(s.updatedate, '%Y%m%d') = ?";
        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long scheduleId = rs.getLong("scheduleId");
                String username = rs.getString("username");
                String content = rs.getString("content");
                String createdate = rs.getString("createdate");
                String updatedate = rs.getString("updatedate");
                return new ScheduleResponseDto(scheduleId, username, content, createdate,updatedate);
            }
        }, username, updatedate);
    }


    public void update(Long scheduleId, Long managerId, String pw, ScheduleRequestDto scheduleRequestDto) {
        String sql = "UPDATE schedule s join manager m on s.managerId = m.managerId SET m.username = ?, s.content = ? where m.managerId = ? and m.pw = ? and s.scheduleId = ?";
        jdbcTemplate.update(sql, scheduleRequestDto.getUsername(), scheduleRequestDto.getContent(), managerId, pw, scheduleId);
    }

    public void delete(Long scheduleId, Long managerId, String pw) {
        String sql = "DELETE FROM schedule WHERE scheduleId = ?";
        jdbcTemplate.update(sql, scheduleId);
    }

    public Schedule findById(Long scheduleId) {
        String sql = "SELECT * FROM schedule WHERE scheduleId = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(resultSet.getLong("scheduleId"));
                schedule.setCreatedate(resultSet.getString("createdate"));
                schedule.setUpdatedate(resultSet.getString("updatedate"));
                schedule.setContent(resultSet.getString("content"));
                schedule.setManagerId(resultSet.getLong("managerId"));
                return schedule;
            } else {
                return null;
            }
        }, scheduleId);
    }

    public Manager findManager(Long managerId, String pw) {
        String sql = "SELECT * FROM manager WHERE managerId = ? and pw = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Manager manager = new Manager();
                manager.setManagerId(resultSet.getLong("managerId"));
                manager.setUsername(resultSet.getString("username"));
                manager.setCreatedate(resultSet.getString("createdate"));
                manager.setUpdatedate(resultSet.getString("updatedate"));
                manager.setEmail(resultSet.getString("email"));
                return manager;
            } else {
                return null;
            }
        }, managerId, pw);
    }
}
