package com.aktug.airport.demo;


import com.aktug.airport.demo.controller.RouteController;
import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.model.Route;
import com.aktug.airport.demo.service.RouteService;
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
public class RouteControllerUnitTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    RouteController routeController;

    @MockBean
    RouteService routeService;

    private List<Route> routes;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.routeController).build();

        Route a = new Route();
        a.setFrom(new Airport("Mock-Airport#1"));
        a.setTo(new Airport("Mock-Airport#2"));

        routes = new ArrayList<>();
        routes.add(a);
    }

    @Test
    public void testGetAll() throws Exception {
        when(routeService.findAll()).thenReturn(routes);
        mockMvc.perform(get("http://localhost:8080/api/v1/routes/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].from.name", is("Mock-Airport#1")));
    }

    @Test
    public void testFind() throws Exception {
        when(routeService.findAirportByNameContains("2")).thenReturn(routes);
        mockMvc.perform(get("http://localhost:8080/api/v1/routes/?search=2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].from.name", is("Mock-Airport#1")));
    }
}

