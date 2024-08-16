package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Manager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class ManagerRepository {
    private final JdbcTemplate jdbcTemplate;

    public ManagerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Manager save(Manager manager) {
        String sql = "INSERT INTO manager (username, pw, email) VALUES (?, ?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, manager.getUsername());
            preparedStatement.setString(2, manager.getPw());
            preparedStatement.setString(3, manager.getEmail());
            return preparedStatement;
        });
        return manager;
    }
}
