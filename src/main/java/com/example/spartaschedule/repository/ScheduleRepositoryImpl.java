package com.example.spartaschedule.repository;

import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;
import com.example.spartaschedule.entity.User;
import com.example.spartaschedule.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule, Number userId){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Schedule")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        LocalDateTime nowTime = LocalDateTime.now();
        parameters.put("userId",userId);
        parameters.put("password",schedule.getPassword());
        parameters.put("userName",schedule.getUserName());
        parameters.put("title",schedule.getTitle());
        parameters.put("contents",schedule.getContents());
        parameters.put("createAt", Timestamp.valueOf(nowTime));
        parameters.put("updateAt", Timestamp.valueOf(nowTime));

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(),schedule.getUserName(),schedule.getTitle(),schedule.getContents(),Timestamp.valueOf(nowTime));
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from Schedule", scheduleRowMapperToResponseDto());
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from Schedule where id = ?", scheduleRowMapperToSchedule(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id){
        List<Schedule> result = jdbcTemplate.query("select * from Schedule where id = ?",scheduleRowMapperToSchedule(),id);
        return result.stream().findAny();
    }


    @Override
    public List<ScheduleResponseDto> findScheduleByDate(int days) {
        String sql = "SELECT * FROM Schedule WHERE updateAt < DATE_SUB(NOW(), INTERVAL -? DAY)";
        return jdbcTemplate.query(sql, scheduleRowMapperToResponseDto(), days);
    }


    @Override
    public int updateSchedule(Long id,String password, String title, String contents){
        String getPasswordSql = "SELECT password FROM Schedule WHERE id = ?";
        String dbPassword = jdbcTemplate.queryForObject(getPasswordSql, String.class, id);

        if (dbPassword == null || !dbPassword.equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match");
        }
        String sql = "update Schedule set title = ?, contents = ?, updateAt = ? where id = ?";
        return jdbcTemplate.update(
                sql,title, contents, Timestamp.valueOf(LocalDateTime.now()),id
        );
    }

    @Override
    public int deleteSchedule(Long id, String password){
        String getPasswordSql = "SELECT password FROM Schedule WHERE id = ?";
        String dbPassword = jdbcTemplate.queryForObject(getPasswordSql, String.class, id);

        if (dbPassword == null || !dbPassword.equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match");
        }
        return jdbcTemplate.update(
                "delete from Schedule where id = ?", id
        );
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapperToResponseDto(){
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("userName"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createAt")
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperToSchedule(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("userName"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createAt"),
                        rs.getTimestamp("updateAt")
                );
            }
        };
    }
}
