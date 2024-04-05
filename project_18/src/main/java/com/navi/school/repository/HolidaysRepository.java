package com.navi.school.repository;

import com.navi.school.entity.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import java.util.List;

@Repository
public class HolidaysRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HolidaysRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> listHolidays(){
        String sql = "SELECT * FROM holidays";
        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        List<Holiday> holidays = jdbcTemplate.query(sql, rowMapper);
        return holidays;
    }
}
