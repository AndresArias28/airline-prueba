package com.col.pop.san.airline.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "passenger")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "passenger")
    private List<BoardingPass> boardingsPasses;

    public void add(BoardingPass boardingPassTemp) {
        if (boardingsPasses == null) {
            boardingsPasses = new ArrayList<>();
        }
        boardingsPasses.add(boardingPassTemp);
        boardingPassTemp.setPassenger(this);
    }
}
