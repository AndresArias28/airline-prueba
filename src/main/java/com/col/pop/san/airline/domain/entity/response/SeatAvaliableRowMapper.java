package com.col.pop.san.airline.domain.entity.response;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatAvaliableRowMapper implements RowMapper {
    @Override
    public SeatAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
        SeatAvailable seat = new SeatAvailable();
        seat.setSeatId(rs.getInt("seat_id"));
        seat.setSeatColumn(rs.getString("seat_column"));
        seat.setSeatRow(rs.getInt("seat_row"));
        seat.setSeatTypeId(rs.getInt("seat_type_id"));
        seat.setAirplaneId(rs.getInt("airplane_id"));
        return seat;
    }
}
