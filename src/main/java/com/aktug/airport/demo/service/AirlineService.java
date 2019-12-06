package com.aktug.airport.demo.service;


import com.aktug.airport.demo.model.Airline;
import com.aktug.airport.demo.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirlineService {

    private final AirlineRepository repository;

    public AirlineService(AirlineRepository repository) {
        this.repository = repository;
    }


    public List<Airline> findAll() {
        List<Airline> airportList = repository.findAll();

        if (airportList.size() > 0) {
            return airportList;
        } else {
            return new ArrayList<Airline>();
        }
    }

    public Airline save(Airline entity) {
        return repository.save(entity);
    }

    public List<Airline> findAirlineByNameContains(String s) {
        return repository.findAirlineByNameContainsIgnoreCase(s);
    }
}
