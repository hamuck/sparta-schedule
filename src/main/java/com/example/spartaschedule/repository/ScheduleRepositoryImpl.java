package com.example.spartaschedule.repository;

import com.example.spartaschedule.dto.ScheduleResponseDto;
import com.example.spartaschedule.entity.Schedule;
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

//스케쥴의 db를 다루는 ScheduleRepository
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //스케쥴을 생성할때 사용함
    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule, Number userId){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("Schedule")
                .usingGeneratedKeyColumns("id");

        //parameters 라는 맵에 입력받은 정보를 넣는다
        Map<String, Object> parameters = new HashMap<>();
        //현재 시간을 작성하기 위해 nowTime 생성
        LocalDateTime nowTime = LocalDateTime.now();
        parameters.put("userId",userId);
        parameters.put("password",schedule.getPassword());
        parameters.put("userName",schedule.getUserName());
        parameters.put("title",schedule.getTitle());
        parameters.put("contents",schedule.getContents());
        parameters.put("createAt", Timestamp.valueOf(nowTime));
        parameters.put("updateAt", Timestamp.valueOf(nowTime));

        //parameters에 있는 값을 db에 insert하고 그 key 값(schedule 테이블의 id)을 반환해 Number key 에 넣는다.
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        //parameters 와 key 를 통해 scheduleresponsedto를 생성하고 반환한다.
        return new ScheduleResponseDto(key.longValue(),schedule.getUserName(),schedule.getTitle(),schedule.getContents(),Timestamp.valueOf(nowTime));
    }

    //모든 스케쥴을 리스트 형태로 반환한다.
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from Schedule", scheduleRowMapperToResponseDto());
    }

    //입력받은 id 값을 query로 찾고 그 값을 scheduleRowMapper를 사용해 schedule로 result에 추가한다.
    public Schedule findScheduleByUserIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from Schedule where userId = ?", scheduleRowMapperToSchedule(), id);
        //스트림을 사용, 만약 result에 값이 없을시 예외처리한다.
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    //입력받은 id 값을 query로 찾고 그 값을 scheduleRowMapper를 사용해 schedule로 result에 추가한다.
    //예외처리가 되어있지 않고, Optional 로 반환한다.
    @Override
    public Optional<Schedule> findScheduleById(Long id){
        List<Schedule> result = jdbcTemplate.query("select * from Schedule where id = ?",scheduleRowMapperToSchedule(),id);
        return result.stream().findAny();
    }

    //현재 날짜 -days 에 해당하는 스케쥴을 리스트로 반환한다.
    @Override
    public List<ScheduleResponseDto> findScheduleByDate(int days) {
        String sql = "SELECT * FROM Schedule WHERE updateAt < DATE_SUB(NOW(), INTERVAL -? DAY)";
        return jdbcTemplate.query(sql, scheduleRowMapperToResponseDto(), days);
    }

    //sql 쿼리문을 사용해 입력받은 값을 덮어쓴다. 이때 matchPassword를 사용해 비밀번호가 맞는지 확인한다.
    @Override
    public int updateSchedule(Long id,String password, String title, String contents){
        matchPassword(id, password);
        String sql = "update Schedule set title = ?, contents = ?, updateAt = ? where id = ?";
        return jdbcTemplate.update(
                sql,title, contents, Timestamp.valueOf(LocalDateTime.now()),id
        );
    }

    //sql 쿼리문을 사용해 입력받은 id에 해당하는 스케쥴을 삭제한다. 이때 matchPassword를 사용해 비밀번호가 맞는지 확인한다.
    @Override
    public int deleteSchedule(Long id, String password){
        matchPassword(id, password);
        return jdbcTemplate.update(
                "delete from Schedule where id = ?", id
        );
    }

    //입력받은 패스워드가 id로 찾은 스케쥴의 패스워드 값과 맞는지 확인한다.
    private boolean matchPassword(Long id,String password){
        String getPasswordSql = "SELECT password FROM Schedule WHERE id = ?";
        String dbPassword = jdbcTemplate.queryForObject(getPasswordSql, String.class, id);
        boolean result = true;

        if (dbPassword == null || !dbPassword.equals(password)) {
            result = false;
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match");
        }
        return result;
    }

    //ResultSet으로 받은 값을 ScheduleResponseDto로 변환한다.
    private RowMapper<ScheduleResponseDto> scheduleRowMapperToResponseDto(){
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("userName"),
                        rs.getLong("userId"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createAt")
                );
            }
        };
    }
    //ResultSet으로 받은 값을 Schedule로 변환한다.
    private RowMapper<Schedule> scheduleRowMapperToSchedule(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("userName"),
                        rs.getLong("userId"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createAt"),
                        rs.getTimestamp("updateAt")
                );
            }
        };
    }
}
