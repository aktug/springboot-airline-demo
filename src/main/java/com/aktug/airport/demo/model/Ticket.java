package com.aktug.airport.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ticket {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;

    private  String ticketNo;

    @ManyToOne(optional = false)
    private  Flight flight;

    private Long ticketPrice;


    private Boolean isCanceled = false;
}
