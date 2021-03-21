package com.ss.utopia.dao;

import com.ss.utopia.entity.Airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO extends BaseDAO<Airport> {

    public AirportDAO(Connection conn) {
        super(conn);
    }

    public void addAirport(Airport airport) throws SQLException {
        save("insert into airport values (?, ?)", new Object[] {airport.getAirportCode(), airport.getCity()});
    }

    public void updateAirport(Airport airport) throws SQLException {
        save("update airport set city = ? where iata_id = ?", new Object[] {airport.getCity(), airport.getAirportCode()});
    }

    public void deleteAirport(Airport airport) throws SQLException {
        save("delete from airport where iata_id = ?", new Object[] {airport.getAirportCode()});
    }

    public List<Airport> readAllAirports() throws SQLException {
        return read("select * from airport", null);
    }

    public List<Airport> readAirportsByCode(Airport airport) throws SQLException {
        return read("select * from airport where iata_id = ?", new Object[] {airport.getAirportCode()});
    }

    public List<Airport> extractData(ResultSet rs) throws SQLException {
        List<Airport> airports = new ArrayList<>();
        while(rs.next()) {
            Airport a = new Airport();
            a.setAirportCode(rs.getString("iata_id"));
            a.setCity(rs.getString("city"));
            airports.add(a);
        }
        return airports;
    }


}
