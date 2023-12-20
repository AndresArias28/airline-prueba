package com.col.pop.san.airline.domain.entity.response;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public  class FlightResponseRowMapper implements RowMapper<FlightData> {

    private final List<PassengerResponse> passengers;

    public FlightResponseRowMapper(List<PassengerResponse> passengers) {
        this.passengers = passengers;
    }

    @Override
    public FlightData mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        FlightData flight = new FlightData();
        flight.setFlightId(resultSet.getInt("flight_id"));
        flight.setTakeoffDateTime(resultSet.getInt("takeoff_date_time"));
        flight.setTakeoffAirport(resultSet.getString("takeoff_airport"));
        flight.setLandingDateTime(resultSet.getInt("landing_date_time"));
        flight.setLandingAirport(resultSet.getString("landing_airport"));
        flight.setAirplaneId(resultSet.getInt("airplane_id"));
        flight.setPassengers(passengers);  // Asigna la lista de pasajeros a la entidad FlightData
        return flight;
    }
}
