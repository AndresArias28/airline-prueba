package com.col.pop.san.airline;

import com.col.pop.san.airline.application.service.Checkinservice;
import com.col.pop.san.airline.domain.entity.Passenger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AirlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(Checkinservice checkinservice) {

		return runner -> {
			//List<Passenger> passengerList = checkinservice.getPassengersByflightId(4);
			System.out.println("passengerList = mensaje de prueba con el tamaño de la lista de pasajeros: ");//+ passengerList.size());


			//Passenger p = passengerList.stream().findAny().get();
			//System.out.println("p.getCountry() = " + p.getCountry());

		};
	}


}

