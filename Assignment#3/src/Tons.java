// ------------------------------------------------------- // Assignment (#3)
// Written by: Duke Nguyen
// For COMP 248 Section (W) – Winter 2018
// --------------------------------------------------------
/*The following program asks users to enter the number (the kind) of square they want to display to the screen. Then, depends on each case the user enter, ask for the factors of the square (size, character,...)
Then produce and display the square*/

import java.util.Scanner;  //declare the Scanner

public class Tons {

    public static void pattern(int SIZE, char[][] SQUARE)   //declare a method pattern to display the square via a character array

    {
        System.out.println("\nHere is your pattern");
        System.out.println();
        for (int i = 0; i < SIZE; i ++) 				//i: row
        {
            System.out.print("  ");
            for (int f = 0; f < SIZE; f++) 			//f: col
                System.out.print(SQUARE[i][f] + " ");
            System.out.println();
        }
    }

    public static void pattern(int SIZE, int[][] SQUARE)	//declare a method pattern to display the square via an integer array
    {
        System.out.println("\nHere is your pattern");
        System.out.println();
        for (int i = 0; i < SIZE; i ++)
        {
            System.out.print("  ");
            for (int f = 0; f < SIZE; f++)
                System.out.printf( "%3d",SQUARE[i][f]);
            System.out.println();
        }
    }
    public static void Fullfill(int SIZE, char[][] SQUARE, char FILL)  //declare a method "Fullfill" that will fill every slot in the array by one particular character
    {
        //full square

        for (int i = 0; i < SIZE; i++)
            for(int f = 0; f < SIZE; f++)
                SQUARE[i][f] = FILL;
    }


