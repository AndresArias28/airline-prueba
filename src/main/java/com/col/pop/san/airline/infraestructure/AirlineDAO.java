package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.*;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba;

import java.util.List;
import java.util.Map;

public interface AirlineDAO {

    List<Airplane> getAirplanes();

    List<Flight> getFlights();

    Airplane findAirplaneAndFlightsByAirplaneId(Integer Id);

    List<Flight> getFlightsByAirport(String airport);

    List<BoardingPass> getBoardingPassById();

    List<Flight> getFlightsUsingFetchBy(Integer id);

    List<Seat> getSeats();

    List<Map<String, Object>> obtenerDatosTransformados(Integer id);

    List<Passenger> getPassengerList();

    List<Passenger> getPassengersByflightId(Integer id);

    List<PassengerResponse> getPassengersClassResponseByFlightId(Integer id);

    List<RespuestaPrueba> get3atributes();

    // List<Passenger> getPassengerNative(Integer id);
}
