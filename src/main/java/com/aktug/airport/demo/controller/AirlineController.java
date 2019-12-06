package com.aktug.airport.demo.controller;


import com.aktug.airport.demo.model.Airline;
import com.aktug.airport.demo.service.AirlineService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/airlines")
@Api(value = "Airlines", tags = { "Airlines" })
public class AirlineController {

    private final AirlineService service;

    public AirlineController(AirlineService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Airline> getAll(@RequestParam Optional<String> search) {

        if (search.isPresent()) {
            return service.findAirlineByNameContains(search.get());
        }

        return service.findAll();
    }

    @PostMapping("/")
    public Airline save(@RequestBody Airline airline) {
        return service.save(airline);
    }

}
