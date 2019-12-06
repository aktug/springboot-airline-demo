package com.aktug.airport.demo.repository;


import com.aktug.airport.demo.model.Airline;
import com.aktug.airport.demo.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    List<Airline> findAll();

    Airline save(Airline entity);

    List<Airline> findAirlineByNameContainsIgnoreCase(String s);
}

