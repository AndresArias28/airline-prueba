package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.*;
import com.col.pop.san.airline.domain.entity.response.*;
import com.col.pop.san.airline.domain.exceptions.FlightNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public List<PassengerResponse> getPassengersAtributes(Integer id) {
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
    public FlightData getFlightsAtributes(Integer data, List<PassengerResponse> passengersList) throws FlightNotFoundException {
        String sql = "SELECT f.flight_id, f.takeoff_date_time, f.takeoff_airport, f.landing_date_time, landing_airport, ai.airplane_id " +
                "FROM flight f " +
                "JOIN airplane ai ON f.airplane_id = ai.airplane_id " +
                "WHERE f.flight_id = ? ";
        Object[] params = { data };
        List<FlightData> results =  jdbcTemplate.query(sql, params, new TuEntidadRowMapper(passengersList));
        if (!results.isEmpty()) {
            return results.get(0);  // Retorna el primer resultado
        } else {
            throw new FlightNotFoundException(data);
        }
    }

    @Override
    public List<Seat> getListOfSeatbyFlightId(Integer id) {

        return null;
    }




   /*    public List<RespuestaPrueba> getPassengers(Integer id) {
        TypedQuery<RespuestaPrueba> query =  entityManager
                .createQuery("SELECT NEW com.col.pop.san.airline.domain.entity.response.RespuestaPrueba(p.passengerId, p.dni, bp.boardingPassId) FROM Passenger p "+
                        "JOIN p.boardingsPasses bp " +
                        "JOIN bp.flight f " +
                        "WHERE f.flightId  = :data", RespuestaPrueba.class
                );
        query.setParameter("data", id);
        return query.getResultList();
        //OTRA FORMA LARGA PARA LA CONSULTA JPA
             .createQuery("SELECT p.passengerId, p.dni, bp.boardingPassId FROM Passenger p "+
                        "JOIN BoardingPass bp ON p.passengerId = bp.passenger.passengerId " +
                        "JOIN Flight f ON bp.flight.flightId = f.flightId " +
                        "WHERE f.flightId  = :data", Passenger.class
                );
    }*/

}
