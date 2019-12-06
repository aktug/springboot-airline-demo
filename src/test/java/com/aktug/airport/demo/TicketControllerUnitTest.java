package com.aktug.airport.demo;


import com.aktug.airport.demo.controller.TicketController;
import com.aktug.airport.demo.model.*;
import com.aktug.airport.demo.service.TicketService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketControllerUnitTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    TicketController ticketController;

    @MockBean
    TicketService ticketService;

    private Ticket ticket;
    private Flight flight;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.ticketController).build();

        flight = new Flight();
        flight.setId(99L);
        flight.setCurrentPrice(100L);
        flight.setAirline(new Airline("Mock-Airline#1"));
        flight.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-06-12 12:00:00"));
        flight.setQuota(10);

        Airport airport1 = new Airport("Mock-Airport#1");
        Airport airport2 = new Airport("Mock-Airport#2");
        Route r1 = new Route(airport1, airport2);
        flight.setRoute(r1);

        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setFlight(flight);
        ticket.setIsCanceled(false);
        ticket.setTicketNo("TEST");
        ticket.setTicketPrice(10L);
    }

    @Test
    public void testBuy() throws Exception {
        when(ticketService.buy(1L)).thenReturn(ticket);
        mockMvc.perform(post("http://localhost:8080/api/v1/tickets/buy/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketNo", is("TEST")))
                .andExpect(jsonPath("$.ticketPrice", is(10)));
    }

    @Test
    public void testCancel() throws Exception {
        when(ticketService.cancel("TEST")).thenReturn(ticket);
        mockMvc.perform(post("http://localhost:8080/api/v1/tickets/cancel/TEST").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCanceled", is(false)));
    }

    @Test
    public void testFind() throws Exception {
        when(ticketService.getTicketByTicketNo("TEST")).thenReturn(ticket);
        mockMvc.perform(get("http://localhost:8080/api/v1/tickets/?search=TEST").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketNo", is("TEST")));
    }
}

