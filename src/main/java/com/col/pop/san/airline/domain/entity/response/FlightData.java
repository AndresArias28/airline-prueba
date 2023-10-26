package com.col.pop.san.airline.domain.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class FlightData extends FlightResponse{
    private Integer flightId;
    private Integer takeoffDateTime;
    private String takeoffAirport;
    private Integer landingDateTime;
    private String landingAirport;
    private Integer airplaneId;
    private List<PassengerResponse> passengers;

}
