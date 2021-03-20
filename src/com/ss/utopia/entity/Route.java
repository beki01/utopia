package com.ss.utopia.entity;

public class Route {
    private Integer id;
    private Airport originAirport;
    private Airport destAirport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestAirport() {
        return destAirport;
    }

    public void setDestAirport(Airport destAirport) {
        this.destAirport = destAirport;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        return getId() != null ? getId().equals(route.getId()) : route.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
