// ------------------------------------------------------- // Assignment (#1)
// Written by: Duke Nguyen
// For COMP 248 Section (section W ) – Winter 2018
/*Description (question #3)
 * Firstly, declares variables Q1, Q2, Q3, Q4 (char). Declares S1 (string) and assigns it to "Yes"
 * Next, asks user if they ready for the PersonalityTest. If they enter Yes, displays a message and starts the program
 * if they not type Yes, then displays other message and starts the program anyways
 * Asks them the question 1 2 3 4 respectively and lets them answer by type in the number in a scale 1- 10
 * reads their input and assigns it to an integer variable: mark (int)
 * in each question, there are 2 cases can happen: the user enters a number <= 5 or >5
 * In each case, assigns Q1 Q2 Q3 Q4 as the answer for the case (depends on the question)
 * at the end, displays the result and closing banner */
// --------------------------------------------------------
import java.util.Scanner;
public class PersonalityTest
{

    public static void main(String[] args)
    {
        /**
         * Declare variables and Scanner
         */
        char Q1, Q2, Q3, Q4;
        Scanner input = new Scanner(System.in);
        String S1= "Yes";

/**
 * WELCOME MESSAGE
 */
        System.out.println("---------------------------------------------------------");
        System.out.println("\tThe Simple Personality Test!");
        System.out.println("---------------------------------------------------------");
/**
 * ASK FOR READY
 */
        System.out.print("Are you ready for a personality test? (Yes/No) " );
        String answer = input.next();
        if (answer.equals(S1))
            System.out.println("Allright here we go !");
        else
            System.out.println("Allright, well we're going to do it anyways"); //if user did not enter Yes then display the message and force him/her do it anyways !!

        System.out.println("\nAnswer the following questions on a scale from 1-10.");

/**
 * Question no.1
 */
        System.out.println("Q1: How do you get your energy?");
        System.out.println("\t1 -- By spending time alone");
        System.out.println("\t10 -- By spending time with others");
        int mark = input.nextInt();
        if (mark <= 5)
            Q1 = 'I';
        else
            Q1 = 'E';

/**
 * QUESTION no.2
 */
        System.out.println("Q2: How do you see the world & gather information? ");
        System.out.println("\t1 -- In concrete terms");
        System.out.println("\t10 -- In abstract terms");
        mark = input.nextInt();
        if (mark <= 5)
            Q2 = 'S';
        else
            Q2 = 'N';

/**
 * QUESTION no.3
 */
        System.out.println("Q3: How do you make your decisions?" );
        System.out.println("\t1 -- Using my head");
        System.out.println("\t10 -- Using my heart");
        mark = input.nextInt();
        if (mark <= 5)
            Q3 = 'T';
        else
            Q3 = 'F';

/**
 * QUESTION no.4
 */
        System.out.println("Q4: How much do you like to plan?");
        System.out.println("\t1 -- Make plans far in advance");
        System.out.println("\t10 -- Go with the flow");
        mark = input.nextInt();
        if (mark <= 5)
            Q4 = 'J';
        else
            Q4 = 'P';

/**
 * CONCLUSION
 */
        System.out.println("Your personality type is: *" + Q1 + Q2 + Q3 + Q4 + "*");
        System.out.println("based on theory of Isabel Briggs Myers");
        System.out.println("To find out more about that type of personality check out:");
        System.out.println("https://www.truity.com/view/types");  //the reference web
        System.out.println("Hope you had fun! See you next time!");

        input.close();
    }

}
