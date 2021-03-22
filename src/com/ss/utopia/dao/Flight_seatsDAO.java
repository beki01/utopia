package com.ss.utopia.dao;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Flight_seats;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Flight_seatsDAO extends BaseDAO<Flight_seats> {
    public Flight_seatsDAO(Connection conn) {
        super(conn);
    }

    public List<Flight_seats> readAllSeatTypes() throws SQLException{
        return read("SELECT * FROM flight_seats LEFT JOIN flight ON flight_seats.flight_id = flight.id", new Object[]{});
    }



    @Override
    public List<Flight_seats> extractData(ResultSet rs) throws SQLException {
        List<Flight_seats> allSeats = new ArrayList<>();

        while (rs.next()){
            Flight_seats fs = new Flight_seats();
            Flight f = new Flight();

            f.setId(rs.getInt("flight_id"));
            fs.setFlight(f);
            fs.setSeat_class(rs.getString("seat_class"));
            fs.setSeat_quantity(rs.getInt("seat_quantity"));
            fs.setPassenger_count(rs.getInt("passenger_count"));

            allSeats.add(fs);
        }

        return allSeats;
    }
}
