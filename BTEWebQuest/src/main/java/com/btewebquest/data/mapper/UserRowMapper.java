package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class maps' database results to UserEntity
 *
 * @author sfradet
 * @version 1.0
 */
public class UserRowMapper implements RowMapper<UserEntity> {

    /**
     * Maps results to UserEntity
     *
     * @param rs ResultSet
     * @param rowNum Row Number
     * @return AlbumEntity
     * @throws SQLException
     */
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new UserEntity(rs.getInt("ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("USER_NAME"),
                rs.getString("PASSWORD"));
    }
}
