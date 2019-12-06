package com.aktug.airport.demo;

import com.aktug.airport.demo.model.Airline;
import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.model.Flight;
import com.aktug.airport.demo.model.Route;
import com.aktug.airport.demo.service.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightServiceUnitTest {


    @Autowired
    private FlightService flightService;

    @Test
    public void testFindAll() {

        List<Flight> airlines = flightService.findAll();
        assertThat(airlines).isNotNull()
                .isNotEmpty()
                .allMatch(p -> p.getAirline().getName()
                        .toLowerCase()
                        .contains("airline"));
    }

    @Test
    public void testSearch() {

        List<Flight> routes = flightService.findAirportByNameContains("airline");
        assertThat(routes).isNotNull()
                .isNotEmpty()
                .allMatch(p -> p.getAirline().getName()
                        .toLowerCase()
                        .contains("airline"));
    }

    @Test
    public void testCreate() throws Exception {
        Flight flight = new Flight();
        flight.setCurrentPrice(100L);
        flight.setAirline(new Airline("Mock-Airline#1"));
        flight.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-06-12 12:00:00"));
        flight.setQuota(10);

        Airport airport1 = new Airport("Mock-Airport#1");
        Airport airport2 = new Airport("Mock-Airport#2");
        Route r1 = new Route(airport1, airport2);
        flight.setRoute(r1);
        flight = flightService.save(flight);
        assertThat(flight).isNotNull();
    }

}