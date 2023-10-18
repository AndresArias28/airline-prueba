package com.col.pop.san.airline.domain.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


public class RespuestaPrueba2 {
    private int passengerId;
    private String dniId;
    private int boardingPassId;
    private int seatId;

    public RespuestaPrueba2() {
    }

    public RespuestaPrueba2(int passengerId, String dniId, int boardingPassId, int seatId) {
        this.passengerId = passengerId;
        this.dniId = dniId;
        this.boardingPassId = boardingPassId;
        this.seatId = seatId;
    }
}