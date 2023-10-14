package com.col.pop.san.airline.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "purchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Integer purchaseId;

    @Column(name = "purchase_date")
    private Integer purchaseDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase")
    private List<BoardingPass> boardingPasses;
}
