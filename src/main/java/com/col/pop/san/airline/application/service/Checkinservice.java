package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.response.*;
import java.util.List;

public interface Checkinservice {

    List<PassengerResponse> getPassengerAttributesByFlightId(Integer flightId, List<SeatAvailable> seatAvaliables, Integer airplaneId);

    FlightData getResponseFlight(Integer id, List<PassengerResponse> passengersList);

    Integer getAirplaneIdByFlightId(Integer id);

    List<SeatAvailable> getSeatAvailableByAirplaneId(Integer airplaneId);
}
