package com.example.schedulenew.repository;

import com.example.schedulenew.dto.ScheduleResponseDto;
import com.example.schedulenew.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ScheduleResponseDto> findAll(String authorName, String modifyDate) {

        StringBuilder query = new StringBuilder("SELECT * FROM schedule WHERE 1=1");
        List<Object> params = new ArrayList<>();


        if (authorName != null) {
            query.append(" AND author_name = ?");
            params.add(authorName);
        }
        if (modifyDate != null) {
            query.append(" AND DATE_FORMAT(modify_date, '%Y-%m-%d) = ?");
            params.add(modifyDate);
        }

        query.append(" ORDER BY modify_date DESC");

        return jdbcTemplate.query(query.toString(), params.toArray(), scheduleRowMapper());

    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(Long id) {
        List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapperV2(), id)
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.Not_FOUND, id + ": 아이디에 맞는 데이터가 없습니다."));
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("task", schedule.getTask());
        params.put("authorName", schedule.getAuthorName());
        params.put("password", schedule.getPassword());
        params.put("write_date", schedule.getWriteDate());
        params.put("modify_date", schedule.getModifyDate());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));

        return new ScheduleResponseDto(key.longValue(), schedule.getTask(), schedule.getAuthorName(), schedule.getWriteDate(), schedule.getModifyDate());
    }

    @Override
    public int deleteSchedule(Long id, String password) {
        jdbcTemplate.update("DELETE FROM schedule WHERE id =  AND password = ?", id, password);
    }

    @Override
    public void updateSchedule(Long id, String task, String authorName, String password) {
        return jdbcTemplate.update("UPDATE schedule SET task = ?, author_name = ?, modify_date = ?, WHERE id = ? ADN PASSWORD = ?", task, authorName, LocalDateTime.now(), id, password);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id") ,
                        rs.getString("task"),
                        rs.getString("author_name"),
                        rs.getObject("write_date", LocalDateTime.class),
                        rs.getObject("modify_date", LocalDateTime.class)
                );
            }

        } ;
    }
    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLExecption {
                return new Schedule(
                        rs.getLong("id") ,
                        rs.getString("task"),
                        rs.getString("author_name"),
                        rs.getObject("write_date", LocalDateTime.class),
                        rs.getObject("modify_date", LocalDateTime.class)
                );
            }
        };
    }


}


