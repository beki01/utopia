package com.ss.utopia.entity;

import java.util.List;

public class Airport {
    private String airportCode;
    private String city;
    private List<Route> routes;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        return getAirportCode() != null ? getAirportCode().equals(airport.getAirportCode()) : airport.getAirportCode() == null;
    }

    @Override
    public int hashCode() {
        return getAirportCode() != null ? getAirportCode().hashCode() : 0;
    }
}
