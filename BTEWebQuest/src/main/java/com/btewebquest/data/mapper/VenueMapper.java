/**
 * VENUE MAPPER
 * An Mapper class for Venue objects
 * Author @ Hiram Viezca
 */

package com.btewebquest.data.mapper;

import com.btewebquest.data.entity.VenueEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VenueMapper implements RowMapper<VenueEntity> {
    @Override
    public VenueEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VenueEntity(rs.getInt("VENUE_ID"),
                rs.getString("VENUE_NAME"),
                rs.getString("VENUE_ADDRESS"),
                rs.getString("VENUE_CITY"),
                rs.getString("VENUE_STATE"),
                rs.getString("VENUE_ZIP"),
                rs.getInt("CONTACT_ID"));
    }
}
