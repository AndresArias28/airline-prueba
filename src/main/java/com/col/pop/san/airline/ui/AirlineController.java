package com.col.pop.san.airline.ui;

import com.col.pop.san.airline.application.service.Checkinservice;
import com.col.pop.san.airline.domain.entity.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AirlineController {

    private final Checkinservice checkinservice;
    @Autowired
    public AirlineController(Checkinservice checkinservice) {
        this.checkinservice = checkinservice;
    }

    @GetMapping("/flights/{id}/passengers")
    public ResponseEntity<?> getAllatributosByFlightId(@PathVariable Integer id) {
        Integer airplaneId =  checkinservice.getAirplaneIdByFlightId(id);
        List<SeatAvailable>  listSeatAvailable = checkinservice.getSeatAvailableByAirplaneId(airplaneId);
        List<PassengerResponse> passengersList = checkinservice.getPassengerAttributesByFlightId(id, listSeatAvailable, airplaneId);
        FlightData response = checkinservice.getResponseFlight(id, passengersList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}