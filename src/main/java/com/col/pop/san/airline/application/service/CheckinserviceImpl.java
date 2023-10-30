package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Seat;
import com.col.pop.san.airline.domain.entity.response.FlightData;
import com.col.pop.san.airline.domain.entity.response.FlightResponse;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.infraestructure.AirlineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinserviceImpl implements Checkinservice {

    private final AirlineDAO airlineDAO;

    @Autowired
    public CheckinserviceImpl(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Override
    public List<PassengerResponse> getPassengerAttributesByFlightId(Integer id) {
        List<PassengerResponse> resultadosBD = airlineDAO.getPassengersAtributes(id);
        List<Seat> availableSeatList = airlineDAO.getListOfSeatbyFlightId(id);
        List<PassengerResponse> resultadosTransformados = new ArrayList<>();
        for (PassengerResponse objetoRespuestaBD : resultadosBD) {
            PassengerResponse respuesta = new PassengerResponse();
            respuesta.setPassengerId((Integer) objetoRespuestaBD.getPassengerId());
            respuesta.setDni((String) objetoRespuestaBD.getDni());
            respuesta.setName((String) objetoRespuestaBD.getName());
            respuesta.setAge((Integer) objetoRespuestaBD.getAge());
            respuesta.setCountry((String) objetoRespuestaBD.getCountry());
            respuesta.setBoardingPassId((Integer) objetoRespuestaBD.getBoardingPassId());
            respuesta.setPurchaseId((Integer) objetoRespuestaBD.getPurchaseId());
            respuesta.setSeatTypeId((Integer) objetoRespuestaBD.getSeatTypeId());
            Integer seatIdAsignacion = calcularSeatId(objetoRespuestaBD);
            respuesta.setSeatId(seatIdAsignacion);
            resultadosTransformados.add(respuesta);
        }
        return resultadosTransformados;
    }

    int aux = 0;
    private Integer calcularSeatId(PassengerResponse objectPassengerResponse) {
        aux =   aux + 5;
        return aux;

    }
/*
    public Integer calcularSeatId(PassengerResponse passenger, List<Seat> availableSeats) {
        Integer seatTypeId = passenger.getSeatTypeId();
        String seatColumn = null;
        Integer seatRow = null;

        // Lógica para asignar asiento según las reglas de negocio
        // 1. Asignar el asiento según el tipo de asiento (seatTypeId)
        // 2. Asignar al lado de un acompañante mayor de edad
        // 3. Asignar asientos juntos o cercanos
        // 4. Asignar asientos en la misma fila con columnas pegadas (A, B, D, E, etc.)

        // Implementa tu lógica aquí y asigna el valor de seatColumn y seatRow

        // Ejemplo de asignación de asiento:
        seatColumn = "A";  // Columna del asiento
        seatRow = 20;     // Fila del asiento

        // Encuentra un asiento disponible que cumpla con las condiciones
        for (Seat seat : availableSeats) {
            if (seat.getSeatType().getId() == seatTypeId
                    && seat.getColumn().equals(seatColumn)
                    && Math.abs(seat.getRow() - seatRow) <= 1) {
                // Asignar el asiento si cumple con las condiciones
                return seat.getId();
            }
        }

        // Si no se encuentra un asiento que cumpla las condiciones, maneja el caso de error apropiadamente.
        // Puedes lanzar una excepción o devolver un valor especial según tu necesidad.

        return null;  // Devolver null si no se puede asignar un asiento válido
    }*/

    public Integer calcularSeatId(PassengerResponse passenger, List<Seat> availableSeats) {
        Integer seatTypeId = passenger.getSeatTypeId();

        // Filtrar los asientos disponibles por tipo de asiento
        List<Seat> filteredSeats = availableSeats.stream()
                .filter(seat -> seat.getSeatType().getSeatTypeId() == seatTypeId)
                .collect(Collectors.toList());

        for (Seat seat : filteredSeats) {
            // Verificar si el asiento es adecuado para el pasajero según las reglas de negocio
            if (esAdecuadoParaPasajero(seat, passenger, availableSeats)) {
                return seat.getSeatId();
            }
        }

        // Si no se encuentra un asiento adecuado, puedes manejarlo como consideres apropiado.
        // Puedes lanzar una excepción o devolver un valor especial según tus necesidades.

        return null;  // Devolver null si no se puede asignar un asiento válido
    }

    private boolean esAdecuadoParaPasajero(Seat seat, PassengerResponse passenger, List<Seat> availableSeats) {
        // Implementa aquí las reglas de negocio para verificar si el asiento es adecuado para el pasajero.
        // Esto puede incluir verificar si el pasajero es menor de edad y está junto a un acompañante,
        // si los asientos están juntos o cercanos, etc.

        // Ejemplo de reglas de negocio:
        // - El pasajero es menor de edad y se encuentra junto a un acompañante mayor de edad.
        // - Los asientos están juntos o cercanos.

        // Puedes implementar estas reglas en detalle en esta función.

        return true;  // Devuelve true si el asiento cumple con las reglas de negocio
    }

    @Override
    public FlightData getResponseFlight(Integer id, List<PassengerResponse> passengersList) {
       return airlineDAO.getFlightsAtributes(id, passengersList);
    }

    @Override
    public FlightResponse generateFlightResponse(Integer id, FlightData flightData) {
        FlightResponse fr = new FlightResponse();
        fr.setCode(200);
        fr.setData(flightData);
        return fr;

    }
}
