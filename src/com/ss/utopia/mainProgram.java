package com.ss.utopia;

import com.ss.utopia.entity.User;
import com.ss.utopia.service.AdminService;
import com.ss.utopia.service.EmpService;

import java.util.Scanner;

public class mainProgram {

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        int userType = -50;

        while (userType != 4) {
            System.out.println("Welcome to the Utopia Airlines Management System. Which category of a user are you?");
            System.out.println("1) Employee \n2) Administrator \n3) Traveler \n4) Quit");
            try {
                userType = user.nextInt();
                switch (userType) {
                    case 1:
                        System.out.println("You are an Employee");
                        employeeOptions();
                        //TODO Build Employee Options
                        break;
                    case 2:
                        System.out.println("You are an Admin");
                        adminOptions();
                        //TODO Build Admin Options
                        break;
                    case 3:
                        System.out.println("You are a Traveler");
                        travelerOptions();
                        //TODO Build Traveler Options
                        break;
                    case 4:
                        System.out.println("GoodBye!");
                        break;
                    default:
                        System.out.println("Entered invalid num. Please try again.");
                        break;
                }

            } catch (Exception e) {
                String incorrectVal = user.nextLine();
                System.out.println(incorrectVal + " is not a valid entry.\n Please use the numbers in front of titles to enter.");
                System.out.println();
            }
            System.out.println();
        }
        user.close();
    }

    public static void adminOptions(){
        Scanner adminRes = new Scanner(System.in);
        Scanner userRes = new Scanner(System.in);
        int adminChoice =-1, userId;
        AdminService admin = new AdminService();
        String res;

        while(adminChoice!=13){
            try{
                System.out.println("\nPlease Choose from the following:");
                System.out.println("1) See All Airports \n" +
                        "2) Update Airports\n" +
                        "3) Delete Airport\n" +
                        "4) Add Airport\n" +
                        "5) See All Employees\n" +
                        "6) See All Travelers\n"+
                        "7) Add Employee\n"+
                        "8) Add Traveler\n"+
                        "9) Update Employee\n"+
                        "10) Update Traveler\n"+
                        "11) Delete Employee\n"+
                        "12) Delete Traveler\n"+
                        "13) Return to previous screen");
                adminChoice = adminRes.nextInt();

                switch (adminChoice){
                    case 1:
                        System.out.println("Retrieving All Airports....\n");
                        res = admin.readAllAirports();
                        System.out.println(res);
                        break;
                    case 2:
                        System.out.println("Update Airports....\n");
                        res = admin.updateAirports();
                        System.out.println(res);
                        break;
                    case 3:
                        System.out.println("Delete Airport....");
                        System.out.println();
                        res = admin.deleteAirport();
                        System.out.println(res);
                        break;
                    case 4:
                        System.out.println("Add Airport....");
                        System.out.println();
                        res = admin.addAirport();
                        System.out.println(res);
                        break;
                    case 5:
                        System.out.println("Retrieving All Employees....");
                        res = admin.readAllEmployeeUsers();
                        System.out.println(res);
                        break;
                    case 6:
                        System.out.println("Retrieving All Travelers....");
                        res = admin.readAllTravelersUsers();
                        System.out.println(res);
                        break;
                    case 7:
                        System.out.println("Add Employee....");
                        res = admin.addUser("employee");
                        System.out.println(res);
                        break;
                    case 8:
                        System.out.println("Add Traveler....");
                        res = admin.addUser("traveler");
                        System.out.println(res);
                        break;
                    case 9:
                        System.out.println("Update Employee...." +
                                "\nPlease indicate enter the id of the employee you would like to update");
                        admin.readAllEmployeeUsers();
                        System.out.println();
                        userId = userRes.nextInt();

                        res = admin.updateUsers("employee", userId);
                        System.out.println(res);
                        break;
                    case 10:
                        System.out.println("Update Traveler...." +
                                "\nPlease indicate enter the id of the Traveler you would like to update");
                        admin.readAllTravelersUsers();
                        System.out.println();
                        userId = userRes.nextInt();

                        res = admin.updateUsers("traveler", userId);
                        System.out.println(res);
                        break;
                    case 11:
                        System.out.println("Delete Employee...." +
                                "\nPlease indicate enter the id of the employee you would like to delete");
                        admin.readAllUsers();
                        System.out.println();
                        userId = userRes.nextInt();

                        res = admin.deleteUser("employee", userId);
                        System.out.println(res);
                        break;
                    case 12:
                        System.out.println("Delete Traveler...." +
                                "\nPlease indicate enter the id of the Traveler you would like to delete");
                        admin.readAllUsers();
                        System.out.println();
                        userId = userRes.nextInt();

                        res = admin.deleteUser("traveler", userId);
                        System.out.println(res);
                        break;
                    case 13:
                        System.out.println("Exiting Admin Interface");
                        break;
                    default:
                        System.out.println("Incorrect value please use the menu to select the correct entry");
                        break;
                }
            }catch (Exception e) {
                String incorrectVal = adminRes.nextLine();
                System.out.println(incorrectVal+" is an invalid number");
                System.out.println("Please use numbers when making selection");
            }
        }


    }

    public static void employeeOptions(){
        Scanner employeeRes = new Scanner(System.in);
        int employeeChoice = -50;
        EmpService emp = new EmpService();
        User employee = new User();
        int employeeId=0;
        String res;

        while(employee.getId()==null || employeeId!=-1){
            try{
                System.out.println("Please enter your Employee Number OR -1 to quit");
                employeeId = employeeRes.nextInt();
                if(employeeId!= -1) {
                    employee.setId(employeeId);
                    emp.readEmployee(employee);
                }

            }catch (Exception e){
                String invalid = employeeRes.nextLine();
                System.out.println(invalid+" is an invalid entry");
                System.out.println("Please use numbers when making selection");
            }

        }


        while(employeeChoice!= 2){
            try{
                System.out.println("\nPlease Choose from the following:");
                System.out.println("1) Enter flights you manage \n2) Return to previous screen");

                employeeChoice = employeeRes.nextInt();

                switch (employeeChoice) {
                    case 1:
                        System.out.println("You've chosen to manage Flights");
                        System.out.println("Please select from the following flights: ");
                        res = emp.readFlightsByEmployee(employee);
                        System.out.println(res);
                        Integer flightNum = employeeRes.nextInt();
                        employeeManageFlights(flightNum, employeeRes);

                        break;
                    case 2:
                        System.out.println("Exiting Employee Interface");
                        break;
                    default:
                        System.out.println("Incorrect value please use the menu to select the correct entry");
                        break;
                }

                System.out.println();
            }catch (Exception e){
                String incorrectVal = employeeRes.nextLine();
                System.out.println(incorrectVal+" is an invalid number");
                System.out.println("Please use Menu numbers when making selection");
            }

        }
    }

    public static void employeeManageFlights(Integer flightNum, Scanner scan){
        System.out.println("What youdl you like to do with this flight? ");
        System.out.println("1) View more Details of Flight \n" +
                "2) Update the details of the flight\n" +
                "3) Add Seats to Flight\n" +
                "4) Return to previous screan");

        Integer action = scan.nextInt();

        //TODO Write EmployeeManageFlights

        switch (action){
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

        }
    }
    public static void travelerOptions(){

    }
}
