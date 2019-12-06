package com.aktug.airport.demo.service;


import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    private final AirportRepository repository;

    public AirportService(AirportRepository repository) {
        this.repository = repository;
    }


    public List<Airport> findAll() {
        List<Airport> airportList = repository.findAll();

        if (airportList.size() > 0) {
            return airportList;
        } else {
            return new ArrayList<Airport>();
        }
    }

    public Airport save(Airport entity) {
        return repository.save(entity);
    }

    public List<Airport> findAirportByNameContains(String s) {
        return repository.findAirportByNameContainsIgnoreCase(s);
    }
}
