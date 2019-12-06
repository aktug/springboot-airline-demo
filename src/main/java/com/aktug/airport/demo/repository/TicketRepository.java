package com.aktug.airport.demo.repository;


import com.aktug.airport.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


    Ticket save(Ticket entity);

    Integer countTicketByTicketNo(String ticketNo);

    Integer countTicketByFlightIdAndIsCanceledIsFalse(Long flightId);

    Ticket getTicketByTicketNo(String ticketNo);
}

