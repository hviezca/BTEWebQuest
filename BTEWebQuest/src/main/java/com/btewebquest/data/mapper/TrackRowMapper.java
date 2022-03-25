package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.TrackEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackRowMapper implements RowMapper<TrackEntity> {
    @Override
    public TrackEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TrackEntity(rs.getInt("TRACK_ID"),
                rs.getString("TRACK_NAME"),
                rs.getInt("TRACK_NUMBER"),
                rs.getInt("ALBUM_ID"));
    }
}
