package com.col.pop.san.airline.domain.entity.response;

import lombok.Data;

@Data
public class PassengerResponse {

    private Integer passengerId;

    private String dni;

    private String name;

    private Integer age;

    private String country;

    private Integer boardingPassId;

    private Integer purchaseId;

    private Integer seatTypeId;

    private Integer seatId;

    public PassengerResponse() {

    }

    public PassengerResponse(Integer passengerId, String dni, String name, Integer age, String country, Integer boardingPassId, Integer purchaseId, Integer seatTypeId, Integer seatId) {
        this.passengerId = passengerId;
        this.dni = dni;
        this.name = name;
        this.age = age;
        this.country = country;
        this.boardingPassId = boardingPassId;
        this.purchaseId = purchaseId;
        this.seatTypeId = seatTypeId;
        this.seatId = seatId;
    }
}
