package com.col.pop.san.airline.domain.exceptions;

public class FlightNotFoundException extends RuntimeException{

        private Integer flightId;

        public FlightNotFoundException(Integer flightId) {
            super("No se encontró el vuelo con el ID " + flightId);
            this.flightId = flightId;
        }

        public Integer getFlightId() {
            return flightId;
        }
}


