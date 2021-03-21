package com.ss.utopia.entity;

import java.util.List;

public class Booking {
    private Integer id;
    private Boolean is_active;
    private String confirmation_code;
    private Booking_agent agent;
    private Booking_user user;
    private Booking_guest guest;
    private Booking_payment payment;
    private List<Flight_bookings> fBookings;
    private List<Passenger> passengers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getConfirmation_code() {
        return confirmation_code;
    }

    public void setConfirmation_code(String confirmation_code) {
        this.confirmation_code = confirmation_code;
    }

    public Booking_agent getAgent() {
        return agent;
    }

    public void setAgent(Booking_agent agent) {
        this.agent = agent;
    }

    public Booking_user getUser() {
        return user;
    }

    public void setUser(Booking_user user) {
        this.user = user;
    }

    public Booking_guest getGuest() {
        return guest;
    }

    public void setGuest(Booking_guest guest) {
        this.guest = guest;
    }

    public Booking_payment getPayment() {
        return payment;
    }

    public void setPayment(Booking_payment payment) {
        this.payment = payment;
    }

    public List<Flight_bookings> getfBookings() {
        return fBookings;
    }

    public void setfBookings(List<Flight_bookings> fBookings) {
        this.fBookings = fBookings;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (!getId().equals(booking.getId())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
