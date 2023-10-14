package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AirlineDAOImpl implements AirlineDAO {

    private final EntityManager entityManager;

    @Autowired
    public AirlineDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Flight> getFlights() {
        TypedQuery<Flight> query = entityManager
                .createQuery("FROM Flight order by takeoffAirport", Flight.class);
        return query.getResultList();
    }

    @Override
    public List<Flight> getFlightsByAirport(String takeoffAirport) {
        TypedQuery<Flight> query = entityManager
                .createQuery("SELECT f FROM Flight f WHERE f.takeoffAirport LIKE :data", Flight.class);
        query.setParameter("data", takeoffAirport);
        return query.getResultList();
    }

    @Override
    public List<Passenger> getPassengerList() {
        TypedQuery<Passenger> getPassengersQuery = entityManager
                .createQuery("SELECT p FROM Passenger p", Passenger.class);
        // getPassengersQuery.setParameter("data", name);
         return getPassengersQuery.getResultList();
    }

    @Override
    public List<BoardingPass> getBoardingPassById() {
        TypedQuery<BoardingPass> queryGetBoarding = entityManager
                .createQuery("SELECT b FROM BoardingPass b ORDER BY boardingPassId", BoardingPass.class);
        //queryGetBoarding.setParameter("data", id);
        return queryGetBoarding.getResultList();
    }

    @Override
    public List<Flight> getFlightsUsingFetchBy(Integer id) {
        TypedQuery<Flight> queryGetFlight = entityManager.createQuery("SELECT f FROM Flight f JOIN f.BoardingPasses JOIN f.airplane WHERE f.flightId = :data", Flight.class);
        queryGetFlight.setParameter("data", id);
        return queryGetFlight.getResultList();

    }

    @Override
    public List<Seat> getSeats() {
        TypedQuery<Seat> queryGetSeat = entityManager.createQuery("FROM Seat", Seat.class);
        return queryGetSeat.getResultList();
    }



    @Override
    public List<Airplane> getAirplanes() {
        TypedQuery<Airplane> query = entityManager
                .createQuery("FROM Airplane order by name", Airplane.class);
        return query.getResultList();
    }

    @Override
    public Airplane findAirplaneAndFlightsByAirplaneId(Integer id) {
        TypedQuery<Airplane> query = entityManager
                .createQuery("SELECT a FROM Airplane a JOIN FETCH a.flights WHERE a.id = :data", Airplane.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public List<Passenger> getPassengersByflightId(Integer id) {

        TypedQuery<Passenger> getPassengersQuery = entityManager
                .createQuery("SELECT p FROM Passenger p JOIN FETCH BoardingPass bp ON p.passengerId = bp.passenger.passengerId "
                        +"JOIN FETCH Flight f ON bp.flight.flightId = f.flightId "
                        +"WHERE f.flightId  = :data", Passenger.class);
        getPassengersQuery.setParameter("data", id);
        return getPassengersQuery.getResultList();

    }


    public Object getPassengers(Integer id) {
        Query query =  entityManager
                .createQuery("SELECT p.passengerId, p.dni, bp.boardingPassId FROM Passenger p "+
                                "JOIN FETCH BoardingPass bp " +
                                "ON  p.passengerId = bp.passenger.passengerId ");
       // query.setParameter("data", id);
        return query.getResultList();
    }
    @Override
    public List<Map<String, Object>> obtenerDatosTransformados(Integer id) {//preguna
        List<Object> resultadosBD = (List<Object>) getPassengers(id);// toca manejar todo con objetos
        //List<RespuestaPrueba> listaObjetosMapeados = new ArrayList<RespuestaPrueba>();
        List<Map<String, Object>> resultadosTransformados = new ArrayList<>();

        for (Object resultado : resultadosBD) {
            Object[] fila = (Object[]) resultado;
           // RespuestaPrueba respuestaPrueba = new RespuestaPrueba();

          Map<String, Object> resultadoTransformado = new HashMap<>();
           resultadoTransformado.put("passengerID", fila[0]);
            resultadoTransformado.put("dni", fila[1]);
            resultadoTransformado.put("boardingPass", fila[2]);
           /*    respuestaPrueba.setPassengerID((Integer) fila[0]);
            respuestaPrueba.setDni((String) fila   [1]);
            respuestaPrueba.setBoardingPass((Integer) fila[2]);*/

            resultadosTransformados.add(resultadoTransformado);
        }

        return resultadosTransformados;
    }



    //SELECT p.passenger_id AS passengerId, p.dni, p.name, p.age, p.country, bp.boarding_pass_id AS boardingPassId, bp.purchase_id AS purchaseId, bp.seat_type_id AS seatTypeId, bp.seat_id AS seatId FROM Passenger p INNER JOIN BoardingPass bp ON p.passenger_id = bp.passenger_id INNER JOIN Flight f ON bp.flight_id = f.flight_id WHERE f.flight_id = :flightId")

}
