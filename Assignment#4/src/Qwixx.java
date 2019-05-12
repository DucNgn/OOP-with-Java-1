/**
 * @AUTHOR: Duke Ngn
 * COMP248 -- WINTER 2018
 * This class handles the game with lock colour, the dices, players and negative points
 * method play() will start the game
 */

import java.util.Arrays;
import java.util.Scanner;

public class Qwixx {

    int bestScore;
    Scanner keyIn;
    Dice[] dices;
    Player[] players;
    boolean lockR, lockY, lockG, lockB;
    final static int NEGPTS = -5;  //THE PENALTY

    /**       !!!!!    NOTICE    !!!!
     * THERE ARE 6 DICES WITH COLOUR RESPECTIVELY
     * RED || YELLOW || GREEN || BLUE || WHITE <1> || WHITE <2>
     * CORRESPONDING TO EACH SLOT IN THE ARRAY OF `DICES`
     */

    /**
     * CONSTRUCTOR
     * @param players
     */
    public Qwixx(Player[] players) {
        this.bestScore = 0;
        this.players    = players;
        keyIn = new Scanner(System.in);
        this.dices      = new Dice[6];
        dices[0] = new Dice("Red");
        dices[1] = new Dice("Yellow");
        dices[2] = new Dice("Green");
        dices[3] = new Dice("Blue");
        dices[4] = new Dice("White1");
        dices[5] = new Dice("White2");

        lockR = lockB = lockG = lockB = false;
    }

    /**
     * ROLLED ALL DICES AT ONCE
     */
    public void rollDice() {
        System.out.println("Rolling dices ..... ");
        for (Dice each : this.dices) {
            each.setCurrentSide(each.rollDice());
        }
    }

    /**
     * PRINT RESULT OF ALL ROLLED DICES
     */
    public void printRolledDice() {
        System.out.println("");
       for(Dice each: dices) {
           System.out.print(each + " | ");
       }
        System.out.println("\n");
    }

   //////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                  ||          **** HELPER METHODS FOR PLAYING DICE ****
    //                  ||
    //                  ||
    //                  ||
    //                 \||/
    //                  \/

    /**
     * RETURN THE TOTAL NUMBER OF ROLLED WHITE DICES
     * @return
     */
    public int getWhiteDiceTotal() {
        return dices[4].getCurrentSide() + dices[5].getCurrentSide();

    }

    /**
     * MOVE ON WHITE DICES
     * ASK USER => CHECK VALID => MAKE MOVE
     * @param p
     */
    public void makeRequestedWhiteMove(Player p) {

        //ASK USER UNTILL GET THE VALID MOVE
        boolean valid = false;
        while (!valid) {
            System.out.print("Would you like to cross off a number on the game board using the white dice total?" +
                    " (anything other than 'yes' is taken to mean no): ");
            String choice                         = keyIn.nextLine();

            if(choice.equalsIgnoreCase("yes")) {
                System.out.print("What colour would you like to cross out? " +
                        "(R = red, Y = yellow, G = green, B = Blue): ");
                char colour                       = keyIn.nextLine().charAt(0);

                Move request                      = new Move(colour, getWhiteDiceTotal());  //create new move
                valid                             = checkValidMove(p, request);

                //MAKE REQUESTED MOVE IF IT IS VALID
                if(valid) {
                                        p.makeMove(request);
                                        checkColourFinished(p, colour); //LOCK THE COLOUR IF THE REQUESTED MOVE LOCKS THAT COLOUR
                                        System.out.println("\n---> " + request + " successfully ");
                                        p.printGameBoard();
                }
                keyIn.nextLine();
            } else {
                valid = true;
            }
        }

    }

