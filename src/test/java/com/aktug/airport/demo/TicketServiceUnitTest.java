package com.aktug.airport.demo;

import com.aktug.airport.demo.model.Ticket;
import com.aktug.airport.demo.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceUnitTest {


    @Autowired
    private TicketService ticketService;

    @Test
    public void testBuy() {

        Ticket ticket = ticketService.buy(1l);
        assertThat(ticket).isNotNull().matches(p -> p.getFlight().getId().equals(1l));

    }

    @Test
    public void testSearch() {

        Ticket ticket = ticketService.getTicketByTicketNo("!@$@!$");
        assertThat(ticket).isNull();
    }


}