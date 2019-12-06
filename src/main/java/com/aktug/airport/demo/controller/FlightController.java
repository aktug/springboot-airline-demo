package com.aktug.airport.demo.controller;


import com.aktug.airport.demo.model.Flight;
import com.aktug.airport.demo.service.FlightService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
@Api(value = "Flight", tags = { "Flight" })
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Flight> getAll(@RequestParam Optional<String> search) {

        if (search.isPresent()) {
            return service.findAirportByNameContains(search.get());
        }

        return service.findAll();
    }

    @PostMapping("/")
    public Flight save(@RequestBody Flight flight) {
        return service.save(flight);
    }

}
