package com.aktug.airport.demo.controller;


import com.aktug.airport.demo.model.Route;
import com.aktug.airport.demo.service.RouteService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/routes")
@Api(value = "Routes", tags = { "Routes" })
public class RouteController {

    private final RouteService service;

    public RouteController(RouteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Route> getAll(@RequestParam Optional<String> search) {

        if (search.isPresent()) {
            return service.findAirportByNameContains(search.get());
        }

        return service.findAll();
    }

    @PostMapping("/")
    public Route save(@RequestBody Route route) {
        return service.save(route);
    }

}
