package com.col.pop.san.airline.domain.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatAvailable {
    private Integer seatId;
    private String seatColumn;
    private Integer seatRow;
    private Integer seatTypeId;
    private Integer airplaneId;
}
