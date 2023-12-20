package com.col.pop.san.airline;

import com.col.pop.san.airline.application.service.Checkinservice;
import com.col.pop.san.airline.domain.entity.response.FlightData;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.domain.entity.response.SeatAvailable;
import com.col.pop.san.airline.domain.entity.response.SeatAvailable;
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

    @Autowired
    Checkinservice checkinservice;

    @Test
    public void getFlights(){
        List<PassengerResponse> listp = new ArrayList<PassengerResponse>();
        listp.add(new PassengerResponse(1,"sdfs123", "santi",23, "popayan", 010, 1,2, 1));
        FlightData flights = airlineDAO.getFlightsAtributes(3, listp);
       System.out.println("flights = " + flights);

    }

   @Test
    public void getsetIdTest(){
       //PassengerResponse passenger = new PassengerResponse(32,"1061727054", "santiago", 17, "colombia", 58, 164, 1, 56);
       final Integer flightId = 1;
       final Integer airplaneId = 1;

       List<SeatAvailable> availableSeats = new ArrayList<SeatAvailable>( );

       SeatAvailable availableSeat1 = new SeatAvailable(1, "A", 1, 1, 1);
       SeatAvailable availableSeat2 = new SeatAvailable(2, "A", 2, 1, 1);
       SeatAvailable availableSeat3 = new SeatAvailable(3, "A", 3, 1, 1);
       SeatAvailable availableSeat4 = new SeatAvailable(4, "A", 4, 1, 1);
       SeatAvailable availableSeat5 = new SeatAvailable(29, "B", 1, 1, 1);
       SeatAvailable availableSeat6 = new SeatAvailable(30, "B", 2, 1, 1);
       SeatAvailable availableSeat7 = new SeatAvailable(31, "B", 3, 1, 1);
       SeatAvailable availableSeat8 = new SeatAvailable(32, "B", 4, 1, 1);
       SeatAvailable availableSeat9 = new SeatAvailable(105, "F", 1, 1, 1);
       SeatAvailable availableSeat10 = new SeatAvailable(106, "F", 2, 1, 1);
       SeatAvailable availableSeat11 = new SeatAvailable(107, "F", 3, 1, 1);
       SeatAvailable availableSeat12 = new SeatAvailable(108, "F", 4, 1, 1);
       SeatAvailable availableSeat13 = new SeatAvailable(133, "G", 1, 1, 1);
       SeatAvailable availableSeat14 = new SeatAvailable(134, "G", 2, 1, 1);
       SeatAvailable availableSeat15 = new SeatAvailable(135, "G", 3, 1, 1);
       SeatAvailable availableSeat16 = new SeatAvailable(136, "G", 4, 1, 1);
       availableSeats.add(availableSeat1);
       availableSeats.add(availableSeat2);
       availableSeats.add(availableSeat3);
       availableSeats.add(availableSeat4);
       availableSeats.add(availableSeat5);
       availableSeats.add(availableSeat6);
       availableSeats.add(availableSeat7);
       availableSeats.add(availableSeat8);
       availableSeats.add(availableSeat9);
       availableSeats.add(availableSeat10);
       availableSeats.add(availableSeat11);
       availableSeats.add(availableSeat12);
       availableSeats.add(availableSeat13);
       availableSeats.add(availableSeat14);
       availableSeats.add(availableSeat15);
       availableSeats.add(availableSeat16);


       final List<PassengerResponse>  listFinal = checkinservice.getPassengerAttributesByFlightId(flightId, availableSeats, airplaneId);
       System.out.println("listFinal = " + listFinal);
   }

//   @Test
//    public  void test1(){
//       String[] columnas = {"A", "B"};
//       PassengerResponse passenger = new PassengerResponse(32,"1061727054", "santiago", 17, "colombia", 58, 164, 1, 56);
//       List<SeatAvailable> availableSeats = new ArrayList<SeatAvailable>( );
//       SeatAvailable availableSeat = new SeatAvailable(1, "A", 1, 1, 1, 25, 19);
//       SeatAvailable availableSeat2 = new SeatAvailable(2, "A", 2, 1, 1, 36, 26);
//       SeatAvailable availableSeat3 = new SeatAvailable(3, "A", 3, 1, 1, 47, 38);
//       SeatAvailable availableSeat4 = new SeatAvailable(4, "A", 4, 1, 1, 66, 59);
//       SeatAvailable availableSeat5 = new SeatAvailable(29, "B", 1, 1, 1, 164, 60);
//       SeatAvailable availableSeat6 = new SeatAvailable(30, "B", 2, 1, 1, 109, 18);
//       SeatAvailable availableSeat7 = new SeatAvailable(31, "B", 3, 1, 1, 109, 48);
//       SeatAvailable availableSeat8 = new SeatAvailable(32, "B", 4, 1, 1, 109, 60);
//       availableSeats.add(availableSeat);
//       availableSeats.add(availableSeat2);
//       availableSeats.add(availableSeat3);
//       availableSeats.add(availableSeat4);
//       availableSeats.add(availableSeat5);
//       availableSeats.add(availableSeat6);
//       availableSeats.add(availableSeat7);
//       availableSeats.add(availableSeat8);
//
//       final boolean b = checkinservice.verificarAcompananteMayorEnColumnas(columnas, availableSeat, availableSeats, passenger);
//       System.out.println("b = " + b);
//   }

}
