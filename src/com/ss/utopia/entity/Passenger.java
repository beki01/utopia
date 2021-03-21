package com.ss.utopia.entity;


import java.util.Date;

public class Passenger {
    private Integer id;
    private Booking booking;
    private String give_name;
    private String family_name;
    private Date dob;
    private String gender;
    private String Address;
    private String seat_class;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getGive_name() {
        return give_name;
    }

    public void setGive_name(String give_name) {
        this.give_name = give_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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

        Passenger passenger = (Passenger) o;

        if (getBooking() != null ? !getBooking().equals(passenger.getBooking()) : passenger.getBooking() != null)
            return false;
        if (getGive_name() != null ? !getGive_name().equals(passenger.getGive_name()) : passenger.getGive_name() != null)
            return false;
        if (getFamily_name() != null ? !getFamily_name().equals(passenger.getFamily_name()) : passenger.getFamily_name() != null)
            return false;
        if (getDob() != null ? !getDob().equals(passenger.getDob()) : passenger.getDob() != null) return false;
        if (getGender() != null ? !getGender().equals(passenger.getGender()) : passenger.getGender() != null)
            return false;
        return getAddress() != null ? getAddress().equals(passenger.getAddress()) : passenger.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getBooking() != null ? getBooking().hashCode() : 0;
        result = 31 * result + (getGive_name() != null ? getGive_name().hashCode() : 0);
        result = 31 * result + (getFamily_name() != null ? getFamily_name().hashCode() : 0);
        result = 31 * result + (getDob() != null ? getDob().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
