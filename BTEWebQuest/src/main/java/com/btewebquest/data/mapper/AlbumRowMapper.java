package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.AlbumEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class maps' database results to AlbumEntity
 *
 * @author sfradet
 * @version 1.0
 */
public class AlbumRowMapper implements RowMapper<AlbumEntity> {

    /**
     * Maps results to AlbumEntity
     *
     * @param rs ResultSet
     * @param rowNum Row Number
     * @return AlbumEntity
     * @throws SQLException
     */
    @Override
    public AlbumEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AlbumEntity(rs.getInt("ALBUM_ID"),
                rs.getString("ALBUM_NAME"),
                rs.getInt("ALBUM_YEAR"),
                rs.getBoolean("IS_MIXED"),
                rs.getBoolean("IS_MASTERED"));
    }
}
