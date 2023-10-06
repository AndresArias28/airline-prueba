package com.col.pop.san.airline.ui;

import com.col.pop.san.airline.application.service.AirplaneService;
import com.col.pop.san.airline.domain.entity.Airplane;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/flights")
    public List<Airplane> getAirplain(){
        return airplaneService.findAll();

    }
}
