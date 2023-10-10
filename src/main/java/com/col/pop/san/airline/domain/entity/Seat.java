package com.col.pop.san.airline.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "seat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "seat_column")
    private String seatColumn;

    @Column(name = "seat_row")
    private int seatRow;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seat_type_id")//RELACIONAR LA LLAVE FORANEA DE LA TABLA setType
    private SeatType seatType;

    @JsonIgnore//campo deberia ser ignorado durante el ´proceso de serialiacion
    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seat")
    private List<BoardingPass> BoardingPasses;



}

