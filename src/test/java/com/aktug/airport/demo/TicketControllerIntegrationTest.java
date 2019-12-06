package com.aktug.airport.demo;

import com.aktug.airport.demo.controller.TicketController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    TicketController ticketController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.ticketController).build();
    }

    @Test
    public void testBuyTicket() throws Exception {
        mockMvc.perform(post("/api/v1/tickets/buy/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketPrice", is(50)));
    }

    @Test
    public void testSearch() throws Exception {
        mockMvc.perform(get("/api/v1/tickets/?search=VGUUWRRK").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancel() throws Exception {
        mockMvc.perform(post("/api/v1/tickets/cancel/VGUUWRRK").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}