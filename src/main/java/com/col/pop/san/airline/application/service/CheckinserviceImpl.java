package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba;
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

   /* @Override
    public List<Passenger> getPassengersByflightId(Integer id) {
        return airlineDAO.getPassengersByflightId(id);
    }

    @Override
    public List<PassengerResponse> getPassengersClassResponseByFlightId(Integer id) {
        return airlineDAO.getPassengersClassResponseByFlightId(id);
    }*/

    @Override
    public List<RespuestaPrueba> get3atributes() {
        return airlineDAO.get3atributes();
    }

    @Override
    public List<RespuestaPrueba> get3atributes(Integer id) {
        return airlineDAO.getPassengers(id);
    }

    public List<Passenger> orderPassengersByAge(){
        return getPassenger();
    }





}
