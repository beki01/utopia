package com.ss.utopia.dao;

import com.ss.utopia.entity.Booking;
import com.ss.utopia.entity.Flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Flight_bookingsDAO extends BaseDAO<Flight_bookingsDAO>{
    public Flight_bookingsDAO(Connection conn) {
        super(conn);
    }

    public void addFlightBooking(Booking b, Flight f) throws SQLException{
        save("INSERT INTO flight_bookings VALUES (?, ?) ", new Object[]{ f.getId(),b.getId()});
    }

    @Override
    public List<Flight_bookingsDAO> extractData(ResultSet rs) throws SQLException {
        return null;
    }
}
