package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.UserRoleEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class maps' database results to UserRoleEntity
 *
 * @author sfradet
 * @version 1.0
 */
public class UserRoleRowMapper implements RowMapper<UserRoleEntity> {

    /**
     * Maps results to UserRoleEntity
     *
     * @param rs ResultSet
     * @param rowNum Row Number
     * @return AlbumEntity
     * @throws SQLException
     */
    @Override
    public UserRoleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserRoleEntity(rs.getInt("USERS_ROLE_ID"), rs.getInt("ROLE_ID"), rs.getInt("USER_ID" ));
    }
}
