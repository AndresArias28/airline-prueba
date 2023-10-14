package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.*;
import com.col.pop.san.airline.infraestructure.AirlineDAO;
import com.col.pop.san.airline.infraestructure.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineDAO airlineDAO;
    private final PassengerRepository passengerRepository;

    @Autowired
    public AirlineServiceImpl(AirlineDAO airlineDAO, PassengerRepository passengerRepository) {
        this.airlineDAO = airlineDAO;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public List<Airplane> findAll() {
        return airlineDAO.getAirplanes();
    }

    @Override
    public List<Flight> findFlight() {
        return airlineDAO.getFlights();
    }

    @Override
    public Airplane findAirplaneFetch(Integer AirplaneId) {
        return airlineDAO.findAirplaneAndFlightsByAirplaneId(AirplaneId);
    }

    @Override
    public List<Flight> getFlights(String id) {
        return airlineDAO.getFlightsByAirport(id);
    }



    @Override
    public List<BoardingPass> getBoardingPassById() {
        return airlineDAO.getBoardingPassById();
    }

    @Override
    public List<Flight> getFlightsUsingFetchBy(Integer id) {
        return airlineDAO.getFlightsUsingFetchBy(id);
    }

    @Override
    public List<Seat> getSeats() {
        return airlineDAO.getSeats();
    }

    @Override
    public List<Map<String, Object>> getPassengerNativetById(Integer id) {
        return airlineDAO.obtenerDatosTransformados(id);
    }
}
