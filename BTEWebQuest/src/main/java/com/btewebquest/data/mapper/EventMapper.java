package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.EventEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<EventEntity> {
    @Override
    public EventEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EventEntity(rs.getInt("EVENT_ID"),
                rs.getDate("DATE"),
                rs.getDouble("PRICE"),
                rs.getBoolean("ALL_AGES"),
                rs.getInt("VENUE_ID"));
    }
}
