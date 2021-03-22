package com.ss.utopia.dao;

import com.ss.utopia.entity.Airplane;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDao extends BaseDAO<Flight>{

    public FlightDao(Connection conn) {
        super(conn);
    }

    public void addFlight(Flight f) throws SQLException{
        save("INSERT INTO flight (route_id, airplane_id, managing_user, departure_time, arrival_time, reserved_seats, seat_price) VALUES (?,?,?,?,?,?,?)",
                new Object[]{f.getRoute().getId(), f.getAirplane_id().getId(), f.getManaging_user().getId(), f.getDeparture_time(), f.getArrival_time(), f.getReserved_seats(), f.getSeat_price()});
    }

    public List<Flight> readFlightById(Flight flight) throws SQLException {
        return read( "SELECT * FROM flight where id = ? ",
                new Object[] {flight.getId()});
    }

    public List<Flight> readFlightsByEmployee(User user) throws SQLException {
        return read("SELECT * FROM flight WHERE managing_user = ? ",
                new Object[]{user.getId()});
    }

    public List<Flight> readAllFlights() throws SQLException{
        return read("SELECT * FROM flight", new Object[]{});
    }

    public void updateFlight(Flight f) throws SQLException{
        save("UPDATE flight SET route_id =?, airplane_id =? , managing_user=?, departure_time=?, arrival_time=?, reserved_seats=?, seat_price=? WHERE id= ? ",
                new Object[]{f.getRoute().getId(), f.getAirplane_id().getId(), f.getManaging_user().getId(), f.getDeparture_time(), f.getArrival_time(), f.getReserved_seats(), f.getSeat_price(), f.getId()});
    }

    public void deleteFlight(Flight f) throws SQLException{
        save("DELETE FROM flight WHERE id=? ", new Object[]{f.getId()});
    }

    @Override
    public List<Flight> extractData(ResultSet rs) throws SQLException {
        List<Flight> flights = new ArrayList<>();

        while (rs.next()){
            Flight f = new Flight();
            Route r = new Route();
            Airplane a = new Airplane();
            User u = new User();

            a.setId(rs.getInt("airplane_id"));
            r.setId(rs.getInt("route_id"));
            u.setId(rs.getInt("managing_user"));

            f.setId(rs.getInt("id"));
            f.setRoute(r);
            f.setAirplane_id(a);
            f.setManaging_user(u);
            f.setDeparture_time(rs.getDate("departure_time"));
            f.setArrival_time(rs.getDate("arrival_time"));
            f.setReserved_seats(rs.getInt("reserved_seats"));
            f.setSeat_price(rs.getDouble("seat_price"));


            flights.add(f);
        }
        return flights;
    }
}
