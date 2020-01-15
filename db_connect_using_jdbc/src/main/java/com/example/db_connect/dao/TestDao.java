package com.example.db_connect.dao;

// DAO : (Data Access Object) DB에 접근하는 객체.
// -> DB를 사용해서 데이터를 조작하는 기능을 한다.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TestDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getName(int userId) throws Exception {
        return jdbcTemplate.queryForMap("SELECT name FROM user WHERE userId = ?", userId);
    }
}
