package com.ss.utopia.entity;

public class Booking_payment {
    private Booking booking;
    private String strip_id;
    private Byte refunded;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getStrip_id() {
        return strip_id;
    }

    public void setStrip_id(String strip_id) {
        this.strip_id = strip_id;
    }

    public Byte getRefunded() {
        return refunded;
    }

    public void setRefunded(Byte refunded) {
        this.refunded = refunded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking_payment that = (Booking_payment) o;

        if (!getBooking().equals(that.getBooking())) return false;
        return getStrip_id().equals(that.getStrip_id());
    }

    @Override
    public int hashCode() {
        int result = getBooking().hashCode();
        result = 31 * result + getStrip_id().hashCode();
        return result;
    }
}
