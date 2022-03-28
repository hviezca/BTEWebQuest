/**
 * MESSAGE MAPPER
 * An Mapper class for Message objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.MessageEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<MessageEntity> {
    @Override
    public MessageEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MessageEntity(rs.getInt("MESSAGE_ID"),
                rs.getString("SUBJECT"),
                rs.getString("MESSAGE"),
                rs.getBoolean("HAS_REPLY"));
    }
}