    /**
     * MOVE ON COLOURED + WHITE DICES
     * ASK USER => CHECK VALID => MAKE MOVE
     * @param p
     */
    public void makeRequestColouredMove(Player p) {

        System.out.println("\n PROCESSING \n");
        //ASK USER UNTILL GET A VALID MOVE
        boolean valid = false;
        while(valid == false) {
            System.out.print("\nWould you like to cross off a number on the game board using one of the coloured dice and a white dice?" +
                    " (anything other than 'yes' is taken to mean no): ");
            String choice                       = keyIn.nextLine();

            if(choice.equalsIgnoreCase("yes")) {
                System.out.print("Which white dice would you like to use? (White dice no.1 = 1, White dice no.2 = 2):");
                int whiteDiceChoice                   = keyIn.nextInt();
                keyIn.nextLine();

                System.out.print("What colour would you like to cross out? (R = red, Y = yellow, G = green, B = blue ): ");
                char colour                     = keyIn.nextLine().charAt(0);
                int colourDice                  = Move.convertColourtoNum(colour);

                int whiteDice = (whiteDiceChoice == 1) ? 4 : 5;  //if user chose white dice no.1 => dices[4]. Otherwise, dices[2] is called
                int totalNumber = dices[whiteDice].getCurrentSide() + dices[colourDice].getCurrentSide();

                //CREATE MOVE AS REQUESTED
                Move request                = new Move(colour, totalNumber);
                valid                       = checkValidMove(p, request);

                //MAKE REQUESTED MOVE
                if(valid) {
                                 p.makeMove(request);
                                 checkColourFinished(p, colour);  //LOCK THE COLOUR IF THE REQUESTED MOVE LOCKS THAT COLOUR
                                 System.out.println("\n---> " + request + " successfully ");
                                 p.printGameBoard();
                }
            } else {
                valid = true;
                p.addNegativePoints(NEGPTS);
                System.out.println("For passing you get " + NEGPTS + " points. You now have " + p.getNegativePoints() + " points.");
            }
        }

    }

    /**
     * CHECK THE VALIDITY OF REQUESTED MOVE M FROM PLAYER P
     * @param p
     * @param m
     * @return
     */
    public boolean checkValidMove(Player p, Move m) {
        //CHECK NUMBER IF OUT OF RANGE
        if(m.getNumber() > 12 || m.getNumber() < 2) {
            return false;
        }

        //CHECK ALREADY CROSSED OFF
        int colour = Move.convertColourtoNum(m.getColour());
        switch(colour) {
            case 0:
                if(lockR) {
                    System.out.println("Can't move on Red, it's locked");
                    return false;
                }
                if(m.getNumber() <= p.getlRed()) {
                    System.out.println("!! Invalid !!. Move " + m.getNumber() + " is already crossed off in " + m.getColour());
                    return false;
                }
                break;

            case 1:
                if(lockY) {
                    System.out.println("Can't move on Yellow, it's locked");
                    return false;
                }
                if(m.getNumber() <= p.getlYellow()) {
                    System.out.println("!! Invalid !!. Move " + m.getNumber() + " is already crossed off in " + m.getColour());
                    return false;
                }
                break;

            case 2:
                if(lockG) {
                    System.out.println("Can't move on Green, it's locked");
                    return false;
                }
                if(m.getNumber() >= p.getlGreen()) {
                    System.out.println("!! Invalid !!. Move " + m.getNumber() + " is already crossed off in " + m.getColour());
                    return false;
                }
                break;

            case 3:
                if(lockB) {
                    System.out.println("Can't move on Blue, it's locked");
                    return false;
                }
                if(m.getNumber() >= p.getlBlue()) {
                    System.out.println("!! Invalid !!. Move " + m.getNumber() + " is already crossed off in " + m.getColour());
                    return false;
                }
        }
        //VALID
        return true;
    }

