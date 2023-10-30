package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.domain.entity.response.*;

import java.util.List;

public interface Checkinservice {

    List<PassengerResponse> getPassengerAttributesByFlightId(Integer id);


    FlightData getResponseFlight(Integer id, List<PassengerResponse> passengersList);


    FlightResponse generateFlightResponse(Integer id, FlightData flightData);
}
