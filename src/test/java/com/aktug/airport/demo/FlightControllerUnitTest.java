package com.aktug.airport.demo;


import com.aktug.airport.demo.controller.FlightController;
import com.aktug.airport.demo.model.Airline;
import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.model.Flight;
import com.aktug.airport.demo.model.Route;
import com.aktug.airport.demo.service.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightControllerUnitTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    FlightController flightController;

    @MockBean
    FlightService flightService;

    private List<Flight> flights;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.flightController).build();

        Flight flight = new Flight();
        flight.setCurrentPrice(100L);
        flight.setAirline(new Airline("Mock-Airline#1"));
        flight.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-06-12 12:00:00"));
        flight.setQuota(10);

        Airport airport1 = new Airport("Mock-Airport#1");
        Airport airport2 = new Airport("Mock-Airport#2");
        Route r1 = new Route(airport1, airport2);
        flight.setRoute(r1);

        flights = new ArrayList<>();
        flights.add(flight);
    }

    @Test
    public void testGetAll() throws Exception {
        when(flightService.findAll()).thenReturn(flights);
        mockMvc.perform(get("http://localhost:8080/api/v1/flights/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].route.from.name", is("Mock-Airport#1")))
                .andExpect(jsonPath("$[0].currentPrice", is(100)));
    }

    @Test
    public void testFind() throws Exception {
        when(flightService.findAirportByNameContains("Airline")).thenReturn(flights);
        mockMvc.perform(get("http://localhost:8080/api/v1/flights/?search=Airline").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].route.from.name", is("Mock-Airport#1")))
                .andExpect(jsonPath("$[0].currentPrice", is(100)));
    }
}

