package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.response.*;
import com.col.pop.san.airline.infraestructure.AirlineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CheckinserviceImpl implements Checkinservice {

    private final AirlineDAO airlineDAO;

    @Autowired
    public CheckinserviceImpl(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }


    @Override
    public FlightData getResponseFlight(Integer id, List<PassengerResponse> passengersList) {
        return airlineDAO.getFlightsAtributes(id, passengersList);
    }

    @Override
    public Integer getAirplaneIdByFlightId(Integer id) {
        return airlineDAO.getAirplaneIdByFlightId(id);
    }

    @Override
    public List<SeatAvailable> getSeatAvailableByAirplaneId(Integer airplaneId) {
        return airlineDAO.getSeatAvailableByAirplaneId(airplaneId);
    }

    @Override
    public List<PassengerResponse> getPassengerAttributesByFlightId(Integer flightId, List<SeatAvailable> seatAvaliables, Integer airplaneId) {

        List<PassengerResponse> resultadosBD = airlineDAO.getPassengersAtributes(flightId);
        List<PassengerResponse> passengerResponseList = new ArrayList<>();
        SeatAvailable asientoaborrar = new SeatAvailable();
        for (PassengerResponse passengerResponseBD : resultadosBD) {


                PassengerResponse passengerRes = new PassengerResponse();

                passengerRes.setPassengerId(passengerResponseBD.getPassengerId());
                passengerRes.setDni(passengerResponseBD.getDni());
                passengerRes.setName(passengerResponseBD.getName());
                passengerRes.setAge(passengerResponseBD.getAge());
                passengerRes.setCountry(passengerResponseBD.getCountry());
                passengerRes.setBoardingPassId(passengerResponseBD.getBoardingPassId());
                passengerRes.setPurchaseId(passengerResponseBD.getPurchaseId());
                passengerRes.setSeatTypeId(passengerResponseBD.getSeatTypeId());
                if (Objects.equals(asientoaborrar, null)) {//si no existe asiento a borrar
                    Integer seatIdAsignacion = calcularSeatId(passengerResponseBD, seatAvaliables, resultadosBD, airplaneId);
                    passengerRes.setSeatId(seatIdAsignacion);
                    asientoaborrar = seatAvaliables.stream().filter(s -> Objects.equals(s.getSeatId(), seatIdAsignacion)).findFirst().orElse(null);

                } else {
                    seatAvaliables.remove(asientoaborrar);
                    Integer seatIdAsignacion = calcularSeatId(passengerResponseBD, seatAvaliables, resultadosBD, airplaneId);
                    passengerRes.setSeatId(seatIdAsignacion);
                    asientoaborrar = seatAvaliables.stream().filter(s -> Objects.equals(s.getSeatId(), seatIdAsignacion)).findFirst().orElse(null);

                }
                passengerResponseList.add(passengerRes);
        }
        return passengerResponseList;
    }

    public Integer calcularSeatId(PassengerResponse passenger, List<SeatAvailable> availableSeats, List<PassengerResponse> resultadosBD, Integer airplaneId) {

        if (passenger.getSeatTypeId() == 1 && airplaneId == 1) {//primera clase y se encuentra en la nave AirNova-660

            Integer idAsignadoMayorEdad;
            List<Integer> filasDisponibles = Arrays.asList(1, 2, 3, 4);
            List<String> columnasAdyacentes = Arrays.asList("A", "B", "F", "G");
            PassengerResponse acompananteAsignado = new PassengerResponse();
            List<PassengerResponse> acompanantesMayores = new ArrayList<>();

            if (passenger.getAge() < 19) {//menor de edad

                for (PassengerResponse otroPasajero : resultadosBD) {
                    if (otroPasajero.getPurchaseId().equals(passenger.getPurchaseId()) && otroPasajero.getAge() >= 18) {
                        acompanantesMayores.add(otroPasajero);
                    }
                }
                for (PassengerResponse acompanantesMayore : acompanantesMayores) {

                    if (acompanantesMayore.getSeatId() == null) {
                        acompananteAsignado = acompanantesMayore;
                    }
                }

                for (Integer fila : filasDisponibles) {
                    for (String columna : columnasAdyacentes) {
                        for (SeatAvailable seat : availableSeats) {
                            if (Objects.equals(seat.getSeatColumn(), columna) && Objects.equals(seat.getSeatRow(), fila)) {
                                //comparar en la lista disponible, si no está es porque ya se fue asignado, entonces asigna el primer puesto que encuentre
                                idAsignadoMayorEdad = seat.getSeatId();
                                //Encontrar id para el acompañante
                                String columnaAsientoMenorEdad = seat.getSeatColumn();

                                switch (columnaAsientoMenorEdad) {
                                    case "A" -> {
                                        for (SeatAvailable asientoAcompanante : availableSeats) {
                                            if (asientoAcompanante.getSeatColumn().equals("B") && asientoAcompanante.getSeatRow().equals(seat.getSeatRow())) {
                                                acompananteAsignado.setSeatId(asientoAcompanante.getSeatId());
                                            }
                                        }
                                    }
                                    case "B" -> {
                                        for (SeatAvailable asientoAcompanante : availableSeats) {
                                            if (asientoAcompanante.getSeatColumn().equals("A") && asientoAcompanante.getSeatRow().equals(seat.getSeatRow())) {
                                                acompananteAsignado.setSeatId(asientoAcompanante.getSeatId());
                                            }
                                        }
                                    }
                                    case "G" -> {
                                        for (SeatAvailable asientoAcompanante : availableSeats) {
                                            if (asientoAcompanante.getSeatColumn().equals("F") && asientoAcompanante.getSeatRow().equals(seat.getSeatRow())) {
                                                acompananteAsignado.setSeatId(asientoAcompanante.getSeatId());
                                            }
                                        }
                                    }
                                    case "F" -> {
                                        for (SeatAvailable asientoAcompanante : availableSeats) {
                                            if (asientoAcompanante.getSeatColumn().equals("G") && asientoAcompanante.getSeatRow().equals(seat.getSeatRow())) {
                                               acompananteAsignado.setSeatId(asientoAcompanante.getSeatId());
                                            }
                                        }
                                    }
                                    default -> {
                                        System.out.println("no se encontro columna");
                                    }
                                }
                                return  idAsignadoMayorEdad;
                            }
                        }
                    }
                }
            }else {//ES MAYOR DE EDAD
                if(acompananteAsignado.getSeatId() == null) {//NO TIENE PUESTO ASIGNADO
                    for (Integer fila : filasDisponibles) {
                        for (String columna : columnasAdyacentes) {
                            for (SeatAvailable seat : availableSeats) {
                                if (Objects.equals(seat.getSeatColumn(), columna) && Objects.equals(seat.getSeatRow(), fila)) {
                                    return  seat.getSeatId();

                                }
                            }
                        }
                    }

                }else{
                    return  acompananteAsignado.getSeatId();
                }
            }
        }
        return null;
    }
}

