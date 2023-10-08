package com.col.pop.san.airline.domain.entity;

import jakarta.persistence.*;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Integer id;

    @Column(name = "purchase_date")
    private Integer purchaseDate;
}
