package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.*;
import com.col.pop.san.airline.domain.entity.response.*;

import java.util.List;

public interface AirlineDAO {

    List<Airplane> getAirplanes();

   // List<Flight> getFlights();

    Airplane findAirplaneAndFlightsByAirplaneId(Integer Id);

    List<Flight> getFlightsByAirport(String airport);

    List<BoardingPass> getBoardingPassById();

    List<Flight> getFlightsUsingFetchBy(Integer id);

    List<Seat> getSeats();

    List<Passenger> getPassengerList();

    List<RespuestaPrueba> get3atributes();

    List<RespuestaPrueba> getPassengers(Integer id);

    List<RespuestaPrueba2> getPassengers2(Integer id);

    List<PassengerResponse> getPassengersAll(Integer id);

    //FlightResponse getFlightResponse(Integer id);

    FlightData getFlightRes(Integer id);
    FlightData getFlights(Integer id, List<PassengerResponse> passengersList);

    // List<Passenger> getPassengerNative(Integer id);
}
