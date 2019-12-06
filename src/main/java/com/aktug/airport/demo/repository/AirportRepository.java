package com.aktug.airport.demo.repository;


import com.aktug.airport.demo.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findAll();

    Airport save(Airport entity);

    List<Airport> findAirportByNameContainsIgnoreCase(String s);
}

