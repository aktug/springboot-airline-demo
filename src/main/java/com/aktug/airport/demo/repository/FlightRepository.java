package com.aktug.airport.demo.repository;


import com.aktug.airport.demo.model.Flight;
import com.aktug.airport.demo.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAll();

    Flight save(Flight entity);

    List<Flight> findFlightByAirlineNameContainsIgnoreCase(String s);
}

