package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Airplane;
import com.col.pop.san.airline.domain.entity.Flight;
import com.col.pop.san.airline.infraestructure.AirplaneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneDAO airplaneDAO;

    @Autowired
    public AirplaneServiceImpl(AirplaneDAO airplaneDAO) {
        this.airplaneDAO = airplaneDAO;
    }

    @Override
    public List<Airplane> findAll() {
        return airplaneDAO.getAirplanes();
    }

    @Override
    public List<Flight> findFlight() {
        return airplaneDAO.getFlights();
    }

    @Override
    public Airplane findAirplaneFetch(Integer AirplaneId) {
        return airplaneDAO.findAirplaneAndFlightsByAirplaneId(AirplaneId);
    }
}
