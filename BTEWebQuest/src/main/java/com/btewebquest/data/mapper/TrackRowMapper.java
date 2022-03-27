package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.TrackEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class maps' database results to TrackEntity
 *
 * @author sfradet
 * @version 1.0
 */
public class TrackRowMapper implements RowMapper<TrackEntity> {

    /**
     * Maps results to TrackEntity
     *
     * @param rs ResultSet
     * @param rowNum Row Number
     * @return AlbumEntity
     * @throws SQLException
     */
    @Override
    public TrackEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TrackEntity(rs.getInt("TRACK_ID"),
                rs.getString("TRACK_NAME"),
                rs.getInt("TRACK_NUMBER"),
                rs.getInt("ALBUM_ID"),
                rs.getBoolean("VOCALS"),
                rs.getBoolean("GUITAR"),
                rs.getBoolean("DRUMS"),
                rs.getBoolean("BASS"));
    }
}
