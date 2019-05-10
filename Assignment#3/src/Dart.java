// ------------------------------------------------------- // Assignment (#3)
// Written by: Duke Nguyen
// For COMP 248 Section (W) – Winter 2018
// --------------------------------------------------------
/*The following program use random method to stimulate the tossing of darts until the total score equals
 * or exceeds 1000, then the game ends. The score for each turn depends on the region it was hit. Display the total score for each region, number of times each region was hit and the total score for the game*/



public final class Dart {

    public static void main(String[] args)
    {
        //BANNER
        System.out.println("|----------------->>>><<<<<--------------------|");
        System.out.println("|\tWelcome to the Simplified Dart Game!\t |");
        System.out.println("|----------------->>>><<<<<------------------- |");

        System.out.println("Region  Hits    Points");
        System.out.println("-------------------------");

        //Declare variables and arrays
        int total = 0;
        int[] Pointvalue = new int[10];   //the array to record the total points of each region (have 10 regions; therefore, declare array with 10 slots)
        int[] Times = new int[10];        //the array to count how many time it was hit in each region (have 10 regions => declare array with 10 slots)
        int count = 0; //variable to count the number of time tossing darts

        do
        {

            int each = (int)(Math.random() * 10 + 1);  //use random method to assign for variable "each" in range (1 -> 10)
            int Value;  //The point user achieve in each turn of tossing

            switch (each)  //variable "each" is the number of region it was hit (by random method)
            {
                case 1:          //hit region 1
                    Value = 7;   // achieve 7 scores
                    break;
                case 2: case 3: case 4:  //hit regions (2 3 4)
                Value = 5;			// achieve 5 scores in each case.
                break;
                case 5: case 6: case 7:  //hit regions (5 6 7)
                Value = 3;			//achieve 3 scores in each case
                break;

                default:    				//hit the rest regions (8; 9; 10)
                    Value = 1;			//achieve 1 score in each case
                    break;
            }


            Times[each-1]++;   // increment the number of time the region was hit
            Pointvalue[each-1] = Pointvalue[each-1] + Value;  //increase the scores in the region

            total = total + Value;  //the total score already had plus the scores just achieved
            count++;					//number of time tossing the darts

        }
        while (total < 1000); 		//the (do while) loop to set the condition for the program. The program keeps running when total scores < 1000

        //Print the result
        for (int i = 1; i <= 10; i++ )
        {
            System.out.println("   " + i + "\t" + Times[i-1] + "\t" + Pointvalue[i-1]);

        }

        //Print the conclusion
        System.out.println("\nIt took " + count + " tosses for a total of " + total);

        //Closing banner
        System.out.println("\nThat was an effortless game of darts !");

    }

}
