package com.aktug.airport.demo.service;


import com.aktug.airport.demo.model.Route;
import com.aktug.airport.demo.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    private final RouteRepository repository;

    public RouteService(RouteRepository repository) {
        this.repository = repository;
    }


    public List<Route> findAll() {
        List<Route> airportList = repository.findAll();

        if (airportList.size() > 0) {
            return airportList;
        } else {
            return new ArrayList<Route>();
        }
    }

    public Route save(Route entity) {
        return repository.save(entity);
    }

    public List<Route> findAirportByNameContains(String s) {
        return repository.findRouteByToNameContainsIgnoreCase(s);
    }
}
