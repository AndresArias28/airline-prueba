package com.col.pop.san.airline.domain.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
public class FlightResponse {
    private Integer code;
    private FlightData data;

}

