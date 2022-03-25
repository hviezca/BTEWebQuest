package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.BookingEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper implements RowMapper<BookingEntity> {
    @Override
    public BookingEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BookingEntity(rs.getInt("ID"),
                rs.getDate("DATE"),
                rs.getInt("EVENT_ID"),
                rs.getInt("MESSAGE_ID"));
    }
}
