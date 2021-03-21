package com.ss.utopia.entity;

public class Flight_seats {
    private Flight flight;
    private Integer seat_quantity;
    private String seat_class;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Integer getSeat_quantity() {
        return seat_quantity;
    }

    public void setSeat_quantity(Integer seat_quantity) {
        this.seat_quantity = seat_quantity;
    }

    public String getSeat_class() {
        return seat_class;
    }

    public void setSeat_class(String seat_class) {
        this.seat_class = seat_class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight_seats that = (Flight_seats) o;

        if (getFlight() != null ? !getFlight().equals(that.getFlight()) : that.getFlight() != null) return false;
        if (getSeat_quantity() != null ? !getSeat_quantity().equals(that.getSeat_quantity()) : that.getSeat_quantity() != null)
            return false;
        return getSeat_class() != null ? getSeat_class().equals(that.getSeat_class()) : that.getSeat_class() == null;
    }

    @Override
    public int hashCode() {
        int result = getFlight() != null ? getFlight().hashCode() : 0;
        result = 31 * result + (getSeat_quantity() != null ? getSeat_quantity().hashCode() : 0);
        result = 31 * result + (getSeat_class() != null ? getSeat_class().hashCode() : 0);
        return result;
    }
}
