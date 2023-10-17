package com.col.pop.san.airline.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boarding_pass")
public class BoardingPass {

    @Id
    @Column(name = "boarding_pass_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardingPassId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;

  }
