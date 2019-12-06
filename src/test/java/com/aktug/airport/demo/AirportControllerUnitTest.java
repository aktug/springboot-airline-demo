package com.aktug.airport.demo;


import com.aktug.airport.demo.controller.AirportController;
import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.service.AirportService;
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
public class AirportControllerUnitTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    AirportController airportController;

    @MockBean
    AirportService airportService;

    private List<Airport> airports;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.airportController).build();

        Airport a = new Airport();
        a.setName("Mock-Airport#1");
        Airport b = new Airport();
        b.setName("Mock-Airport#2");

        airports = new ArrayList<>();
        airports.add(a);
        airports.add(b);
    }

    @Test
    public void testGetAll() throws Exception {
        when(airportService.findAll()).thenReturn(airports);
        mockMvc.perform(get("http://localhost:8080/api/v1/airports/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Mock-Airport#1")))
                .andExpect(jsonPath("$[1].name", is("Mock-Airport#2")));
    }

    @Test
    public void testFind() throws Exception {
        when(airportService.findAirportByNameContains("2")).thenReturn(airports);
        mockMvc.perform(get("http://localhost:8080/api/v1/airports/?search=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Mock-Airport#1")))
                .andExpect(jsonPath("$[1].name", is("Mock-Airport#2")));
    }
}

