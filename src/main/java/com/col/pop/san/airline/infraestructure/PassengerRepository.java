package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.Passenger;
import com.col.pop.san.airline.domain.entity.response.PassengerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    @Query(name = "find_passengerRepo", nativeQuery = true)
    List<PassengerResponse> getPassengersByflightId(@Param("flightId") Integer flightId);

}
