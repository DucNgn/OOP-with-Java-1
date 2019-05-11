import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner keyIn = new Scanner(System.in);
        int playerCtr = 0;

        while (true) {
            System.out.print("Please enter the number of players (2-5): ");
            playerCtr = keyIn.nextInt();
            if(playerCtr >=2 && playerCtr <= 5) {
                break;
            }
            System.out.println("You must have between 2 and 5 players");
        }

        Player[] ready = new Player[playerCtr];
        for(int i=0; i<playerCtr; i++) {
            ready[i] = new Player();
        }

        for(int i=0; i<playerCtr; i++) {

            int ctr                  = i++;
            System.out.print("Please enter the name of player no. " + ctr + ": ");
            keyIn.nextLine();
            String pName             = keyIn.nextLine();

            ready[i] = new Player(pName);
            ready[i].printGameBoard();
            System.out.println();
        }

        keyIn.close();
        Qwixx game = new Qwixx(ready);
        game.play();

    }
}
