//------------------------------------------------------------------------------
//Assignment: #1
//Written by: Duke Nguyen
//For Comp 248 - WINTER 2018
//Q#1
//------------------------------------------------------------------------------

import java.util.Scanner;

public class SISsystem{

    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);
        /**
         * Welcome message
         */
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                       STUDENT INFORMATION SYSTEM                             ");
        System.out.println("------------------------------------------------------------------------------");

        /**
         * Asking information
         */
        System.out.print("First name: ");
        String fName       = keyIn.nextLine();

        System.out.print("Last name: ");
        String lName       = keyIn.nextLine();

        System.out.print("Major: ");
        String major       = keyIn.nextLine();

        System.out.print("Year: ");
        int year           = keyIn.nextInt();

        System.out.print("ID: ");
        long id            = keyIn.nextLong();

        System.out.print("Login: ");
        String login       = keyIn.nextLine();

        System.out.print("GPA(0.0 - 4.3): ");
        double gpa         = keyIn.nextDouble();

        System.out.println("");

        /**
         * Display student information
         */
        System.out.println(" Your student information:");
        System.out.println("    Login: " + login);
        System.out.println("    ID: " + id);
        System.out.println("    Name: " + lName + ", " + fName);
        System.out.println("    Field and year: " + major + ", " + year + "\n");

        /**
         * Goodbye message
         */
        System.out.println("That's all folks!");

        keyIn.close();  //close Scanner
        ///END OF PROGRAM
    }
}
