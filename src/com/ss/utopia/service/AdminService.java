package com.ss.utopia.service;


import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDao;
import com.ss.utopia.dao.UserRoleDAO;
import com.ss.utopia.entity.Airport;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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
            conn.commit();
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

    public String addAirport() throws SQLException{
        Connection conn = null;

        try{
            conn = util.getConnection();
            Scanner userInfo = new Scanner(System.in);
            AirportDAO aDao = new AirportDAO(conn);
            Airport a = new Airport();
//
            newAirportPrompts(userInfo, a);

            aDao.addAirport(a);

            conn.commit();
            return "\nSuccessfully created new Airport";

        }catch (Exception e){
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not add new Airport...";
        } finally{
            if(conn!=null) conn.close();
        }
    }

    public String deleteAirport() throws SQLException{
        Connection conn = null;

        try {
            conn = util.getConnection();
            Scanner userInput = new Scanner(System.in);
            AirportDAO aDao = new AirportDAO(conn);

            aDao.readAllAirports();

            System.out.println("Please enter the Iata_code of the airport you would like to delete");
            readAllAirports();
            System.out.println();
            String aCode = userInput.nextLine();

            Airport a = new Airport();
            a.setAirportCode(aCode);

            Airport b = aDao.readAirportsByCode(a).get(0);

            aDao.deleteAirport(b);

            conn.commit();
            return "\nSuccessfully deleted Airport";
        }catch (Exception e) {
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not delete Airport...";
        }finally {
            if(conn!=null) conn.close();
        }
    }

    public String updateAirports() throws SQLException{
        Connection conn= null;

        try{
            conn = util.getConnection();
            Scanner userInfo = new Scanner(System.in);
            AirportDAO aDao = new AirportDAO(conn);

//
            System.out.println("Please enter the Iata_code of the airport you would like to Update");
            readAllAirports();
            System.out.println();
            String aCode = userInfo.nextLine();
            Airport a = new Airport();
            a.setAirportCode(aCode.toUpperCase(Locale.ROOT));

            System.out.println("aCode in Update airport: " +aCode + "Airport code:"+a.getAirportCode());

            Airport b = aDao.readAirportsByCode(a).get(0);
            updateAirportPrompts(userInfo,b);
            aDao.updateAirport(b);

            conn.commit();

            return "\nSuccessfully updated Airport...";
        }catch(Exception e){
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not update Airport...";
        } finally {
            if(conn!=null) conn.close();
        }
    }

    public String readAllUsers() throws SQLException{
        Connection conn= null;
        try{
            conn = util.getConnection();
            UserDao uDao = new UserDao(conn);
            List<User> allUsers = uDao.readAllUsers();
            for (User u : allUsers) {
                System.out.print("\nUserId: "+u.getId()+" Username: "+u.getUsername()+" Name: "+ u.getGiven_name() +" "+ u.getGiven_name());
            }
            conn.commit();
            return "\nRetrieved users successfully";
        } catch (Exception e){
            if(conn !=null) conn.rollback();
            System.out.println();
//            e.printStackTrace();

            return "\nCould not retrieve All Users";

        } finally {
            if(conn!=null) conn.close();
        }
    }

    public String readAllEmployeeUsers() throws SQLException{
        Connection conn = null;

        try{
            conn = util.getConnection();
            UserDao uDao = new UserDao(conn);
            List<User> employeeList = uDao.readUserByRole(2);
            for (User u : employeeList) {
                System.out.print("\nId: "+u.getId()+" Name: "+ u.getGiven_name() +" "+ u.getFamily_name());
            }
            conn.commit();
            return "\nRetrieved Employees successfully";
        }catch (Exception e){
            if(conn !=null) conn.rollback();
            System.out.println();
//            e.printStackTrace();

            return "\nCould not retrieve Employees from Users";
        } finally {
            if(conn!=null) conn.close();
        }
    }

    public String readAllTravelersUsers() throws SQLException {
        Connection conn= null;

        try{
            conn = util.getConnection();
            UserDao uDao = new UserDao(conn);
            List<User> employeeList = uDao.readUserByRole(3);
            for (User u : employeeList) {
                System.out.print("\nUserId: "+u.getId()+" Username: "+u.getUsername()+" Name: "+ u.getGiven_name() +" "+ u.getGiven_name());
            }
            conn.commit();
            return "\nRetrieved Travelers successfully";
        }catch(Exception e){
            if(conn !=null) conn.rollback();
            System.out.println();
//            e.printStackTrace();

            return "\nCould not retrieve Travelers from Users";
        }finally{
            if(conn!=null) conn.close();
        }
    }

    public String addUser(String userType) throws SQLException{
        Connection conn= null;
        try {
            conn= util.getConnection();
            Scanner userInfo = new Scanner(System.in);
            UserRoleDAO urDao = new UserRoleDAO(conn);
            UserDao uDao = new UserDao(conn);
            User u = new User();

            //setRoleId to first find the role of string that was passed in
            u.setRole_id(urDao.findRole(userType).get(0));

            newUserPrompts(userInfo, u);

            uDao.addUser(u);
            conn.commit();
            return "\nSuccessfully created new user";
        }catch (Exception e){
            if(conn !=null) conn.rollback();
            System.out.println();
//            e.printStackTrace();

            return "\nCould not create new user";
        } finally {
            if(conn!=null) conn.close();
        }
    }

    public String updateUsers(String userType, Integer id) throws SQLException {
        Connection conn = null;

        try{
            conn= util.getConnection();
            Scanner userInfo = new Scanner(System.in);
            UserDao uDao = new UserDao(conn);
            User u = uDao.findUserById(id).get(0);

            updateUserPrompts(userInfo, u);
            uDao.updateUser(u);
//            System.out.println("First name after update: "+updated.getGiven_name());
            conn.commit();

            return "\nSuccessfully updated "+userType;
        } catch (Exception e){
            if(conn !=null) conn.rollback();
            System.out.println();
            e.printStackTrace();

            return "\nCould not update "+userType;
        } finally {
            if(conn!=null) conn.close();
        }
    }

    public String deleteUser(String userType, Integer id) throws SQLException{
        Connection conn = null;

        try{
            conn= util.getConnection();

            UserDao uDao = new UserDao(conn);

            uDao.deleteUser(id);

            conn.commit();
            return "\nSuccessfully deleted "+userType;
        }catch (Exception e) {
            if(conn !=null) conn.rollback();
            System.out.println();
            e.printStackTrace();

            return "\nCould not delete "+userType;
        } finally {
            if(conn!=null) conn.close();
        }
    }

    public static void newAirportPrompts(Scanner airportInfo, Airport a){
        System.out.println("Please enter the 3 char Iata Id: ");
        String iata_id = airportInfo.nextLine();
        System.out.println("Please enter the city of the aiport");
        String city = airportInfo.nextLine();

        a.setAirportCode(iata_id.toUpperCase(Locale.ROOT));
        a.setCity(city);
    }

    public static void updateAirportPrompts(Scanner airportInfo, Airport a){
        System.out.println("If you do not want to change the value please enter N/A");
        System.out.println("Please enter the city of the aiport");
        String city = airportInfo.nextLine();
        if(!city.equalsIgnoreCase("N/A")) a.setCity(city);

    }

    public static void newUserPrompts(Scanner userInfo, User u) {
        System.out.println("Please enter user First name: ");
        String first = userInfo.nextLine();
        System.out.println("Please enter user Last name: ");
        String last = userInfo.nextLine();
        System.out.println("Please enter user username: ");
        String username = userInfo.nextLine();
        System.out.println("Please enter user email: ");
        String email = userInfo.nextLine();
        System.out.println("Please enter user password: ");
        String password = userInfo.nextLine();
        System.out.println("Please enter user phone: ");
        String phone = userInfo.nextLine();

        u.setGiven_name(first);
        u.setFamily_name(last);
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setPhone(phone);
    }

    public static void updateUserPrompts(Scanner userInfo, User u){

       System.out.println("If you do not want to change the value please enter N/A");
        System.out.println("Please enter user First name: ");
        String first = userInfo.nextLine();
         if(!first.equalsIgnoreCase("N/A") )u.setGiven_name(first);
        System.out.println("Please enter user Last name: ");
        String last = userInfo.nextLine();
        if(!last.equalsIgnoreCase("N/A") )u.setFamily_name(last);
        System.out.println("Please enter user username: ");
        String username = userInfo.nextLine();
        if(!username.equalsIgnoreCase("N/A") )u.setUsername(username);
        System.out.println("Please enter user email: ");
        String email = userInfo.nextLine();
        if(!email.equalsIgnoreCase("N/A") )u.setEmail(email);

        System.out.println("Please enter user password: ");
        String password = userInfo.nextLine();
        if(!password.equalsIgnoreCase("N/A") )u.setPassword(password);

        System.out.println("Please enter user phone: ");
        String phone = userInfo.nextLine();
        if(!phone.equalsIgnoreCase("N/A") )u.setPhone(phone);

    }

}
