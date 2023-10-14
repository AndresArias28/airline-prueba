package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.infraestructure.AirlineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckinserviceImpl implements Checkinservice {

    private final AirlineDAO airlineDAO;

    @Autowired
    public CheckinserviceImpl(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Override
    public List<Passenger> getPassenger() {
        return airlineDAO.getPassengerList();
    }

    @Override
    public List<Passenger> getPassengersByflightId(Integer id) {
        return airlineDAO.getPassengersByflightId(id);
    }

    public List<Passenger> orderPassengersByAge(){
        List<Passenger> passengers = getPassenger();
        return passengers;
    }





}