    public static void main(String[] args)     //main method
    {
        Scanner KeyIn = new Scanner(System.in);




        //BANNER
        System.out.println("|-------****-------****-------****-------****-----****-----|");
        System.out.println("| Welcome to the Decorated Square Drawing Program! |");
        System.out.println("|-------****-------****-------****-------****-----****-----|");

        //declare variables to use
        int choice;
        int size;
        char fill;

        do   //do-while loop
        {

            //Intro
            System.out.println("What type of square would you like? ");
            System.out.println("\t1 - Full square\n"
                    + "\t2 - Hollow square\n"
                    + "\t3 - An X\n"
                    + "\t4 - Horizontal Bars\n"
                    + "\t5 - Vertical Bars\n"
                    + "\t6 - Diagonal Bars\n"
                    + "\t7 - Integer Filled Square\n"
                    + "\t8 - Checkered (must be a multiple of 4)\n"
                    + "\t9 - Quit" );
            System.out.print("Enter your choice (1 to 9): ");
            choice = KeyIn.nextInt();  //assign user' input to variable "choice"


            if (choice == 9)
                break;   					//set condition: input = 9 => out of the do-while loop (Quit option)


            //test the requirements of the number user entered
            while (choice < 1  || choice > 9 )				//if the condition is not satisfied. Then display:

            {
                System.out.println("Sorry! That is an illegal choice.");
                System.out.print("Enter your choice (1 to 9): ");
                choice = KeyIn.nextInt();
            }

            //Ask for the size of the square and test requirement:
            if (choice == 8)   //Only in case 8, the number of rows and columns has additional condition (multiple of 4) and the text to be displayed is different than others
            {
                System.out.print("\nHow many rows and columns (max 20 and multiple of 4)? ");
                size = KeyIn.nextInt();
                while (size < 4 || size > 20 || size%4 != 0 )   //while the number user entered larger than 20 or smaller than 4 or is not multiple of 4. Display:
                {
                    System.out.print("How many rows and columns (max 20 and multiple of 4)? ");
                    size = KeyIn.nextInt();
                }
            }
            else  //in other cases
            {
                System.out.print("\nHow many rows and columns (min 4 & max 20)? ");
                size = KeyIn.nextInt();
                while (size < 4 || size >20)  //check if user input is larger than 20 or smaller than 4, then display
                {
                    System.out.print("How many rows and columns (min 4 & max 20)? ");
                    size = KeyIn.nextInt();
                }
            }

            //Declare a 2D array with type char

            char[][] square = new char[size][size];

            //use switch to determine what to do with each case
            switch(choice)
            {
                case 1:
                    //full square (1)
                    System.out.print("Which character do you want to fill your square with? ");
                    fill = KeyIn.next().charAt(0); //assign the user input to variable "fill"

                    Tons.Fullfill(size, square, fill);  //use the method declared to fill the array with character of "fill" variable
                    Tons.pattern(size, square);  	//use the method declared to display the array in square form
                    break;

                case 2:
                    //Hollow square
                    System.out.print("Which character do you want for the border? ");
                    fill = KeyIn.next().charAt(0); //assign the user input to variable "fill"
                    char blank = ' ';  //assign a blank space into "blank"
                    Tons.Fullfill(size, square, blank);  //fill the whole array with blank spaces

                    for (int i = 0; i < size; i++ )
                    {
                        square[0][i] = fill;  //fill the first row (in array) by the character user entered
                        square[size-1][i] = fill;  //fill the final row (in array) by the character user entered
                    }

                    for (int j = 0; j < size; j++)
                    {
                        square[j][0] = fill;  //fill the first column(in array) by the character user entered
                        square[j][size-1] = fill;	//fill the final column(in array) by the character user entered
                    }

                    Tons.pattern(size, square);  //use the method declared to display the array in square form
                    break;

                case 3: //an X
                    System.out.print("Which character do you want for the X? ");
                    fill = KeyIn.next().charAt(0);
                    System.out.print("Which character do you want around the X? ");
                    char around = KeyIn.next().charAt(0);

                    Tons.Fullfill(size, square, around);  //use the method declared to fill the array with character of "around" variable

                    square[size-1][size-1] = fill;    //Assign 3 special points (3 slots in the array) by the character of X ("fill" variable)
                    square[0][size-1] = fill;
                    square[size-1][0] = fill;

                    //assign the X character ("fill") into slots in array
                    //the diagonal from north east to south west
                    for(int i= 0, j = 0; i< size && j< size -1; i++, j++)
                        square[i][j] = fill;

                    //the diagonal from south east to north west
                    for(int i= size-1, j = 0; i >= 0 && j< size -1; i--, j++)
                        square[i][j] = fill;

                    Tons.pattern(size, square);  //use the method declared to display the array in square form
                    break;

                case 4: //Horizontal Bars
                    System.out.print("Which character do you want for the even rows? ");
                    char even = KeyIn.next().charAt(0);
                    System.out.print("Which character do you want for the odd rows? ");
                    char odd = KeyIn.next().charAt(0);

                    Tons.Fullfill(size, square, odd);	//use the method declared to fill the array with character of "odd" variable

                    for(int j = 1; j<= size; j++)  //check each row
                        if (j%2 == 0)				//if the number of row can be divided by 2 => use a for loop to fill it with even character
                        {
                            for (int count = 0; count < size; count ++)
                                square[j-1][count] = even;
                        }

                    Tons.pattern(size, square); //use the method declared to display the array in square form
                    break;

                case 5: //Vertical bars
                    System.out.print("Which character do you want for the even columns? ");
                    char evencol = KeyIn.next().charAt(0);
                    System.out.print("Which character do you want for the odd columns? ");//
                    char oddcol = KeyIn.next().charAt(0);

                    Tons.Fullfill(size, square, oddcol);	//use the method declared to fill the array with character of "oddcol" variable

                    for (int row = 0; row< size; row ++) //scroll through each row
                        for(int col = 1; col <= size; col++)	//a for loop to check each character in each row
                            if (col%2 == 0)					//check if the number of the column can be divided by 2 then fill it with "evencol" character
                                square[row][col-1] = evencol;

                    Tons.pattern(size, square);		 //use the method declared to display the array in square form
                    break;

                case 6: //diagonal bars
                    System.out.print("Which character do you want for the even diagonals? ");
                    char evenLetter = KeyIn.next().charAt(0);
                    System.out.print("Which character do you want for the odd diagonals? ");
                    char oddLetter = KeyIn.next().charAt(0);

                    Tons.Fullfill(size, square, oddLetter);	//use the method declared to fill the array with character "oddLetter"

                    for(int i = 0; i < size; i ++)  //check each row
                        for(int j = 0; j< size; j++)  //check each column
                        {
                            int location = i + j;		//declare variable "location" is the location of  character
                            if ( location % 2 == 0 )		//if statement to check if the "location" of the character is even, then assign that slot in array by "evenLetter"
                                square[i][j] = evenLetter;
                        }

                    Tons.pattern(size, square);	//use the method declared to display the array in square form
                    break;

                case 7: //Integer Filled Square
                    int[][] square7 = new int[size][size]; //declare a new 2D array (with type integer)

                    System.out.print("What is the starting number for your integer filled square (between 0 and 50 inclusive): ");
                    int begin = KeyIn.nextInt();
                    while (begin < 0 || begin > 50)  //check the validation of user input (if smaller than or larger than 50 then display)
                    {
                        System.out.print("What is the starting number for your integer filled square (between 0 and 50 inclusive): ");
                        begin = KeyIn.nextInt();
                    }

                    int n = begin - 1; // declare variable "n"
                    //In this problem, I divided it into 2 parts: 1 when the pattern is from north-east down to south-west and 1 with the pattern is from south-west up to north-east

                    //when the pattern starts from north-east down to south-west
                    for (int j = 0; j< size; j++)    //a for loop for columns
                        for(int count = j; count < size; count++) //a for loop for rows
                        {
                            square7[count][j] = n + 1;   //assign n+1 into the slot (n is the last value of the slot that assigned)
                            n = square7[count][j];		//assign into n to use for the next slot
                        }
                    //when the pattern starts from south-west up to north-east
                    for (int j = size - 1 ; j > 0; j-- )		//a for loop for columns
                        for(int count = j - 1; count >= 0; count --)		//a for loop for rows
                        {
                            square7[count][j] = n + 1;	//assign n+1 into the slot (n is the last value of the slot that assigned
                            n = square7[count][j];		//assign into n to use for the next slot
                        }

                    Tons.pattern(size, square7);		//use the method declared to display the array in square form

                    break;

                case 8: //Checkered
                    System.out.print("Which character do you want for the 1st checker? ");
                    char firstchecker = KeyIn.next().charAt(0);
                    System.out.print("Which character do you want for the 2nd checker? ");
                    char secondchecker = KeyIn.next().charAt(0);

                    Tons.Fullfill(size, square, firstchecker);	//use the method declared to fill the array with character "firstchecker"

                    for(int i = 0 ; i < size; i++)		//scroll through each row
                    {
                        if (i%4 == 0)  //if statement to determine if the number of row can be divided to 4

                        {
                            for(int j = 0; j < size-1; j++)  // a for loop to check each column

                                if (j%4 == 0)  //if statement in case the column can be divided by 4. Then assign the variable "secondchecker"
                                {
                                    square[i][j] = secondchecker;
                                    square[i+1][j+1] = secondchecker;
                                    square[i+1][j] = secondchecker;
                                    square[i][j+1] = secondchecker;
                                }
                        }
                        if (i%4 == 2)  //if statement to determine if the number of row will have the remainder = 2 when divided by 4
                            for(int j = 0; j< size -1; j++) // a for statement to check each column
                                if (j%4 == 2)				// //if statement in case the column will have the remainder = 2 when divided by 4. Then assign the variable "secondchecker"
                                {
                                    square[i][j] = secondchecker;
                                    square[i+1][j+1] = secondchecker;
                                    square[i+1][j] = secondchecker;
                                    square[i][j+1] = secondchecker;
                                }
                    }

                    Tons.pattern(size, square); //use the method declared to display the array in square form
                default: break;

            }
            System.out.println();

        } while (choice != 9);  //condition to continue the do-while loop

        KeyIn.close();  //keep Eclipse happy
        //closing banner
        System.out.println("\nHope you enjoyed your patterns!! Come back soon ...");



    }

}
