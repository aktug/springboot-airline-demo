package com.aktug.airport.demo;

import com.aktug.airport.demo.controller.AirportController;
import com.aktug.airport.demo.model.Airline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static com.aktug.airport.demo.utils.JsonString.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AirportControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    AirportController airportController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.airportController).build();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/v1/airports/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Test-Airport#1")));
    }

    @Test
    public void testSearch() throws Exception {
        mockMvc.perform(get("/api/v1/airports/?search=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Test-Airport#2")));
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/airports/")
                .content(asJsonString(new Airline("TEST AIRPORT")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

}