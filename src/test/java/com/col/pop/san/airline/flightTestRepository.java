package com.col.pop.san.airline;

import com.col.pop.san.airline.domain.entity.Flight;
import com.col.pop.san.airline.infraestructure.AirlineDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class flightTestRepository {
    @Autowired
    AirlineDAO airlineDAO;

    @Test
    public  void getFlights(){
        // List<Flight> flights = airlineDAO.getFlights(1);
       // System.out.println("flights = " + flights);

    }

}
