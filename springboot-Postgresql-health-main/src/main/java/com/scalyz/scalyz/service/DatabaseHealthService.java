package com.scalyz.scalyz.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseHealthService {
    private final JdbcTemplate jdbc;

    public DatabaseHealthService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean isUp() {
        try {
            Integer one = jdbc.queryForObject("SELECT 1", Integer.class);
            return one != null && one == 1;
        } catch (Exception e) {
            return false;
        }
    }
}
