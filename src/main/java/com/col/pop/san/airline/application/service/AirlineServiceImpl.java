package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Airplane;
import com.col.pop.san.airline.domain.entity.BoardingPass;
import com.col.pop.san.airline.domain.entity.Flight;
import com.col.pop.san.airline.domain.entity.Seat;
import com.col.pop.san.airline.infraestructure.AirlineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineDAO airlineDAO;

    @Autowired
    public AirlineServiceImpl(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
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

    /*@Override
    public List<Map<String, Object>> getPassengerNativetById(Integer id) {
        return airlineDAO.obtenerDatosTransformados(id);
    }*/
}
