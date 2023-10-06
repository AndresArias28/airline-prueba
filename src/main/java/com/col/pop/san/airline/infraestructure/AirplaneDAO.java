package com.col.pop.san.airline.infraestructure;

import com.col.pop.san.airline.domain.entity.Airplane;

import java.util.List;

public interface AirplaneDAO {

    List<Airplane> getAirplanes();
}
