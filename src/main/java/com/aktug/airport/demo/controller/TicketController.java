package com.aktug.airport.demo.controller;


import com.aktug.airport.demo.model.Ticket;
import com.aktug.airport.demo.service.TicketService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
@Api(value = "Tickets", tags = {"Tickets"})
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Ticket getAll(@RequestParam String search) {

        return service.getTicketByTicketNo(search);

    }

    @PostMapping("/cancel/{ticketNo}")
    public Ticket cancel(@PathVariable String ticketNo) {
        return service.cancel(ticketNo);
    }

    @PostMapping("/buy/{flightId}")
    public Ticket buy(@PathVariable long flightId) {
        return service.buy(flightId);
    }

}
