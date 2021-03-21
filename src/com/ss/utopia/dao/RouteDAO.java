package com.ss.utopia.dao;

import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO extends BaseDAO<Route>{
    public RouteDAO(Connection conn) {
        super(conn);
    }

    public void addRoute(Route route) throws SQLException {
        save("INSERT INTO route VALUES (?, ?)", new Object[] {route.getOriginAirport(), route.getDestAirport()});
    }

    public void updateRoute(Route route) throws SQLException {
        save("UPDATE route SET origin_id = ?, destination_id=? WHERE id = ? ", new Object[] {route.getOriginAirport(), route.getDestAirport(), route.getId()});
    }

    public void deleteRoute(Route route) throws  SQLException {
        save("DELETE FROM route WHERE id = ?", new Object[] {route.getId()});
    }
    public List<Route> readRouteById(Route r)throws SQLException{
        return read("SELECT * FROM route WHERE id = ?",
                new Object[]{r.getId()});
    }

    public List<Route> readAllRoutes() throws SQLException{
        return read("SELECT * FROM route;", null);
    }

    @Override
    public List<Route> extractData(ResultSet rs) throws  SQLException {
        List<Route> newList = new ArrayList<>();
        while(rs.next()){
            Route r = new Route();
            Airport a = new Airport();
            Airport b = new Airport();

//          System.out.println("First col: " + rs.getInt(1) + " 2nd Col: " + rs.getString(2) + " 3rd Col "+ rs.getString(3));
            a.setAirportCode(rs.getString(2));
            b.setAirportCode(rs.getString(3));

            r.setId(rs.getInt(1));
            r.setOriginAirport(a);
            r.setDestAirport(b);

            newList.add(r);
        }
        return newList;
    }
}
