package com.aktug.airport.demo.service;


import com.aktug.airport.demo.exception.ResourceNotFoundException;
import com.aktug.airport.demo.model.Flight;
import com.aktug.airport.demo.model.Ticket;
import com.aktug.airport.demo.repository.FlightRepository;
import com.aktug.airport.demo.repository.TicketRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository repository;
    private final FlightRepository flightRepository;

    public TicketService(TicketRepository repository, FlightRepository flightRepository) {
        this.repository = repository;
        this.flightRepository = flightRepository;
    }

    private String generateFlightNo() {
        String randomFlightNo = RandomStringUtils.random(8, true, false).toUpperCase();
        if (repository.countTicketByTicketNo(randomFlightNo) > 0) {
            return generateFlightNo();
        }
        return randomFlightNo;
    }

    public Ticket buy(Long flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        int flightPassengerCnt = repository.countTicketByFlightIdAndIsCanceledIsFalse(flightId);
        if (flight.getQuota() <= flightPassengerCnt) {
            throw new ResourceNotFoundException("Flight is Full.");
        }


        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setTicketPrice(flight.getCurrentPrice());
        ticket.setTicketNo(generateFlightNo());

        repository.save(ticket);

        int passengerLoad = 100 * flightPassengerCnt / flight.getQuota();
        if (passengerLoad % 10 == 0) {
            flight.setCurrentPrice((long) (flight.getCurrentPrice() * 1.1));
            flightRepository.save(flight);
        }

        return ticket;
    }


    public Ticket save(Ticket entity) {
        return repository.save(entity);
    }

    public Ticket cancel(String ticketNo) {
        Ticket ticket = repository.getTicketByTicketNo(ticketNo);
        if (ticket == null) {
            throw new ResourceNotFoundException("Ticket not found");
        }
        ticket.setIsCanceled(true);
        ticket = repository.save(ticket);

        Flight flight = flightRepository.findById(ticket.getFlight().getId()).orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
        int flightPassengerCnt = repository.countTicketByFlightIdAndIsCanceledIsFalse(ticket.getFlight().getId());

        int passengerLoad = 100 * flightPassengerCnt / flight.getQuota();
        if (passengerLoad % 10 == 0) {
            flight.setCurrentPrice((long) (flight.getCurrentPrice() * 0.9));
            flightRepository.save(flight);
        }

        return ticket;
    }

    public Ticket getTicketByTicketNo(String ticketNo) {
        return repository.getTicketByTicketNo(ticketNo);
    }
}
