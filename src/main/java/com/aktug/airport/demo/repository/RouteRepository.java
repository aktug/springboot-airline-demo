package com.aktug.airport.demo.repository;


import com.aktug.airport.demo.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findAll();

    Route save(Route entity);

    List<Route> findRouteByToNameContainsIgnoreCase(String s);
}

