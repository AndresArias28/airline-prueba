package com.col.pop.san.airline.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "takeoff_date_time")
    private  Integer takeoffDateTime;

    @Column(name = "takeoff_airport")
    private String takeoffAirport;

    @Column(name = "landing_date_time")
    private  Integer landingDateTime;

    @Column(name = "landing_airport")
    private String landingAirport;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;
}
