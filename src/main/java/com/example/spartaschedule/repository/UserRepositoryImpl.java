package com.example.spartaschedule.repository;

import com.example.spartaschedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource datasource){
        this.jdbcTemplate =new JdbcTemplate(datasource);
    }

    @Override
    public Number saveUser(Schedule schedule){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("User")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name",schedule.getUserName());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return key;
    }
}
