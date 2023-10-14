package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;

import java.util.List;

public interface Checkinservice {

    List<Passenger> getPassenger();
    List<Passenger> getPassengersByflightId(Integer id);

}
