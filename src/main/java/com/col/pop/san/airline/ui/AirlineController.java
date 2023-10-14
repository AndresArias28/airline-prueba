package com.col.pop.san.airline.ui;

import com.col.pop.san.airline.application.service.AirlineService;
import com.col.pop.san.airline.application.service.Checkinservice;
import com.col.pop.san.airline.domain.entity.*;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flights")
public class AirlineController {

    private final AirlineService airlineService;
    private final Checkinservice checkinservice;

    @Autowired
    public AirlineController(AirlineService airlineService, Checkinservice checkinservice) {
        this.airlineService = airlineService;
        this.checkinservice = checkinservice;
    }
    @GetMapping("/")
    public List<Airplane> getAirplain() {
        return airlineService.findAll();
    }

    @GetMapping("/migration")
    public List<Flight> getFlights() {
        return airlineService.findFlight();
    }

    @GetMapping("/migrationFetch/{id}")
    public Airplane getAirplaneUsingFetchById(@PathVariable Integer id) {
        return airlineService.findAirplaneFetch(id);
    }

    @GetMapping("/airplane/{airport}")
    public List<Flight> getFlights(@PathVariable String airport) {
        return  airlineService.getFlights(airport);
    }

    @GetMapping("/passenger")
    public List<Passenger> getPassenger() {
        return checkinservice.getPassenger();
    }

    @GetMapping("/boardingPass")
    public List<BoardingPass> getBoardingPassById() {
        return airlineService.getBoardingPassById();
    }

    @GetMapping("/flightTest/{id}")
    public List<Flight> getFlightsUsingFetchByFlightId(@PathVariable Integer id) {
        return airlineService.getFlightsUsingFetchBy(id);
    }

    @GetMapping("/seat")
    public List<Seat> getSeats(){
        return airlineService.getSeats();
    }

    @GetMapping("/nativeQ/{id}")
    public ResponseEntity<?> getPassengerNativeByAgeId(@PathVariable Integer id){
        List<Map<String, Object>> passengers = airlineService.getPassengerNativetById(id);
        return new ResponseEntity<>(passengers, HttpStatus.OK);

    }





}
