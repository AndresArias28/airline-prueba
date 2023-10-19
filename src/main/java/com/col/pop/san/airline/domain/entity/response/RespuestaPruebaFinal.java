package com.col.pop.san.airline.domain.entity.response;

import lombok.Data;

@Data
public class RespuestaPruebaFinal {
    private int passengerId;
    private String dniId;
    private int boardingPassId;
    private int seatId;

    public RespuestaPruebaFinal() {
    }


}