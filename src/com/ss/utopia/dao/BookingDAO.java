package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Booking_payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO extends BaseDAO<Booking>{
    public BookingDAO(Connection conn) {
        super(conn);
    }

    public void createBooking(Booking b) throws SQLException{
        save("INSERT INTO booking (is_active, confirmation_code) VALUES (?, ?) ",
                new Object[]{b.getIs_active(), b.getConfirmation_code() });
    }

    public void deleteBooking(Booking b) throws SQLException{
        save("DELETE FROM booking WHERE id=? ", new Object[]{b.getId()});
    }

    public void updateBooking(Booking b )throws SQLException{
        save("UPDATE booking SET (is_active = ? , confirmation_code=? ) WHERE id=? ",
                new Object[]{b.getIs_active(), b.getConfirmation_code(), b.getId()});
    }

    public void cancelBooking(Booking b, Booking_payment bp) throws SQLException{
        save("UPDATE booking SET (is_active = ? ) WHERE id=? ",
                new Object[]{b.getIs_active(), b.getId()});
        save("UPDATE booking_payment SET (is_refunded = ?) where id=? ",
                new Object[]{bp.getRefunded(), b.getId()});
    }

    public List<Booking> readBookings( Booking b) throws SQLException{
       return read("SELECT * FROM booking", new Object[]{});
    }
//    public List<Booking> readBy

    public List<Booking> findByConfirmation(Booking b) throws SQLException{
        return read("SELECT * FROM booking WHERE confirmation_code = ?", new Object[]{b.getConfirmation_code()});
    }

    @Override
    public List<Booking> extractData(ResultSet rs) throws SQLException {
        List<Booking> allBookings = new ArrayList<>();

        while(rs.next()){
            Booking b = new Booking();

            b.setId(rs.getInt("id"));
            b.setIs_active(rs.getBoolean("is_active"));
            b.setConfirmation_code(rs.getString("confirmation_code"));

            allBookings.add(b);

        }
        return allBookings;
    }
}
