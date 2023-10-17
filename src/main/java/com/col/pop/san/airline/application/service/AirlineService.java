package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.*;

import java.util.List;
import java.util.Map;

public interface AirlineService {

    List<Airplane> findAll();

    List<Flight> findFlight();

    Airplane findAirplaneFetch(Integer AirplaneId);

    List<Flight>  getFlights(String Air);

    List<BoardingPass> getBoardingPassById();

    List<Flight> getFlightsUsingFetchBy(Integer id);

    List<Seat> getSeats();

    //List<Map<String, Object>> getPassengerNativetById(Integer id);
}
