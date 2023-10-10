package com.col.pop.san.airline.ui;

import com.col.pop.san.airline.application.service.AirlineService;
import com.col.pop.san.airline.domain.entity.Airplane;
import com.col.pop.san.airline.domain.entity.BoardingPass;
import com.col.pop.san.airline.domain.entity.Flight;
import com.col.pop.san.airline.domain.entity.Passenger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
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

    @GetMapping("/passenger/{name}")
    public List<Passenger> getPassenger(@PathVariable String name) {
        return airlineService.getPassenger(name);
    }

    @GetMapping("/boardingPass/{id}")
    public List<BoardingPass> getBoardingPassById(@PathVariable Integer id) {
        return airlineService.getBoardingPassById(id);
    }


}
