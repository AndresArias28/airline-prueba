package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Airplane;
import com.col.pop.san.airline.domain.entity.Flight;
import com.col.pop.san.airline.domain.entity.Passenger;

import java.util.List;

public interface AirplaneService {

    List<Airplane> findAll();

    List<Flight> findFlight();

    Airplane findAirplaneFetch(Integer AirplaneId);

    List<Flight>  getFlights(String Air);

    List<Passenger> getPassenger(String name);
}
