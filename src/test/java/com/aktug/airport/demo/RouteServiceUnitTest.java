package com.aktug.airport.demo;

import com.aktug.airport.demo.model.Airport;
import com.aktug.airport.demo.model.Route;
import com.aktug.airport.demo.service.RouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceUnitTest {


    @Autowired
    private RouteService routeService;

    @Test
    public void testFindAll() {

        List<Route> airlines = routeService.findAll();
        assertThat(airlines).isNotNull()
                .isNotEmpty()
                .allMatch(p -> p.getFrom().getName()
                        .toLowerCase()
                        .contains("airport"));
    }

    @Test
    public void testSearch() {

        List<Route> routes = routeService.findAirportByNameContains("2");
        assertThat(routes).isNotNull()
                .isNotEmpty()
                .allMatch(p -> p.getFrom().getName()
                        .toLowerCase()
                        .contains("airport"));
    }

    @Test
    public void testCreate() {
        Route route = routeService.save(new Route(new Airport("AIRPORT A"), new Airport("AIRPORT B")));
        assertThat(route).isNotNull();
    }

}