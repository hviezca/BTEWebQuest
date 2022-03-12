package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<UserEntity> {

    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new UserEntity(rs.getInt("ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("USER_NAME"),
                rs.getString("PASSWORD"));
    }
}
