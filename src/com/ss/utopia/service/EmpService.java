package com.ss.utopia.service;

import com.ss.utopia.dao.FlightDao;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDao;
import com.ss.utopia.dao.UserRoleDAO;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;
import com.ss.utopia.entity.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpService {
    Util util = new Util();

    public void readEmployee(User emp) throws SQLException{
        Connection conn = null;

        try {
            conn = util.getConnection();
//            User e = new User();
            UserDao uDao = new UserDao(conn);
            UserRoleDAO urDao = new UserRoleDAO(conn);

            if(emp.getId()!=null){
                User a = uDao.readUserById(emp.getId()).get(0);
                UserRole ur = urDao.findRoleById(a.getRole_id().getId()).get(0);
                emp.setRole_id(ur);
                emp.setGiven_name(a.getGiven_name());
                emp.setFamily_name(a.getFamily_name());
                emp.setUsername(a.getUsername());
                emp.setEmail(a.getEmail());
                emp.setPassword(a.getPassword());
                emp.setPhone(a.getPhone());
            } else {
                return;
            }

            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            emp.setId(null);
            System.out.println();
        }finally {
            if(conn!=null) conn.close();
        }
    }

    public String readFlightsByEmployee(User u) throws SQLException{
        Connection conn = null;

        try {
            conn = util.getConnection();
            FlightDao fDao = new FlightDao(conn);
            RouteDAO rDao = new RouteDAO(conn);

            List<Flight> allFlightsByEmployee = fDao.readFlightsByEmployee(u);

            allFlightsByEmployee.forEach(flight -> {
                try {
                    Route route = rDao.readRouteById(flight.getRoute()).get(0);
                    flight.setRoute(route);
                    System.out.println(flight.getId()+") "+flight.getRoute().getOriginAirport()+" ==> "+
                            flight.getRoute().getDestAirport());

                } catch (SQLException throwables) {
                    System.out.println("Routes for flights were not retrievable");
                }
            });

            conn.commit();
            return "\nRetrieved Flights by Employee successfully";
        }catch (Exception e){
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not retrieve Flights by Employee";
        } finally {
            if(conn!=null) conn.close();
        }
    }
}
