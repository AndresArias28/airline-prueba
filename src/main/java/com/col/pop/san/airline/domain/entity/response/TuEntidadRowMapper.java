package com.col.pop.san.airline.domain.entity.response;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public  class TuEntidadRowMapper implements RowMapper<FlightData> {

    private List<PassengerResponse> passegenrs;

    public TuEntidadRowMapper(List<PassengerResponse> passegenrs) {
        this.passegenrs = passegenrs;
    }

    @Override
    public FlightData mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        FlightData entidad = new FlightData();
        entidad.setFlightId(resultSet.getInt("flight_id"));
        entidad.setTakeoffDateTime(resultSet.getInt("takeoff_date_time"));
        entidad.setTakeoffAirport(resultSet.getString("takeoff_airport"));
        entidad.setAirplaneId(resultSet.getInt("airplane_id"));
        entidad.setPassengers(passegenrs);  // Asigna la lista de pasajeros a la entidad FlightData
        return entidad;

    }
}
