package com.ss.utopia.service;


import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminService {
    Util util = new Util();

    public String readAllRoutes() throws SQLException {
        Connection conn = null;

        try{
            conn=util.getConnection();
            RouteDAO rDao = new RouteDAO(conn);
            List<Route> allRoutes = rDao.readAllRoutes();

            allRoutes.forEach(route -> {
                System.out.print("Route Id: "+route.getId()+" ");
                System.out.print("Origin: "+route.getOriginAirport().getAirportCode()+" ");
                System.out.print("Destination: "+route.getDestAirport().getAirportCode()+"\n");
            });

            System.out.println();
            conn.commit();

            return "\nRetrieved Routes successfully";
        }catch (Exception e) {
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not retrieve routes";
        }finally {
            if(conn != null) conn.close();
            System.out.println();
        }
    }

    public String readAllAirports() throws SQLException{
        Connection conn = null;

        try{
            conn = util.getConnection();
            AirportDAO aDao = new AirportDAO(conn);
            List<Airport> allAirports = aDao.readAllAirports();

            allAirports.forEach(airport -> {
                System.out.print("Iata_id: "+airport.getAirportCode()+" ");
                System.out.print("City: "+airport.getCity()+"\n");
            });

            return "\nRetrieved Airports successfully";
        }catch (Exception e){
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not retrieve Airports";
        }
        finally{
            if(conn!=null) conn.close();
        }
    }
}
