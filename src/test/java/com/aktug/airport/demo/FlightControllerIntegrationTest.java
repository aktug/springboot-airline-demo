package com.aktug.airport.demo;

import com.aktug.airport.demo.controller.FlightController;
import com.aktug.airport.demo.model.Airline;
import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.model.Flight;
import com.aktug.airport.demo.model.Route;
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

import java.text.SimpleDateFormat;

import static com.aktug.airport.demo.utils.JsonString.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    FlightController flightController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.flightController).build();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/v1/flights/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].airline.name", is("Test-Airline#1")));
    }

    @Test
    public void testSearch() throws Exception {
        mockMvc.perform(get("/api/v1/flights/?search=airline").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].airline.name", is("Test-Airline#1")));
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

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/flights/")
                .content(asJsonString(flight))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
}