package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.Airplane;
import com.col.pop.san.airline.domain.entity.Flight;

import java.util.List;

public interface AirplaneDAO {

    List<Airplane> getAirplanes();

    List<Flight> getFlights();


    Airplane findAirplaneAndFlightsByAirplaneId(Integer Id);

    List<Flight> getFlightsByAirport(String airport);
}
