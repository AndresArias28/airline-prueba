package com.col.pop.san.airline.ui;

import com.col.pop.san.airline.application.service.AirplaneService;
import com.col.pop.san.airline.domain.entity.Airplane;
import com.col.pop.san.airline.domain.entity.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/")
    public List<Airplane> getAirplain() {
        return airplaneService.findAll();

    }

    @GetMapping("/migration")
    public List<Flight> getFlights() {
        return airplaneService.findFlight();
    }

    @GetMapping("/migrationFetch/{id}")
    public Airplane getAirplaneUsingFetchById(@PathVariable Integer id) {

        return airplaneService.findAirplaneFetch(id);
    }

    @GetMapping("/airplane/{airport}")
    public List<Flight> getFlights(@PathVariable String airport) {
        return  airplaneService.getFlights(airport);
    }

    //@GetMapping("/airplane/")
}
