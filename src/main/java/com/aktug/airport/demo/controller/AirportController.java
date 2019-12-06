package com.aktug.airport.demo.controller;


import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.service.AirportService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airports")
@Api(value = "Airports", tags = { "Airports" })
public class AirportController {

    private final AirportService service;

    public AirportController(AirportService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Airport> getAll(@RequestParam Optional<String> search) {

        if (search.isPresent()) {
            return service.findAirportByNameContains(search.get());
        }

        return service.findAll();
    }

    @PostMapping("/")
    public Airport save(@RequestBody Airport airport) {
        return service.save(airport);
    }

}
