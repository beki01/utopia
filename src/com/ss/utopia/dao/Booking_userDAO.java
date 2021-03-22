package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Booking_user;
import com.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Booking_userDAO extends BaseDAO<Booking_user>{
    public Booking_userDAO(Connection conn) {
        super(conn);
    }

    public void addBooking(Booking b, User u) throws SQLException {
        save("INSERT INTO booking_user VALUES (?, ?)", new Object[]{b.getId(), u.getId()});
    }

    @Override
    public List<Booking_user> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
