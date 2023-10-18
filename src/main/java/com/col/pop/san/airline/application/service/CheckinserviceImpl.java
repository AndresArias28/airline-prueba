package com.col.pop.san.airline.application.service;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba;
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
            resultadosTransformados.add(respuesta);
        }
        return resultadosTransformados;
    }

    public List<Passenger> orderPassengersByAge(){
        return getPassenger();
    }





}
