package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.Airplane;
import com.col.pop.san.airline.domain.entity.Flight;
import com.col.pop.san.airline.domain.entity.Passenger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirplaneDAOImpl implements AirplaneDAO {

    private final EntityManager entityManager;

    @Autowired
    public AirplaneDAOImpl(EntityManager entityManager) {
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
    public List<Passenger> getPassengerByName(String name) {
        TypedQuery<Passenger> getPassengersQuery = entityManager
                .createQuery("SELECT p FROM Passenger p WHERE p.name LIKE :data", Passenger.class);
         getPassengersQuery.setParameter("data", name);
         return getPassengersQuery.getResultList();
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

}
