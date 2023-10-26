package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.domain.entity.response.*;

import java.util.List;

public interface Checkinservice {

    List<Passenger> getPassenger();

    //List<Passenger> getPassengersByflightId(Integer id);

   // List<PassengerResponse> getPassengersClassResponseByFlightId(Integer id);

    List<RespuestaPrueba> get3atributes();

    List<RespuestaPrueba> get3atributes(Integer id);

    List<RespuestaPrueba2> get3atributes2(Integer id);

    List<PassengerResponse> getAllatributes(Integer id);

    FlightData getResponse(Integer id);

    FlightData getResponseFlight(Integer id, List<PassengerResponse> passengersList);


}
