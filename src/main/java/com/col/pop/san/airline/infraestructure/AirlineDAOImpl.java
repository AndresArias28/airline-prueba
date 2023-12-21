package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.response.*;
import com.col.pop.san.airline.domain.exceptions.FlightNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
        return jdbcTemplate.queryForObject(sql, new Object[]{data}, new FlightResponseRowMapper(passengersList));

    }

    @Override
    public List<SeatAvailable> getSeatAvailableByAirplaneId(Integer airplaneId) {

        String sql = "SELECT * FROM seat WHERE airplane_id = ?";
        List<SeatAvailable> results = jdbcTemplate.query(sql, new Object[]{airplaneId}, new BeanPropertyRowMapper<>(SeatAvailable.class));

        if (results == null || results.isEmpty()) {
            throw new FlightNotFoundException(airplaneId);
        } else {
            return results;
        }
    }

    @Override
    public Integer getAirplaneIdByFlightId(Integer id) {
        String sql = "SELECT s.airplane_id " +
                "FROM seat s " +
                "JOIN boarding_pass bp ON s.seat_id = bp.seat_id " +
                "JOIN flight f ON bp.flight_id = f.flight_id " +
                "WHERE f.flight_id = ? ";
        Object[] params = { id };
        List<Integer> results = jdbcTemplate.queryForList(sql, Integer.class, params);

        if (!results.isEmpty()) {
            return results.get(0);  // Retorna el primer resultado
        } else {
            throw new FlightNotFoundException(id);
        }
    }
}
