package com.aktug.airport.demo.service;


import com.aktug.airport.demo.model.Flight;
import com.aktug.airport.demo.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }


    public List<Flight> findAll() {
        List<Flight> airportList = repository.findAll();

        if (airportList.size() > 0) {
            return airportList;
        } else {
            return new ArrayList<Flight>();
        }
    }

    public Flight save(Flight entity) {
        return repository.save(entity);
    }

    public List<Flight> findAirportByNameContains(String s) {
        return repository.findFlightByAirlineNameContainsIgnoreCase(s);
    }
}
