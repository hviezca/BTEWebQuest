/**
 * CONTACT MAPPER
 * An Mapper class for Contact objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.ContactEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<ContactEntity> {
    @Override
    public ContactEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ContactEntity(rs.getInt("PERSON_ID"),
                rs.getString("NAME"),
                rs.getString("EMAIL"),
                rs.getString("PHONE"));
    }
}
