package com.col.pop.san.airline;

import com.col.pop.san.airline.domain.entity.Flight;
import com.col.pop.san.airline.domain.entity.response.FlightData;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.infraestructure.AirlineDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class flightTestRepository {
    @Autowired
    AirlineDAO airlineDAO;

    @Test
    public void getFlights(){
        List<PassengerResponse> listp = new ArrayList<PassengerResponse>();
        listp.add(new PassengerResponse(1,"sdfs123", "santi",23, "popayan", 010, 1,2, 1));
        FlightData flights = airlineDAO.getFlights(3, listp);
       System.out.println("flights = " + flights);

    }

}
