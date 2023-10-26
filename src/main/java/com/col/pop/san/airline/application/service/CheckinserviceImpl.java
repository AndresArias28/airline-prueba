package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.domain.entity.response.*;
import com.col.pop.san.airline.infraestructure.AirlineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckinserviceImpl implements Checkinservice {

    private final AirlineDAO airlineDAO;

    @Autowired
    public CheckinserviceImpl(AirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Override
    public List<Passenger> getPassenger() {
        return airlineDAO.getPassengerList();
    }

   /* @Override
    public List<Passenger> getPassengersByflightId(Integer id) {
        return airlineDAO.getPassengersByflightId(id);
    }

    @Override
    public List<PassengerResponse> getPassengersClassResponseByFlightId(Integer id) {
        return airlineDAO.getPassengersClassResponseByFlightId(id);
    }*/

    @Override
    public List<RespuestaPrueba> get3atributes() {
        return airlineDAO.get3atributes();
    }

    @Override
    public List<RespuestaPrueba> get3atributes(Integer id) {
        List<RespuestaPrueba> resultadosBD = airlineDAO.getPassengers(id);
        List<RespuestaPrueba> resultadosTransformados = new ArrayList<>();

        for (RespuestaPrueba fila : resultadosBD) {
            RespuestaPrueba respuesta = new RespuestaPrueba();
            respuesta.setPassengerID((Integer) fila.getPassengerID());
            respuesta.setDni((String) fila.getDni());
            respuesta.setBoardingPass((Integer) fila.getBoardingPass());
            //respuesta.setSeatId(9);
            resultadosTransformados.add(respuesta);
        }
        return resultadosTransformados;
    }

    @Override
    public List<RespuestaPrueba2> get3atributes2(Integer id) {
        List<RespuestaPrueba2> resultadosBD = airlineDAO.getPassengers2(id);
        List<RespuestaPrueba2> resultadosTransformados = new ArrayList<>();

        for (RespuestaPrueba2 objetoRespuestaBD : resultadosBD) {
            RespuestaPrueba2 respuesta = new RespuestaPrueba2();
            respuesta.setPassengerId((Integer) objetoRespuestaBD.getPassengerId());
            respuesta.setDniId((String) objetoRespuestaBD.getDniId());
            respuesta.setBoardingPassId((Integer) objetoRespuestaBD.getBoardingPassId());
            Integer seatIdAsignacion = calcularSeatId3atributes(objetoRespuestaBD);
            respuesta.setSeatId(seatIdAsignacion);
            resultadosTransformados.add(respuesta);
        }
        return resultadosTransformados;
    }

    @Override
    public List<PassengerResponse> getAllatributes(Integer id) {
        List<PassengerResponse> resultadosBD = airlineDAO.getPassengersAll(id);
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

    @Override
    public FlightData getResponse(Integer id) {
        FlightData resultadoBD = airlineDAO.getFlightRes(id);
        FlightData respuesta = new FlightData();
        respuesta.setCode(200);
        respuesta.setFlightId(resultadoBD.getFlightId());
        respuesta.setTakeoffDateTime(resultadoBD.getTakeoffDateTime());
        respuesta.setTakeoffAirport(resultadoBD.getTakeoffAirport());
        respuesta.setLandingDateTime(resultadoBD.getLandingDateTime());
        respuesta.setLandingAirport(resultadoBD.getLandingAirport());
        respuesta.setAirplaneId(resultadoBD.getAirplaneId());
        respuesta.setPassengers(getAllatributes(id));

        return respuesta;
    }

    @Override
    public FlightData getResponseFlight(Integer id, List<PassengerResponse> passengersList) {
       return airlineDAO.getFlights(id, passengersList);
    }

    Integer aux = 0;

    private Integer calcularSeatId(PassengerResponse objectPassengerResponse) {
        aux = aux + 3;
        return aux;

    }


    private Integer calcularSeatId3atributes(RespuestaPrueba2 objectPassengerResponse) {
        aux = aux + 3;
        return aux;

    }


}
