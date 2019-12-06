package com.aktug.airport.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    private Airport from;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    private Airport to;

    public Route() {
    }

    public Route(Airport from, Airport to) {
        this.from = from;
        this.to = to;
    }
}
