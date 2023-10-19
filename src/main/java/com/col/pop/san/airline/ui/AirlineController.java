package com.col.pop.san.airline.ui;

import com.col.pop.san.airline.application.service.AirlineService;
import com.col.pop.san.airline.application.service.Checkinservice;
import com.col.pop.san.airline.domain.entity.*;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

   /* @GetMapping("/nativeQ/{id}")//NO SIRVE
    public ResponseEntity<?> getPassengerNativeByAgeId(@PathVariable Integer id){
        List<Map<String, Object>> passengers = airlineService.getPassengerNativetById(id);
        return new ResponseEntity<>(passengers, HttpStatus.OK);

    }

    @GetMapping("/nativeResponseClass/{id}")//NO SRIVE
    public ResponseEntity<List<PassengerResponse>> getPassengerResponseClassByFlightId(@PathVariable Integer id) {
        List<PassengerResponse> passengersList = checkinservice.getPassengersClassResponseByFlightId(id);
        return new ResponseEntity<>(passengersList, HttpStatus.OK);
    }*/

    @GetMapping("/passengersList")
    public ResponseEntity<List<RespuestaPrueba>> get3atributosByFlightId() {
        List<RespuestaPrueba> passengersList = checkinservice.get3atributes();
        return new ResponseEntity<>(passengersList, HttpStatus.OK);
    }

    @GetMapping("/atributos/{id}")
    public ResponseEntity<?> get3atributosByFlightId(@PathVariable Integer id) {
        List<RespuestaPrueba> passengersList = checkinservice.get3atributes(id);
        return new ResponseEntity<>(passengersList, HttpStatus.OK);
    }

    @GetMapping("/atributos2/{id}")
    public ResponseEntity<?> get3atributosByFlightId2(@PathVariable Integer id) {
        List<RespuestaPrueba2> passengersList = checkinservice.get3atributes2(id);
        return new ResponseEntity<>(passengersList, HttpStatus.OK);
    }

    @GetMapping("/atributesAll/{id}")
    public ResponseEntity<?> getAllatributosByFlightId(@PathVariable Integer id) {
        List<PassengerResponse> passengersList = checkinservice.getAllatributes(id);
        return new ResponseEntity<>(passengersList, HttpStatus.OK);
    }


}
