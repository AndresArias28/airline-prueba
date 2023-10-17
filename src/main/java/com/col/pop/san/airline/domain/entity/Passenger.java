package com.col.pop.san.airline.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passenger")
/*
@NamedQuery(
        name = "find_passengerRepo",
        query =
                "SELECT NEW com.col.pop.san.airline.domain.entity.response.PassengerResponse( p.passengerId, p.dni, p.name, p.age, p.country, bp.boardingPassId, bp.purchase.purchaseId, bp.seatType.seatTypeId, bp.seat.seatId) " +
                        "FROM Passenger p " +
                        "JOIN FETCH  BoardingPass bp " +
                        "ON p.passengerId = bp.passenger.passengerId " +
                        "JOIN FETCH Flight f ON bp.flight.flightId = f.flightId " +
                        "WHERE f.flightId = :flightId"
)
@SqlResultSetMapping(
        name = "find_passengerRepo",
        classes = @ConstructorResult(
                targetClass = PassengerRepository.class,
                columns = {
                        @ColumnResult(name = "passengerId", type = Integer.class),
                        @ColumnResult(name = "dni", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "age", type = Integer.class),
                        @ColumnResult(name = "country", type = String.class),
                        @ColumnResult(name = "boardingPassId", type = String.class),
                        @ColumnResult(name = "purchaseId", type = Integer.class),
                        @ColumnResult(name = "seatTypeId", type = Integer.class),
                        @ColumnResult(name = "seatId", type = Integer.class),
                        @ColumnResult(name = "passengerId", type = Integer.class)
                }
        )
)*/
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;

    @Column(name = "dni")
    private String dni;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "country")
    private  String country;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "passenger")
    private List<BoardingPass> boardingsPasses;

    public void add(BoardingPass boardingPassTemp) {
        if (boardingsPasses == null) {
            boardingsPasses = new ArrayList<>();
        }
        boardingsPasses.add(boardingPassTemp);
        boardingPassTemp.setPassenger(this);
    }
}
