package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.*;
import com.col.pop.san.airline.domain.entity.response.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AirlineDAOImpl implements AirlineDAO {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AirlineDAOImpl(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
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

   /* @Override
    public List<Passenger> getPassengersByflightId(Integer id) {
        TypedQuery<Passenger> getPassengersQuery = entityManager
                .createQuery("SELECT p FROM Passenger p JOIN FETCH BoardingPass bp ON p.passengerId = bp.passenger.passengerId "
                        +"JOIN FETCH Flight f ON bp.flight.flightId = f.flightId "
                        +"WHERE f.flightId  = :data", Passenger.class);
        getPassengersQuery.setParameter("data", id);
        return getPassengersQuery.getResultList();

    }*/

   /* @Override
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
    }*/

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

    public Object getPassengers() {
        Query query =  entityManager
                .createQuery("SELECT p.passengerId, p.dni, bp.boardingPassId FROM Passenger p "+
                        "JOIN FETCH BoardingPass bp " +
                        "ON p.passengerId = bp.passenger.passengerId"
                );
        return query.getResultList();
    }

   /* private List<Object[]> getPassengersClassByflightId(Integer id) {
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
    }*/

    @Override
    public List<RespuestaPrueba> getPassengers(Integer id) {
        TypedQuery<RespuestaPrueba> query =  entityManager
                .createQuery("SELECT NEW com.col.pop.san.airline.domain.entity.response.RespuestaPrueba(p.passengerId, p.dni, bp.boardingPassId) FROM Passenger p "+
                        "JOIN p.boardingsPasses bp " +
                        "JOIN bp.flight f " +
                        "WHERE f.flightId  = :data", RespuestaPrueba.class
                );
        query.setParameter("data", id);
        return query.getResultList();
        //OTRA FORMA LARGA PARA LA CONSULTA JPA
               /* .createQuery("SELECT p.passengerId, p.dni, bp.boardingPassId FROM Passenger p "+
                        "JOIN BoardingPass bp ON p.passengerId = bp.passenger.passengerId " +
                        "JOIN Flight f ON bp.flight.flightId = f.flightId " +
                        "WHERE f.flightId  = :data", Passenger.class
                );*/
    }

    @Override
    public List<RespuestaPrueba2> getPassengers2(Integer id) {
        TypedQuery<RespuestaPrueba2> query =  entityManager
                .createQuery("SELECT NEW com.col.pop.san.airline.domain.entity.response.RespuestaPrueba2(p.passengerId, p.dni, bp.boardingPassId, bp.seat.seatId ) FROM Passenger p " +
                        "JOIN p.boardingsPasses bp " +
                        "JOIN bp.seat " +
                        "JOIN bp.flight f " +
                        "WHERE f.flightId  = :data", RespuestaPrueba2.class
                );
        query.setParameter("data", id);
        return query.getResultList();
        //return null;
    }

    @Override
    public List<PassengerResponse> getPassengersAll(Integer id) {
        TypedQuery<PassengerResponse> query =  entityManager
                .createQuery("SELECT NEW com.col.pop.san.airline.domain.entity.response.PassengerResponse(" +
                        "p.passengerId, p.dni, p.name, p.age, p.country, bp.boardingPassId," +
                        "pu.purchaseId, st.seatTypeId, bp.seat.seatId) " +
                        "FROM Passenger p " +
                        "JOIN p.boardingsPasses bp " +
                        "JOIN bp.seatType st " +
                        "JOIN bp.purchase pu " +
                        "JOIN bp.flight f " +
                        "WHERE f.flightId  = :data", PassengerResponse.class
                );
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public FlightData getFlights(Integer data, List<PassengerResponse> passengersList) {
        String sql = "SELECT f.flight_id, f.takeoff_date_time, f.takeoff_airport, f.landing_date_time, landing_airport, ai.airplane_id " +
                "FROM flight f " +
                "JOIN airplane ai ON f.airplane_id = ai.airplane_id " +
                "WHERE f.flight_id = ? ";
        Object[] params = { data };
        List<FlightData> results =  jdbcTemplate.query(sql, params, new TuEntidadRowMapper(passengersList));
        if (!results.isEmpty()) {
            return results.get(0);  // Retorna el primer resultado
        } else {
            return null;  // Maneja el caso en el que no se encontraron resultados
        }
    }

    @Override
    public FlightResponse getAllResponseByFlight(Integer id, FlightData flightData) {

        return null;
    }


    @Override
   public FlightData getFlightRes(Integer id) {
       try {
           TypedQuery<FlightData> query = entityManager.createQuery(
                   "SELECT NEW com.col.pop.san.airline.domain.entity.response.FlightData(" +
                           "f.flightId, " +
                           "f.takeoffDateTime, " +
                           "f.takeoffAirport, " +
                           "f.landingDateTime, " +
                           "f.landingAirport, " +
                           "ai.airplaneId, " +
                           "(SELECT NEW com.col.pop.san.airline.domain.entity.response.PassengerResponse(" +
                           "p.passengerId, " +
                           "p.dni, " +
                           "p.name, " +
                           "p.age, " +
                           "p.country, " +
                           "bp.boardingPassId, " +
                           "pu.purchaseId, " +
                           "st.seatTypeId, " +
                           "bp.seat.seatId) " +
                           "FROM Passenger p " +
                           "JOIN p.boardingsPasses bp " +
                           "JOIN bp.purchase pu " +
                           "JOIN bp.seat s " +
                           "JOIN bp.seatType st " +
                           "WHERE p.flight.flightId = f.flightId) " +
                           ") " +
                           "FROM Flight f " +
                           "JOIN f.airplane ai " +
                           "WHERE f.flightId = :data", FlightData.class
           );

           query.setParameter("data", id);
           return  query.getSingleResult();
       } catch (NoResultException e) {
           e.printStackTrace();
           return null;
       }
   }




}
