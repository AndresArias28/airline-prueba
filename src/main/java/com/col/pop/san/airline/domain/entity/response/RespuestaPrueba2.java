package com.col.pop.san.airline.domain.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RespuestaPrueba2 {
    private Integer passengerId;
    private String dniId;
    private Integer boardingPassId;
    private Integer seatId;

    public RespuestaPrueba2() {
    }

    public RespuestaPrueba2(Integer passengerId, String dniId, Integer boardingPassId, Integer seatId) {
        this.passengerId = passengerId;
        this.dniId = dniId;
        this.boardingPassId = boardingPassId;
        this.seatId = seatId;
    }
}