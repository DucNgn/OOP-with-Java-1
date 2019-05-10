// ------------------------------------------------------- // Assignment (#2)
// Written by: Duke Nguyen
// For COMP 248 Section (W) – Winter 2018
// --------------------------------------------------------
/*the following programs asks user to enter the size and seed, then displays to the screen a Parkside triangle based on the seed and size user entered*/
import java.util.Scanner;
public class Triangle {

    public static void main(String[] args)
    {
        int output, line, numb; //declare variables

        Scanner KeyIn = new Scanner(System.in); // create scanner for console input

        System.out.println("-----------------------------------------------------");
        System.out.println("\tWelcome to Nancy's Parkside's Triangle Producer");
        System.out.println("-----------------------------------------------------");// banner

        //ask user inout for size
        System.out.print("\nSize ( must be between 5 and 10 inclusive): ");
        int size = KeyIn.nextInt();

        if ( (size < 5 ) || (size > 10 ) )   //determine if the size user entered in the range (5 to 10). if no then ask user again until the user enter a eligible number of size
        {
            System.out.print("Size ( must be between 5 and 10 inclusive): ");
            size = KeyIn.nextInt();
        }


        //ask user input for seed
        System.out.print("Seed ( must be between 1 and 9 inclusive): ");
        byte seed = KeyIn.nextByte();

        if ( (seed < 1) || (seed > 9))  //determine if the seed user entered in the range from 1 to 9. if no then ask the user again, until the user enter a eligible number of seed
        {
            System.out.print("Seed ( must be between 1 and 9 inclusive): ");
            seed = KeyIn.nextByte();
        }

        //Intial assignment:
        output = seed;
        line = 0;

        //loop (basically, the number of line is also the number of digits exist in a line. Such as: line 2 has 2 digits, line 3 has 3 digits,.. )
        do
        {
            numb = 0;
            line++;
            do
            {
                System.out.print(output);
                System.out.print(" ");
                numb++;
                output++;
                if (output >= 10)
                    output = output/10;  // if output >= 10 then make it becomes a digit by finding the quotient of it to 10
            }
            while (numb < line);   //print the digits until the number of digits in a line is equal to the position of that line
            System.out.println(" ");  //go to next line
            size-- ;   //decrement the size after print a line completely
        }
        while (size > 0);

        System.out.println("\nAll done !");  //closing banner
        KeyIn.close();   //close method is the reason why Eclipse happy

    }

}
