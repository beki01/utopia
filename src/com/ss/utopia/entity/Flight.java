package com.ss.utopia.entity;

import java.util.Date;
import java.util.List;

public class Flight {
    private Integer id;
    private Route route;
    private Airplane airplane_id;
    private User managing_user;
    private Date departure_time;
    private Integer reserved_seats;
    private Double seat_price;
    private List<Flight_bookings> flight_bookings;
    private List<Flight_seats> flight_seats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane_id() {
        return airplane_id;
    }

    public void setAirplane_id(Airplane airplane_id) {
        this.airplane_id = airplane_id;
    }

    public User getManaging_user() {
        return managing_user;
    }

    public void setManaging_user(User managing_user) {
        this.managing_user = managing_user;
    }

    public Date getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Date departure_time) {
        this.departure_time = departure_time;
    }

    public Integer getReserved_seats() {
        return reserved_seats;
    }

    public void setReserved_seats(Integer reserved_seats) {
        this.reserved_seats = reserved_seats;
    }

    public Double getSeat_price() {
        return seat_price;
    }

    public void setSeat_price(Double seat_price) {
        this.seat_price = seat_price;
    }

    public List<Flight_bookings> getFlight_bookings() {
        return flight_bookings;
    }

    public void setFlight_bookings(List<Flight_bookings> flight_bookings) {
        this.flight_bookings = flight_bookings;
    }

    public List<Flight_seats> getFlight_seats() {
        return flight_seats;
    }

    public void setFlight_seats(List<Flight_seats> flight_seats) {
        this.flight_seats = flight_seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        return getId() != null ? getId().equals(flight.getId()) : flight.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}