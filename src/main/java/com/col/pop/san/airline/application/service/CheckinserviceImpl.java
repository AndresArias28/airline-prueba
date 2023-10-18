package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba2;
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

        for (RespuestaPrueba2 fila : resultadosBD) {
            RespuestaPrueba2 respuesta = new RespuestaPrueba2();
            respuesta.setPassengerId((Integer) fila.getPassengerId());
            respuesta.setDniId((String) fila.getDniId());
            respuesta.setBoardingPassId((Integer) fila.getBoardingPassId());
            Integer seatIdAsignacion = calcularseatId(fila);
            respuesta.setSeatId(seatIdAsignacion);
            resultadosTransformados.add(respuesta);
        }
        return resultadosTransformados;
    }
    Integer aux = 0;
    private Integer calcularseatId(RespuestaPrueba2 fila) {
        aux = aux+ 3;
        return aux;

    }

    public Integer asignacionAsiento(){
        int aux =+ 1;
        return aux;
    }

    public List<Passenger> orderPassengersByAge(){
        return getPassenger();
    }





}
