package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.*;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import com.col.pop.san.airline.domain.entity.response.RespuestaPrueba;
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

    @Override
    public List<PassengerResponse> getPassengersClassResponseByFlightId(Integer id) {
        List<Object[]> resultadosBD = getPassengersClassByflightId(id);
        List<PassengerResponse> listaResultados = new ArrayList<PassengerResponse>();

        for (Object[] filaObjetos : resultadosBD) {
            PassengerResponse passengerResponse = new PassengerResponse();
            passengerResponse.setPassengerId((Integer) filaObjetos[0]);
            passengerResponse.setDni((String) filaObjetos[1]);
            passengerResponse.setName((String) filaObjetos[2]);
            passengerResponse.setAge((Integer) filaObjetos[3]);
            passengerResponse.setCountry((String) filaObjetos[4]);
            passengerResponse.setBoardingPassId((Integer) filaObjetos[5]);
            passengerResponse.setPurchaseId((Integer) filaObjetos[6]);
            passengerResponse.setSeatTypeId((Integer) filaObjetos[7]);

            listaResultados.add(passengerResponse);
        }

        return listaResultados;
    }

    @Override
    public List<RespuestaPrueba> get3atributes() {
        List<Object[]> resultadosBD = (List<Object[]>) getPassengers();
        List<RespuestaPrueba> resultadosTransformados = new ArrayList<>();

        for (Object[] fila : resultadosBD) {
            RespuestaPrueba respuesta = new RespuestaPrueba();
            respuesta.setPassengerID((Integer) fila[0]);
            respuesta.setDni((String) fila[1]);
            respuesta.setBoardingPass((Integer) fila[2]);
            resultadosTransformados.add(respuesta);
        }
        return resultadosTransformados;
    }

    private List<Object[]> getPassengersClassByflightId(Integer id) {
        Query query = entityManager.createQuery(
             "SELECT p.passengerId, p.dni, p.name, p.age, p.country, bp.boardingPassId, pu.purchaseId, s.seatTypeId " +
                "FROM Passenger p "+
                     "JOIN FETCH BoardingPass bp ON p.passengerId = bp.passenger.passengerId " +
                     "JOIN FETCH SeatType s ON s.seatTypeId = bp.seatType.seatTypeId " +
                     "JOIN FETCH Purchase pu ON pu.purchaseId = bp.purchase.purchaseId " +
                     "JOIN FETCH Flight f ON bp.flight.flightId = f.flightId " +
                     "WHERE f.flightId  = :data", Passenger.class
        );
        query.setParameter("data", id);
        final List<Object[]> resultList = query.getResultList();
        return resultList;
    }

    public Object getPassengers() {
        Query query =  entityManager
                .createQuery("SELECT p.passengerId, p.dni, bp.boardingPassId FROM Passenger p "+
                                "JOIN FETCH BoardingPass bp " +
                                "ON  p.passengerId = bp.passenger.passengerId "
                                );
        return query.getResultList();
    }
    @Override
    public List<Map<String, Object>> obtenerDatosTransformados(Integer id) {
        List<Object> resultadosBD = (List<Object>) getPassengers();
        List<Map<String, Object>> resultadosTransformados = new ArrayList<>();
        for (Object resultado : resultadosBD) {
            Object[] fila = (Object[]) resultado;
            Map<String, Object> resultadoTransformado = new HashMap<>();
            resultadoTransformado.put("passengerID", fila[0]);
            resultadoTransformado.put("dni", fila[1]);
            resultadoTransformado.put("boardingPass", fila[2]);
            resultadosTransformados.add(resultadoTransformado);
        }
        return resultadosTransformados;
    }
}
