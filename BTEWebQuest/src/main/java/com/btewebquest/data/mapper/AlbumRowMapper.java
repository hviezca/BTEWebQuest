package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.AlbumEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumRowMapper implements RowMapper<AlbumEntity> {

    @Override
    public AlbumEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AlbumEntity(rs.getInt("ALBUM_ID"),
                rs.getString("ALBUM_NAME"),
                rs.getInt("ALBUM_YEAR"),
                rs.getBoolean("IS_MIXED"),
                rs.getBoolean("IS_MASTERED"));
    }
}
