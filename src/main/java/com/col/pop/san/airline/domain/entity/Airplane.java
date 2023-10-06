package com.col.pop.san.airline.domain.entity;


import  jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import java.util.List;
@Entity
@Table(name="airplane")
@Data @NoArgsConstructor @AllArgsConstructor
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id")
    private Integer id;

    @Column(name = "name")
    private  String name;

    //enlazando Seat  y Airplane
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "airplane")//este nombre es el atributo que enlaza desde la entidad Seat
    private List<Seat> seats;

    //ESTA CLASE PERMITE BIDIRECCIONALIDAD ENTRE TABLAS
    public void add(Seat tempSeat){
        if(seats==null){
            seats = new ArrayList<>();
        }
        seats.add(tempSeat);
        tempSeat.setAirplane(this);
    }


}
