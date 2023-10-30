package com.col.pop.san.airline.ui;

import com.col.pop.san.airline.application.service.Checkinservice;
import com.col.pop.san.airline.domain.entity.response.FlightData;
import com.col.pop.san.airline.domain.entity.response.FlightResponse;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
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

    @GetMapping("/flights/{id}/atributesAll")
    public ResponseEntity<?> getAllatributosByFlightId(@PathVariable Integer id) {
        List<PassengerResponse> passengersList = checkinservice.getPassengerAttributesByFlightId(id);
        FlightData response = checkinservice.getResponseFlight(id, passengersList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/flights/{id}/passengers")
    public ResponseEntity<?> getFlightPassengers(@PathVariable Integer id) {
        List<PassengerResponse> passengerAttributes  = checkinservice.getPassengerAttributesByFlightId(id);
        FlightData flightData  = checkinservice.getResponseFlight(id, passengerAttributes);
        FlightResponse flightResponse = checkinservice.generateFlightResponse(id, flightData);
        return new ResponseEntity<>(flightResponse, HttpStatus.OK);
    }



}
