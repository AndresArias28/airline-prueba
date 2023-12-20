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
        SeatAvailable asientoaborrar = null;

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
            if (asientoaborrar != null) {//si existe asiento a borrar
                seatAvaliables.remove(asientoaborrar);
                Integer seatIdAsignacion = calcularSeatId(passengerResponseBD, seatAvaliables, resultadosBD, airplaneId);
                passengerRes.setSeatId(seatIdAsignacion);
            } else {
                Integer seatIdAsignacion = calcularSeatId(passengerResponseBD, seatAvaliables, resultadosBD, airplaneId);
                passengerRes.setSeatId(seatIdAsignacion);
                asientoaborrar = seatAvaliables.stream().filter(s -> Objects.equals(s.getSeatId(), seatIdAsignacion)).findFirst().orElse(null);
            }
            passengerResponseList.add(passengerRes);
        }
        return passengerResponseList;
    }

    public Integer calcularSeatId(PassengerResponse passenger, List<SeatAvailable> availableSeats, List<PassengerResponse> resultadosBD, Integer airplaneId) {

        PassengerResponse acompananteAsignado;
        if (passenger.getSeatTypeId() == 1 && airplaneId == 1) {//primera clase y se encuentra en la nave AirNova-660

            Integer idAsignadoMayorEdad;
            List<Integer> filasDisponibles = Arrays.asList(1, 2, 3, 4);
            List<String> columnasAdyacentes = Arrays.asList("A", "B", "F", "G");
            boolean flag;
            if (passenger.getAge() < 18) {

                List<PassengerResponse> acompanantesMayores = new ArrayList<>();
                for (PassengerResponse otroPasajero : resultadosBD) {
                    if (otroPasajero.getPurchaseId().equals(passenger.getPurchaseId()) && otroPasajero.getAge() >= 18) {
                        acompanantesMayores.add(otroPasajero);
                    }
                }
                acompananteAsignado = acompanantesMayores.get(0);

                for (Integer fila : filasDisponibles) {
                    for (String columna : columnasAdyacentes) {
                        for (SeatAvailable seat : availableSeats) {
                            if (Objects.equals(seat.getSeatColumn(), columna) && Objects.equals(seat.getSeatRow(), fila)) {
                                //comparar en la lista disponible, si no está es porque ya se fue asignado, entonces asigna el primer puesto que encuentre
                                idAsignadoMayorEdad = seat.getSeatId();

                                //Encontrar id para el acompañante
                                String columnaAsientoMenorEdad = seat.getSeatColumn();
                                Integer idAux;

                                switch (columnaAsientoMenorEdad) {
                                    case "A" -> {
                                        for (SeatAvailable asientoAcompanante : availableSeats) {
                                            if (asientoAcompanante.getSeatColumn().equals("B") && asientoAcompanante.getSeatRow().equals(seat.getSeatRow())) {
                                                idAux = asientoAcompanante.getSeatId();
                                                acompananteAsignado.setSeatId(idAux);
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
                                //acompananteAsignado.setSeatId(idAux);
                            } else { //no encontro columna o fila disponible, tal ves la lista este vacia
                                throw new NullPointerException("no se encontro columna o fila disponible");
                            }
                        }
                    }
                }

            }else {//ES MAYOR DE EDAD

                if (Objects.equals(passenger.getSeatId(), null)) {//NO TIENE PUESTO ASIGNADO
                    for (Integer fila : filasDisponibles) {
                        for (String columna : columnasAdyacentes) {
                            for (SeatAvailable seat : availableSeats) {
                                if (Objects.equals(seat.getSeatColumn(), columna) && Objects.equals(seat.getSeatRow(), fila)) {
                                    return  seat.getSeatId();
                                }
                            }
                        }
                    }
                    //return idAsignadoMayorEdad;
                }else{
                    return  passenger.getSeatId();
                }
            }
        }
        return null;
    }
}