    /**
     * CHECK TO LOCK COLOUR IF NEEDED
     * @param p
     * @param colour
     * @return
     */
    public boolean checkColourFinished(Player p, char colour) {

        int colourDigit = Move.convertColourtoNum(colour);
        //LOCK COLOUR
        switch(colourDigit) {
            case 0:
                if(p.getlRed() == 12) {
                    lockR = true;
                    System.out.println("Red is no longer playable. Player " + p.getName() + " has locked it");
                    return true;
                }
                break;

            case 1:
                if(p.getlYellow() == 12) {
                    lockY = true;
                    System.out.println("Yellow is no longer playable. Player " + p.getName() + " has locked it");
                    return true;
                }
                break;

            case 2:
                if(p.getlGreen() == 2) {
                    lockG = true;
                    System.out.println("Green is no longer playable. Player " + p.getName() + " has locked it");
                    return true;
                }
                break;

            case 3:
                if(p.getlBlue() == 2) {
                    lockB = true;
                    System.out.println("Blue is no longer playable. Player " + p.getName() + " has locked it");
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * CHECK IF GAME ENDS
     * @return
     */
    public boolean checkGameFinished() {
        int check =0;
        if(lockR) {
            check++;
        }
        if(lockB) {
            check++;
        }
        if(lockG) {
            check++;
        }
        if(lockY) {
            check++;
        }

        if(check >= 2) {
            System.out.println("\n-------------------------GAME FINISHED------------------------\n");
            return true;
        }

        for(Player each: players) {
            if(each.getNegativePoints() <= -20) {
                System.out.println("Player " + each.getName() + " has reached maximum allowed penalty points");
                System.out.println("\n-------------------------GAME FINISHED------------------------\n");
                return true;
            }
        }
        return false; //THE GAME CONTINUES
    }

    //                  /\
    //                 /||\
    //                  ||          **** HELPER METHODS FOR PLAYING DICE  ****
    //                  ||
    //                  ||
    //                  ||
    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * GENERATE MOVE WITH WHITE DICES AS REQUESTED
     * @param p
     */
    public void playWhiteDiceMove(Player p) {

        //DISPLAY INFORMATION FOR USER
        System.out.println("\n***** Move on white dice *****");
        System.out.println("The total for the white dice is " + getWhiteDiceTotal() + "\n");

        p.callTurn();
        p.printGameBoard();
        System.out.println("\n");

        //CALLING SUPPORT METHOD
        makeRequestedWhiteMove(p);


    }  //END OF METHOD

    /**
     * GENERATE MOVE WITH COLOURED DICE
     * @param p
     */
    public void playColourDiceMoves(Player p) {

        //DISPLAY INFORMATION FOR USER
        p.callTurn();
        System.out.println("\n***** Move on any colour dice *****");

        p.printGameBoard();
        System.out.println();

        printRolledDice();

        //CALLING SUPPORT METHOD
        makeRequestColouredMove(p);
    }

    /**
     * A LOOP METHOD TO PLAY THE GAME TILL FINISH
     */
    public void play() {
        boolean done = false;
        while(done == false ) {

            for(int i =0; i<players.length; i++) {

                //ANNOUNCE NEW ROUND
                System.out.println("\n############----------------NEW ROUND------------###########\n");
                System.out.println("----------------------------------------------------------------");
                System.out.println("        ||                                      ||");
                System.out.println("        ||                                      ||");
                System.out.println("        ||                                      ||");
                System.out.println("       \\||/                                    \\||/");
                System.out.println("        \\/                                      \\/ ");
                System.out.println("");

                //DEBUG
                rollDice();
                dices[4].setCurrentSide(6);
                dices[5].setCurrentSide(6);

                printRolledDice();
                //ALL PLAYER PLAY WITH WHITE DICES
                for(Player each: players) {
                        playWhiteDiceMove(each);
                        done        = checkGameFinished();
                        if(done)    {
                            break;
                        }
                }

                if(done) {
                    break;
                }

                //THE PLAYER IS IN TURN PLAYS WITH COLOURED DICE
                playColourDiceMoves(players[i]);
                done = checkGameFinished();

                if(done) {
                    break;
                }

                System.out.println("\n############----------------END ROUND------------###########\n");
            } //END OF FOR-LOOP

        } //END OF WHILE-LOOP

        //AT THIS POINT, THE GAME HAS BEEN FINISHED
        determineWinner();
        keyIn.close();
    } //END OF METHOD

    public void makeScoreBoard() {

        //sorting
        Player compare = players[0];
        for(int i = 1; i<players.length; i++) {
            if(players[i].getBoardTotal() > compare.getBoardTotal()) {
              Player temp = players[i];
              players[i-1] = players[i];
              players[i] = temp;
            } else {
                compare = players[i];
            }
        }

        System.out.println("DISPLAYING TOTAL SCORE IN ORDER");
        int count = 1;
        for (Player each: players) {
            System.out.println("No." + count + " :" + each.getName() + " has a total of: " + each.getBoardTotal() );
            count++;
        }

        bestScore = players[0].getBoardTotal();
    }

    /**
     *  DISPLAY ALL THE SCORE
     *  DETERMINE THE WINNER
     */
    public void determineWinner() {

        makeScoreBoard();
        System.out.println("The winner is player " + players[0].getName() + " with the total score: " + bestScore);
    }


    //END OF CLASS
}
