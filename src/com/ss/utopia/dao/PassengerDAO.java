package com.ss.utopia.dao;

import com.ss.utopia.entity.Passenger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PassengerDAO extends BaseDAO<Passenger> {
    public PassengerDAO(Connection conn) {
        super(conn);
    }

    public void createPassenger(Passenger p) throws SQLException{
        save("INSERT INTO passenger (booking_id, given_name, family_name, dob, gender, address, seat_class) VALUES (?,?,?,?,?,?,?)",
                new Object[]{p.getBooking().getId(), p.getGive_name(), p.getFamily_name(), p.getDob(), p.getGender(), p.getAddress(), p.getSeat_class()});
    }

    @Override
    public List<Passenger> extractData(ResultSet rs) throws SQLException {
        //TODO Build PassengerDao Extract method;
        return null;
    }
}
