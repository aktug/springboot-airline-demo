package com.aktug.airport.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Flight {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Airline airline;

    private Integer quota;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    private Route route;

    private Long currentPrice;

    private Date date;

    public Flight() {
    }
}
