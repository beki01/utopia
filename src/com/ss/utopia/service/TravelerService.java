package com.ss.utopia.service;

import com.ss.utopia.dao.*;
import com.ss.utopia.entity.*;

import javax.swing.text.html.StyleSheet;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class TravelerService {
    Util util = new Util();

    public void readTraveler(User traveler) throws SQLException{
        Connection conn = null;
        try{
            conn = util.getConnection();
            UserDao uDao = new UserDao(conn);
            UserRoleDAO urDao = new UserRoleDAO(conn);

            if(traveler.getId()!=null){
                User a = uDao.readUserById(traveler.getId()).get(0);
                UserRole ur = urDao.findRoleById(a.getRole_id().getId()).get(0);
                traveler.setRole_id(ur);
                traveler.setGiven_name(a.getGiven_name());
                traveler.setFamily_name(a.getFamily_name());
                traveler.setUsername(a.getUsername());
                traveler.setEmail(a.getEmail());
                traveler.setPassword(a.getPassword());
                traveler.setPhone(a.getPhone());
            } else return;

            conn.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            traveler.setId(null);
            System.out.println();
        } finally{
            if(conn!=null) conn.close();
        }
    }

    public String bookTicket(User u) throws SQLException{
        Connection conn = null;

        try{
            conn = util.getConnection();
            BookingDAO bDao = new BookingDAO(conn);
            RouteDAO routeDAO = new RouteDAO(conn);
            AirportDAO aDao = new AirportDAO(conn);
            FlightDao fDao = new FlightDao(conn);
            Flight_seatsDAO fsDao = new Flight_seatsDAO(conn);
            PassengerDAO pDAO = new PassengerDAO(conn);
            Booking_userDAO buDao = new Booking_userDAO(conn);
            Booking_paymentDAO bpDao = new Booking_paymentDAO(conn);
            Flight_bookingsDAO fbDAO = new Flight_bookingsDAO(conn);

            Passenger p = new Passenger();
            Booking b = new Booking();

            b.setIs_active(true);
            b.setConfirmation_code(confirmationGeneration());

            bDao.createBooking(b);
            b = bDao.findByConfirmation(b).get(0);

            //select which flight
            Flight f = selectFlightFromCurrent(fDao, routeDAO, aDao);

            //select seat
            String seat = chooseSeat(fsDao, f.getId());

            //create passenger
            addPassenger(b, p,pDAO, seat);

            //create entry on booking user
            buDao.addBooking(b, u);

            //create booking payment
            String stripe = confirmationGeneration();
            bpDao.addPayment(b, stripe, false);

            //create entry on flight bookings
            System.out.println("Flight is: "+ f.getId()+ " "+ f.getRoute().getDestAirport().getAirportCode());

            fbDAO.addFlightBooking(b, f);

            conn.commit();
            return "\nBooked Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not book a ticket";
        } finally {
            if(conn!=null) conn.close();
        }
    }

    public String cancelTicket() throws SQLException{
        Connection conn = null;
        //TODO build Traveler => CancelTicket
        try{
            conn = util.getConnection();


            conn.commit();
            return "\nDeleted Successfully";
        } catch (Exception e){
            e.printStackTrace();
            if(conn !=null) conn.rollback();
            System.out.println();
            return "\nCould not delete the ticket";
        } finally{
            if(conn!=null) conn.close();
        }

    }

    public String confirmationGeneration() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        return (random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());
    }

    public Flight selectFlightFromCurrent(FlightDao fdao, RouteDAO rDao, AirportDAO aDao) throws SQLException {
        Scanner travlerChoice = new Scanner(System.in);

        List<Flight> allFlights = fdao.readAllFlights();
        for(int i =0; i<allFlights.size();i++){
            try{
                System.out.print((i+1)+") ");
             AdminService.displayFlight(rDao,aDao, allFlights.get(i));
            }catch (Exception e) {

                System.out.println("Unable to retrieve flight information for flight id: " + allFlights.get(i).getId());
            }
        }

        int flightChoice = travlerChoice.nextInt()-1;

        return allFlights.get(flightChoice);
    }

    public String chooseSeat(Flight_seatsDAO fsDao, int flightID) throws SQLException {
        Scanner traverChoice =  new Scanner(System.in);
        System.out.println("Please select Seats");

        List<Flight_seats> allFlightSeats = fsDao.readAllSeatTypes();
        List<Flight_seats> availableSeats = fsDao.readAllSeatTypes();

//        System.out.println("available seats size"+availableSeats.size());

        for(int i =0; i<allFlightSeats.size(); i++){
            if((allFlightSeats.get(i).getSeat_quantity()-allFlightSeats.get(i).getPassenger_count())>0 && allFlightSeats.get(i).getFlight().getId()==flightID){
                System.out.println((i+1)+") " + allFlightSeats.get(i).getSeat_class());
            }
        }

        int seatChoice = traverChoice.nextInt();

        return availableSeats.get(seatChoice-1).getSeat_class();

    }

    public void addPassenger(Booking b, Passenger p, PassengerDAO pDao, String seat) throws ParseException, SQLException {
        Scanner traverChoice =  new Scanner(System.in);

        System.out.println("Please enter Passenger's given name");
        String firstName = traverChoice.nextLine();

        System.out.println("Please enter the Passenger's last name");
        String lastName = traverChoice.nextLine();

        System.out.println("Please enter the DOB in the following format 'YYYY-MM-DD'");
        String newTime = (traverChoice.nextLine());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = simpleDateFormat.parse(newTime);

        System.out.println("Please enter the gender of the passenger");
        String gender = traverChoice.nextLine();

        System.out.println("Please enter the full address of the passenger");
        String address = traverChoice.nextLine();

        p.setBooking(b);
        p.setGive_name(firstName);
        p.setFamily_name(lastName);
        p.setDob(dob);
        p.setGender(gender);
        p.setAddress(address);
        p.setSeat_class(seat);

        pDao.createPassenger(p);

    }

}

