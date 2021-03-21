package com.ss.utopia.entity;

public class Booking_agent {
    private Booking booking;
    private User user;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking_agent that = (Booking_agent) o;

        if (!getBooking().equals(that.getBooking())) return false;
        return getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        int result = getBooking().hashCode();
        result = 31 * result + getUser().hashCode();
        return result;
    }
}
