package com.aktug.airport.demo;


import com.aktug.airport.demo.controller.AirlineController;
import com.aktug.airport.demo.model.Airline;
import com.aktug.airport.demo.service.AirlineService;
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
public class AirlineControllerUnitTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    AirlineController airlineController;

    @MockBean
    AirlineService airlineService;

    private List<Airline> airlines;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.airlineController).build();

        Airline a = new Airline();
        a.setName("Mock-Airline#1");
        Airline b = new Airline();
        b.setName("Mock-Airline#2");

        airlines = new ArrayList<>();
        airlines.add(a);
        airlines.add(b);
    }

    @Test
    public void testGetAll() throws Exception {
        when(airlineService.findAll()).thenReturn(airlines);
        mockMvc.perform(get("http://localhost:8080/api/v1/airlines/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Mock-Airline#1")))
                .andExpect(jsonPath("$[1].name", is("Mock-Airline#2")));
    }

    @Test
    public void testFind() throws Exception {
        when(airlineService.findAirlineByNameContains("2")).thenReturn(airlines);
        mockMvc.perform(get("http://localhost:8080/api/v1/airlines/?search=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Mock-Airline#1")))
                .andExpect(jsonPath("$[1].name", is("Mock-Airline#2")));
    }

}

