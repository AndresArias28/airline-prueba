package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.response.*;
import java.util.List;

public interface AirlineDAO {

    List<PassengerResponse> getPassengersAtributes(Integer id);

    FlightData getFlightsAtributes(Integer id, List<PassengerResponse> passengersList);

    Integer getAirplaneIdByFlightId(Integer id);

    List<SeatAvailable> getSeatAvailableByAirplaneId(Integer airplaneId);
}
