package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.TrackProgressEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TrackProgressRowMapper implements RowMapper<TrackProgressEntity> {

    @Override
    public TrackProgressEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TrackProgressEntity(rs.getInt("PROGRESS_ID"),
                rs.getString("INSTRUMENT_NAME"),
                rs.getBoolean("IS_RECORDED"),
                rs.getInt("TRACK_ID"));
    }
}
