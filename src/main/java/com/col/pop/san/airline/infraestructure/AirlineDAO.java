package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.Airplane;
import com.col.pop.san.airline.domain.entity.BoardingPass;
import com.col.pop.san.airline.domain.entity.Flight;
import com.col.pop.san.airline.domain.entity.Passenger;

import java.util.List;

public interface AirlineDAO {

    List<Airplane> getAirplanes();

    List<Flight> getFlights();


    Airplane findAirplaneAndFlightsByAirplaneId(Integer Id);

    List<Flight> getFlightsByAirport(String airport);

    List<Passenger> getPassengerByName(String name);

    List<BoardingPass> getBoardingPassById(Integer id);
}
