package com.aktug.airport.demo;

import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.service.AirportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceIntegrationTest {

    @Autowired
    private AirportService airportService;

    @Test
    public void testGetAll() {
        List<Airport> airlines = airportService.findAll();
        assertThat(airlines).isNotNull().isNotEmpty();
    }

    @Test
    public void testSearch() {
        List<Airport> airlines = airportService.findAirportByNameContains("2");
        assertThat(airlines).isNotNull().isNotEmpty();
    }

    @Test
    public void testSearch2() {
        List<Airport> airlines = airportService.findAirportByNameContains("!@%!@$#!^%$");
        assertThat(airlines).isNotNull().isEmpty();
    }

    @Test
    public void testCreate() {
        Airport airport = airportService.save(new Airport("TEST AIRPORT"));
        assertThat(airport).isNotNull();
    }

}