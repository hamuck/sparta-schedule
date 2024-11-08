package com.example.spartaschedule.repository;

import com.example.spartaschedule.dto.UserResponseDto;
import com.example.spartaschedule.entity.Schedule;
import com.example.spartaschedule.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource datasource){
        this.jdbcTemplate =new JdbcTemplate(datasource);
    }

    //스케쥴을 생성할때 User도 같이 생성하기 위해 사용한다.
    public Number saveUserToSchedule(Schedule schedule){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("User")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name",schedule.getUserName());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return key;
    }

    //User 정보를 입력받아 생성할 때 사용한다.
    @Override
    public UserResponseDto saveUser(User user){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("User")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        LocalDateTime nowTime = LocalDateTime.now();
        parameters.put("name",user.getName());
        parameters.put("mail",user.getMail());
        parameters.put("creatAt", Timestamp.valueOf(nowTime));
        parameters.put("updateAt",Timestamp.valueOf(nowTime));

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new UserResponseDto(key.longValue(),user.getName(),user.getMail(),Timestamp.valueOf(nowTime));
    }
}
