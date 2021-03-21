package com.ss.utopia.entity;

import java.util.List;

public class Airplane {
    private Integer id;
    private Airplane_type airplane_type;
    private List<Flight> flights;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airplane_type getAirplane_type() {
        return airplane_type;
    }

    public void setAirplane_type(Airplane_type airplane_type) {
        this.airplane_type = airplane_type;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airplane airplane = (Airplane) o;

        return getId() != null ? getId().equals(airplane.getId()) : airplane.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
