package com.aktug.airport.demo;

import com.aktug.airport.demo.model.Airline;
import com.aktug.airport.demo.service.AirlineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AirlineServiceUnitTest {


    @Autowired
    private AirlineService airlineService;

    @Test
    public void testFindAll() {

        List<Airline> airlines = airlineService.findAll();
        assertThat(airlines).isNotNull()
                .isNotEmpty()
                .allMatch(p -> p.getName()
                        .toLowerCase()
                        .contains("airline"));
    }

    @Test
    public void testSearch() {

        List<Airline> airlines = airlineService.findAirlineByNameContains("2");
        assertThat(airlines).isNotNull()
                .isNotEmpty()
                .allMatch(p -> p.getName()
                        .toLowerCase()
                        .contains("airline"));
    }


    @Test
    public void testCreate() {
        Airline airline = airlineService.save(new Airline("TEST AIRLINE"));
        assertThat(airline).isNotNull();
    }

}