package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

   // @Query(name = "find_passengerRepo", nativeQuery = true)
    //List<PassengerResponse> getPassengersByflightId(@Param("flightId") Integer flightId);
}
