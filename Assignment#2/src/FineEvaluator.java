// ------------------------------------------------------- // Assignment (#2)
// Written by: Duke Nguyen
// For COMP 248 Section (W) – Winter 2018
// --------------------------------------------------------
/*The following program asks officers the situation that the driver using a cell phone while driving and then display the instruction of their fine, penalty
 * and then ask for the past demerit points they already had, and display the demerit points after the fine*/

import java.util.Scanner;

public class FineEvaluator {

    public static void main(String[] args)
    {
        String answer ;
        int add, intial, fine, total;   //declare variables

        Scanner Input = new Scanner(System.in); //create scanner for console input

        System.out.println("-------****-------****-------****-------****-----****-----");
        System.out.println("\tWelcome to the Fine and Demerit Point Evaluator!");
        System.out.println("\t\tbased on Crazy Nancy's Criteria");
        System.out.println("-------****-------****-------****-------****-----****-----");  // header

        System.out.println("\nWelcome Officer - I need some information before I tell you what the fine and demerit points are.");
        System.out.println("Here are the possible locations");
        System.out.println("\t1 - Driver was stopped on the highway");
        System.out.println("\t2 - In a school zone");
        System.out.println("\t3 - Car is stopped at a Stop sign or traffic light");
        System.out.println("\t4 - None of the above");  //display the possible situations instruction

        //ask for user input
        System.out.print("\nPlease enter the digit corresponding to your case: ");
        int fault = Input.nextInt();

        // check if the situation is number 2
        if (fault == 2)
        {
            System.out.print("\nOfficer, how many months has the driver been driving? ");  //ask for input (the time that driver has been driven)
            int months = Input.nextInt();
            fine = 100;
            //check the months that user just inputed to determine the penalty for the driver
            if (months < 24)
                System.out.println("\n--> Officer, write a ticket for $" + fine + ", take away their driver's license and make arrangements to have the car towed right away.");
            else
            { System.out.print("Last question officer! How many demerit points did the driver have prior to being stopped? ");  //ask for the demerit points that the driver already had
                intial = Input.nextInt();
                add = 4;
                total = intial + add;
                //determine should the officer take away driver's license. then display.
                if (total < 12)
                    System.out.println("\n--> Write a ticket for $" + fine + " and inform the driver that they now have " + total + " demerit points.");
                else
                {
                    System.out.println("\n--->Write a ticket for $" + fine + ". Also the driver has "+ total + " demerit points" );
                    System.out.println("Please take away their driver's license and make arrangements to have the car towed right away");
                }
            }
        }

        else  //if the situation is not in case 2
        // check
        { switch (fault)
        {
            case 1:   //in case officer enter 1
                System.out.print("\nOfficer, is this the driver's 1st offence (answer with y for yes and anything else for no)? "); //ask for the input to determine how many demerit points that driver will receive
                answer = Input.next();
                fine = 80;
                if (answer.equals("y"))  // check to determine the demerit points will be added
                    add = 1;
                else
                    add = 2;
                break;

            case 3:  // in case officer enter 3
                System.out.print("Officer, is the cellphone in question an iPhone (answer with y for yes and anything else for no)? "); // ask for the input to determine how many demerit points will be added and what will be the fine for the driver
                answer = Input.next();
                if (answer.equals("y"))
                {fine = 100;
                    add = 2; }
                else
                {fine = 80;
                    add = 1;  }
                break;

            default: //all other case
                fine = 90;
                add = 3;
        }
            System.out.print("Last question officer! How many demerit points did the driver have prior to being stopped? ");
            intial = Input.nextInt();  //ask officer how many demerit points the driver already had
            total = intial + add;      //total demerit points after the fine
            if (total < 12)
                System.out.println("\n--> Write a ticket for $" + fine + " and inform the driver that they now have " + total + " demerit points."); //display the fine and penalty
            else //in case demerit point >=12 => take away driver's license
            {
                System.out.println("\n--->Write a ticket for $" + fine + ". Also the driver has "+ total + " demerit points" );
                System.out.println("Please take away their driver's license and make arrangements to have the car towed right away");
            }

        }

        System.out.println("\nGood job officer! Crazy Nancy's tells you to keep up the good work!!!!"); //display goodbye banner
        Input.close(); //close method

    }
}
