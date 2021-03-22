package com.ss.utopia.service;


import com.ss.utopia.dao.*;
import com.ss.utopia.entity.*;
import com.sun.source.tree.TryTree;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public String addFlight() throws SQLException{
        Connection conn = null;
        Scanner flightDetails = new Scanner(System.in);

        try{
            conn = util.getConnection();

            RouteDAO rDao = new RouteDAO(conn);
            AirportDAO aDao = new AirportDAO(conn);
            UserDao uDao = new UserDao(conn);
            FlightDao fDao = new FlightDao(conn);
            AirplaneDAO apDao = new AirplaneDAO(conn);

            Flight f = new Flight();

            //Choose Departure airport
            System.out.println("Please choose from the following departure airports OR enter -1 to enter a new airport");
            Airport departure_airport = chooseAirport(aDao);

//            System.out.println("Departure airport: "+ departure_airport.getAirportCode());

            //Choose Arrival airport
            System.out.println("Please choose from the following arrival airports OR enter -1 to enter a new airport");
            Airport arrival_airport = chooseAirport(aDao);

            //Bundle into Route
            Route r = new Route();
            r.setOriginAirport(departure_airport);
            r.setDestAirport(arrival_airport);
//            System.out.println("Route in create Flight is: "+ r.getDestAirport());


            List<Route> isRoute = rDao.isRoute(r);
            if(isRoute.size()<=0) {
                rDao.addRoute(r);
                isRoute = rDao.isRoute(r);
            };
            r = isRoute.get(0);

            //Choose Airplane
            Airplane a = chooseAirplane(apDao);

            //choose managing user
            User u = chooseUser(uDao);

            //# of reserved seats
            System.out.println("Please enter the number of reserved seats");
            int reserved = flightDetails.nextInt();

            //seat_price
            System.out.println("Please enter the base price of  seats");
            double priceSeats = flightDetails.nextDouble();

            //Choose Departure/Arrival
            List<Flight> allFlights = fDao.readAllFlights();
            Date departure = chooseFlightDepartureTimes(fDao, allFlights);
            Date Arrival = chooseFlightArrivalTimes(fDao, allFlights);

            f.setRoute(r);
            f.setAirplane_id(a);
            f.setManaging_user(u);
            f.setDeparture_time(departure);
            f.setArrival_time(Arrival);
            f.setReserved_seats(reserved);
            f.setSeat_price(priceSeats);

            fDao.addFlight(f);

            conn.commit();
            return "\nSuccesfully created new Flight";
        }catch (Exception e){
            if(conn !=null) conn.rollback();
            System.out.println();
//            e.printStackTrace();

            return "\nCould not create new flight";
        }finally {
            if(conn!=null) conn.close();
        }
    }

    public String updateFlight() throws SQLException{
        Connection conn = null;
        Scanner flightDetails = new Scanner(System.in);


        try{
            conn = util.getConnection();
            FlightDao fDao = new FlightDao(conn);
            RouteDAO rDao = new RouteDAO(conn);
            AirportDAO aDao = new AirportDAO(conn);
            AirplaneDAO airDao = new AirplaneDAO(conn);
            UserDao uDao = new UserDao(conn);

            System.out.println("Please select which flight you would like to update");
            List<Flight> allFlights = fDao.readAllFlights();
            allFlights.forEach(flight -> {
                try{
                    displayFlight(rDao, aDao, flight);

                } catch (Exception e){
                    System.out.println("Unable to retrieve flight information for flight id: " + flight.getId());
                }
            });

            int flightChoice = flightDetails.nextInt();

            Flight f = allFlights.get(flightChoice-1);

            System.out.println("What would you like to change?\n" +
                    "1) Route\n" +
                    "2) Airplane\n" +
                    "3) Managing User\n" +
                    "4) Departure time\n" +
                    "5) Arrival Time\n" +
                    "6) Reserved Seats\n" +
                    "7) Seat Price");
            int userChoice = flightDetails.nextInt();

            switch (userChoice){
                case 1:
                    System.out.println("Please choose the new Route");
                    List<Route> routes= rDao.readAllRoutes();
                    for(int i=0; i<routes.size(); i++) {
                        System.out.println((i+1)+") "+ routes.get(i).getOriginAirport().getAirportCode()+" => "+routes.get(i).getDestAirport().getAirportCode());
                    }

                    int newRoute = flightDetails.nextInt();

                    f.setRoute(routes.get(newRoute-1));
                    break;
                case 2:
                    System.out.println("Please choose new airplane");
                    List<Airplane> airplanes = airDao.readAllAirplanes();
                    for(int i=0; i<airplanes.size(); i++) {
                        System.out.println((i+1)+") Max Capacity"+ airplanes.get(i).getAirplane_type().getMax_capacity()+" => ");
                    }

                    int newAirplane =(flightDetails.nextInt()-1);

                    f.setAirplane_id(airplanes.get(newAirplane-1));
                    break;
                case 3:
                    System.out.println("Please choose new User");
                    List<User> users = uDao.readUserByRole(2);
                    for(int i=0; i<users.size(); i++) {
                        System.out.println((i+1)+") "+ users.get(i).getGiven_name()+" "+ users.get(i).getFamily_name());
                    }

                    int newManager = flightDetails.nextInt()-1;

                    f.setManaging_user(users.get(newManager));
                    break;
                case 4:
                    flightDetails.nextLine();
                    System.out.println("Please Enter new Departure time in the following format 'YYYY-MM-DD HH:MM:SS' ");
                    String newTime = (flightDetails.nextLine());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    simpleDateFormat.parse(newTime);

                    Date newDeparture = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newTime);
                    f.setDeparture_time(newDeparture);
                    break;
                case 5:
                    flightDetails.nextLine();
                    System.out.println("Please Enter new Arrival time in the following format 'YYYY-MM-DD HH:MM:SS' ");
                    String newArrival = (flightDetails.nextLine());
                    SimpleDateFormat simpleDateFormatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    simpleDateFormatTwo.parse(newArrival);

                    Date newArrivalTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newArrival);
                    f.setArrival_time(newArrivalTime);
                    break;
                case 6:
                    System.out.println("Please enter the new number of reserved seats");
                    int reserved = flightDetails.nextInt();
                    f.setReserved_seats(reserved);
                    break;
                case 7:
                    System.out.println("Please enter the new price of seats");
                    Double price = flightDetails.nextDouble();
                    f.setSeat_price(price);
                    break;
                default:
                    break;

            }

            fDao.updateFlight(f);

            conn.commit();
            return "\nSuccesfully updated Flight";
        }catch (Exception e) {
            if(conn !=null) conn.rollback();
            System.out.println();
            e.printStackTrace();

            return "\nCould not update flight";
        }finally{
            if(conn!=null) conn.close();
        }

    }

    public String deleteFlight() throws SQLException{
        Connection conn = null;
        try{
            conn = util.getConnection();
            FlightDao fDao = new FlightDao(conn);
            RouteDAO rDao = new RouteDAO(conn);
            AirportDAO aDao = new AirportDAO(conn);
            Scanner flightIndex = new Scanner(System.in);

            List<Flight> allFlights = fDao.readAllFlights();
            allFlights.forEach(flight -> {
                try{
                    displayFlight(rDao, aDao, flight);

                } catch (Exception e){
                    System.out.println("Unable to retrieve flight information for flight id: " + flight.getId());
                }

                System.out.print("Flight Id: " + flight.getId() +" ");
            });

            int userChoice = flightIndex.nextInt()-1;

            fDao.deleteFlight(allFlights.get(userChoice));

            conn.commit();
            return "\nSuccessfully deleted flight";
        } catch (Exception e){
            if(conn !=null) conn.rollback();
            System.out.println();
//            e.printStackTrace();

            return "\nCould not delete flight";
        } finally{
            if(conn!=null) conn.close();
        }

    }

    public String readFlights()throws SQLException{
        Connection conn = null;

        try{
            conn = util.getConnection();
            FlightDao fDao = new FlightDao(conn);
            RouteDAO rDao = new RouteDAO(conn);
            AirportDAO aDao = new AirportDAO(conn);

            List<Flight> allFlights = fDao.readAllFlights();

            allFlights.forEach(flight -> {
                try{
                    displayFlight(rDao, aDao, flight);

                } catch (Exception e){
                    System.out.println("Unable to retrieve flight information for flight id: " + flight.getId());
                }
            });

            conn.commit();
            return "\nRetrieved Flights successfully";
        }catch (Exception e){
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not retrieve Flights";
        }
        finally{
            if(conn!=null) conn.close();
        }
    }

    static void displayFlight(RouteDAO rDao, AirportDAO aDao, Flight flight) throws SQLException {
        Route r = rDao.readRouteById(flight.getRoute()).get(0);
        Airport a = aDao.readAirportsByCode(r.getOriginAirport()).get(0);
        Airport b = aDao.readAirportsByCode(r.getDestAirport()).get(0);

        r.setOriginAirport(a);
        r.setDestAirport(b);

        flight.setRoute(r);
        System.out.println("Flight Id: " + flight.getId());
        System.out.println("Departure city: " + flight.getRoute().getOriginAirport().getCity()
                +" Departure Airport: "+flight.getRoute().getOriginAirport().getAirportCode());
        System.out.println("Arrival city: " + flight.getRoute().getDestAirport().getCity()
                + " Departure Airport: " + flight.getRoute().getDestAirport().getAirportCode());
        System.out.println("Departure Date: " + flight.getDeparture_time() + "Arrival Date: " + flight.getArrival_time());
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

    public static Airplane chooseAirplane (AirplaneDAO airDao) throws SQLException {
        Scanner userChoice = new Scanner(System.in);

        List<Airplane> allAirplanes = airDao.readAllAirplanes();

        System.out.println("Please choose from the following airplanes ");

        for(int i=0; i<allAirplanes.size(); i++){
            System.out.println((i+1)+") " + "Airplane Id: " + allAirplanes.get(i).getId()+ " Airplane Capacity: " + allAirplanes.get(i).getAirplane_type().getMax_capacity());
        }

        int aIndex = userChoice.nextInt()-1;

        return  allAirplanes.get(aIndex);
    }

    public static User chooseUser (UserDao uDao) throws SQLException{
        Scanner userChoice = new Scanner(System.in);

        List<User> allUsers = uDao.readAllUsers();

        System.out.println("Please choose from the following as the managing employee ");

        for(int i=0; i<allUsers.size(); i++){
            System.out.println((i+1)+") " + "User Id: " + allUsers.get(i).getId()+ " User Name: " +allUsers.get(i).getGiven_name()+" "+ allUsers.get(i).getFamily_name());
        }

        int aIndex = userChoice.nextInt()-1;

        return allUsers.get(aIndex);

    }

    public static Airport chooseAirport(AirportDAO aDao)throws SQLException{
        Scanner userChoice = new Scanner(System.in);

        List<Airport> allAirports = aDao.readAllAirports();

        for(int i = 0; i<allAirports.size(); i++){
            String aCode = allAirports.get(i).getAirportCode();

            System.out.println((i+1)+") " + aCode);
        }

        int aIndex = userChoice.nextInt();
        userChoice.nextLine();

        if(aIndex<0){
            Airport a = new Airport();

            System.out.println("Please Enter Iata Code");
            String aCode = userChoice.nextLine();
            System.out.println("Please Enter City");
            String city = userChoice.nextLine();

            a.setAirportCode(aCode);
            a.setCity(city);

            aDao.addAirport(a);

            return a;
        }

//        System.out.println("airport Chosen: "+ allAirports.get(aIndex-1).getCity());

        return allAirports.get(aIndex-1);

    }

    public static Date chooseFlightDepartureTimes(FlightDao fDao, List<Flight> allFlights ) throws SQLException, ParseException {
        Scanner userChoice = new Scanner(System.in);
        System.out.println("Please choose a departure time");
        if(allFlights.size()>0){
            for(int i = 0; i<allFlights.size(); i++){
                System.out.println((i+1)+") "+ allFlights.get(i).getDeparture_time());
            }
        }else{
            System.out.println("No previous departure times found");
        }

        System.out.println("Enter -1 to enter your own time");

        int choice = userChoice.nextInt()-1;

        System.out.println("Userchoice at line 592: " + choice);
        userChoice.nextLine();

        if(choice<0){
            System.out.println("Please enter new date/time in the following format 'YYYY-MM-DD HH:MM:SS'");
            String newTime = (userChoice.nextLine());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.parse(newTime);

            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newTime);
        }

        return allFlights.get(choice).getDeparture_time();
    }

    public static Date chooseFlightArrivalTimes(FlightDao fDao, List<Flight> allFlights) throws SQLException, ParseException {

        Scanner userChoice = new Scanner(System.in);


        System.out.println("Please choose an arrvial date");
        for(int i = 0; i<allFlights.size(); i++){
            System.out.println((i+1)+") "+ allFlights.get(i).getArrival_time());
        }
        System.out.println("Enter -1 to enter your own time");

        int choice = userChoice.nextInt()-1;
        userChoice.nextLine();

        if(choice<0){
            System.out.println("Please enter new date/time in the following format 'YYYY-MM-DD HH:MM:SS'");
            String newTime = (userChoice.nextLine());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.parse(newTime);

            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(newTime);
        }

        return allFlights.get(choice).getArrival_time();
    }

}
