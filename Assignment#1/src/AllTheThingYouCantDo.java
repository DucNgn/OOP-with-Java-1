// ------------------------------------------------------- // Assignment (#1)
// Written by: Duke Nguyen
// For COMP 248 Section (section W ) – Winter 2018
/*Description (question #2)
 * The program displays a welcome message. Then, asks a user his/her name and age. the program reads the input of user and assign it to variables: name, age (integer)
 * next step is determining things users cannot do by "if ..else" method (depends on their age)
 * then display the list of all the things they can not do and closing message at the end*/
// --------------------------------------------------------
import java.util.Scanner;
public class AllTheThingYouCantDo
{

    public static void main(String[] args)
    {
        Scanner keyIn = new Scanner(System.in);

        //WELCOME MESSAGE
        System.out.println("/****************************************************************/");
        System.out.println("/******** All the things you cannot do ... **************/");
        System.out.println("/****************************************************************/");

        /**
         * Ask user about his/her name and age then read the input and assign it to variables: name, age.
         */
        System.out.print("Welcome ! What's your name ? ");
        String name = keyIn.nextLine();

        System.out.print("Nice to meet you " + name + ", how old are you? ");
        int age = keyIn.nextInt();

        /**
         * Determine things user can not do then display the list to screen
         */
        String cantdo;
        if (age < 16)
            cantdo = "you can't drive !";
        else
        { if (age < 18)
            cantdo = "you can't vote !";
        else
        { if (age <25)
            cantdo = "you can't rent a car !";
        else
            cantdo = "you can do anything that's legal !";
        }
        }

        //RESULT
        System.out.println("Well, " + name + " did you know, " + cantdo);

        //Display closing message
        System.out.println("\nThanks, see you next time.");

        keyIn.close();  //CLOSE SCANNER

    }

}
