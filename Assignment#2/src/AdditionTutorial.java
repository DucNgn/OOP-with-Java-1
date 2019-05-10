// ------------------------------------------------------- // Assignment (#2)
// Written by: Duke Nguyen
// For COMP 248 Section (W) – Winter 2018
// --------------------------------------------------------
/*the following program asks user for input 2 number (at most 3 digits) then calculate the multiplier of these 2 numbers
and display to the screen step-by-step*/
import java.util.Scanner;

public class AdditionTutorial {

    public static void main(String[] args)
    {

        String answer;
        //Welcome message
        System.out.println("Welcome to Nancy's Addition Tutorial Program!");
        System.out.println("---------------------------------------------"); //header
        do
        {
            Scanner keyboard = new Scanner(System.in); //create scanner for console input


            System.out.print("\nEnter two numbers with at most 3 digits each, seprated by a space and press enter: "); //ask user input for 2 numbers
            int num1 = keyboard.nextInt();
            int num2 = keyboard.nextInt();

            //assign digits of the first number user entered
            int a, b, c;
            a = num1/100;      //determine the 1st digit by finding the quotient of the division of numb1 to 100
            b = (num1%100)/10; //determine the middle digit by finding the remainder of the division of numb1 to 100, then find the quotient of that remainder divided by 10 => the result is the middle digit. assign it to b
            c = (num1%100)%10; //determine the last digit by finding the remainder of the division of numb1 to 100, then take it and divide it by 10, find the remainder => the result is the last digit. assign it to c

            //assign digits of the second number user entered
            int d, e, f;
            d = num2/100;   //determine the 1st digit by finding the quotient of the division of numb1 to 100
            e= (num2%100)/10;  //determine the middle digit by finding the remainder of the division of numb2 to 100, then find the quotient of that remainder divided by 10 => the result is the middle digit. assign it to e
            f = (num2%100)%10; //determine the last digit by finding the remainder of the division of numb2 to 100, then take it and divide it by 10, find the remainder => the result is the last digit. assign it to f

            //DISPLAY WHAT USER REQUESTED
            System.out.println("\nYou requested the following operation: ");
            System.out.println("\n  num1:\t  " + num1);
            System.out.println("  num2:\t+ " + num2);
            System.out.println("\t-----");

            //FIRST ADDITION
            int result1 = c + f;    //add the last digit of each number
            int carry1 = result1/10;  //the quotient of the addition of 2 last digits of each number is the carry (the 1st digit of result1 if result1 >= 10; otherwise, carry = 0)
            int digit1 = result1%10;  //the remainder of the division result1 for 10
            //display the instruction:
            System.out.println("\n1st addition: ");
            System.out.println("  last digit of each number");
            System.out.println(c + " + " + f + " = " + result1 + " so answer is " + digit1 + " with a carry of " + carry1);

            //SECOND ADDITION
            System.out.println("\n2st addition:");
            System.out.println("  the carry from previous addition plus the middle digit of each number");
            int result2 = carry1 + b + e; //add the middle digit of each number and the carry from first addition
            int carry2 = result2/10;      //determine carry of second addition
            int digit2 = result2%10;      //determine the digit will display
            System.out.println("   " + carry1 + " + " + b + " + " + e + " = " + result2 + " so answer is " + digit2 + " with a carry of " + carry2);

            //THIRD ADDITION
            System.out.println("\n3st addition:");
            System.out.println("  the carry from previous addition plus the middle digit of each number");
            int result3 = carry2 + a + d;
            System.out.println( carry2 + " + " + a + " + " + d + " = " + result3 + " so answer is " + result3);

            //DISPLAY THE FINAL RESULT
            System.out.println("\nFinal answer:");
            System.out.println("\n  num1:\t  " + num1);
            System.out.println("  num2:\t+ " + num2);
            System.out.println("\t-----");
            System.out.println("Answer: " + result3 + digit2 + digit1);

            //ASK USER IF THEY WANT TO DO ANOTHER ADDITION
            System.out.print ("Do you want to try another one (y or Y to repeat) ");
            answer = keyboard.next();  //assign user answer to "answer"
            keyboard.close();  //close method makes Eclipse happy
        }

        while ( (answer.equalsIgnoreCase("y") )) ;  // if user enter Y or y, the program will run again.

        //closing banner
        System.out.println("\nHope you are more comfortable with additions now! If not, don't hesitate to come back :-) "); //closing banner
    }


}






















