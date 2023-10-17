package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba;

import java.util.List;

public interface Checkinservice {

    List<Passenger> getPassenger();

    List<Passenger> getPassengersByflightId(Integer id);

    List<PassengerResponse> getPassengersClassResponseByFlightId(Integer id);

    List<RespuestaPrueba> get3atributes();
}
