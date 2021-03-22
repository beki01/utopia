package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Booking_payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Booking_paymentDAO extends BaseDAO<Booking_payment> {
    public Booking_paymentDAO(Connection conn) {
        super(conn);
    }

    public void addPayment(Booking b,String stripe, Boolean refund) throws SQLException {
        save("INSERT INTO booking_payment VALUES (?, ?, ?) ", new Object[]{b.getId(), stripe, refund});
    }

    @Override
    public List<Booking_payment> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
