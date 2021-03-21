package com.ss.utopia.entity;

public class Flight_bookings {
    private Flight flight;
    private Booking booking;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight_bookings that = (Flight_bookings) o;

        if (!getFlight().equals(that.getFlight())) return false;
        return getBooking().equals(that.getBooking());
    }

    @Override
    public int hashCode() {
        int result = getFlight().hashCode();
        result = 31 * result + getBooking().hashCode();
        return result;
    }
}
