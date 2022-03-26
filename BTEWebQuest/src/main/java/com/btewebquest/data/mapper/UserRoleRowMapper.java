package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.UserRoleEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRoleRowMapper implements RowMapper<UserRoleEntity> {
    @Override
    public UserRoleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserRoleEntity(rs.getInt("USERS_ROLE_ID"), rs.getInt("ROLE_ID"), rs.getInt("USER_ID" ));
    }
}
