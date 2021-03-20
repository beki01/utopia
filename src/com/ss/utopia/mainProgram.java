package com.ss.utopia;

import com.ss.utopia.service.AdminService;

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
    }

    public static void adminOptions(){
        Scanner adminRes = new Scanner(System.in);
        int adminChoice =-1;
        AdminService admin = new AdminService();
        String res;

        while(adminChoice!=3){
            try{
                System.out.println("\nPlease Choose from the following:");
                System.out.println("1) See All Routes \n2) See All Airports\n3) Return to previous screen");
                adminChoice = adminRes.nextInt();

                switch (adminChoice){
                    case 1:
                        System.out.println("Retrieving all Routes.....");
                        res= admin.readAllRoutes();
                        System.out.println(res);
                        break;
                    case 2:
                        System.out.println("Retrieving All Airports....");
                        res = admin.readAllAirports();
                        System.out.println(res);
                        break;
                    case 3:
                        System.out.println("Exiting Admin Interface");
                        break;
                    default:
                        System.out.println("Incorrect value please use the menu to select the correct entry");
                        break;
                }
            }catch (Exception e) {
                String incorrectVal = adminRes.nextLine();
                System.out.println(incorrectVal+" is an invalid number");
                System.out.println("Please use Menu numbers when making selection");
            }
        }


    }
    public static void employeeOptions(){
        Scanner employeeRes = new Scanner(System.in);
        int employeeChoice = -50;

        while(employeeChoice!= 2){
            try{
                System.out.println("\nPlease Choose from the following:");
                System.out.println("1) Enter flights you manage \n2) Return to previous screen");

                employeeChoice = employeeRes.nextInt();

                switch (employeeChoice) {
                    case 1:
                        System.out.println("You've chosen to manage Flights");
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
    public static void travelerOptions(){

    }
}
