package com.ss.utopia.entity;

import java.util.List;

public class Booking_user {
    private Booking booking;
    private List<User> users;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking_user that = (Booking_user) o;

        return getBooking() != null ? getBooking().equals(that.getBooking()) : that.getBooking() == null;
    }

    @Override
    public int hashCode() {
        return getBooking() != null ? getBooking().hashCode() : 0;
    }
}
