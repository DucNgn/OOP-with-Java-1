import java.util.Scanner;

public class Qwixx {

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
        this.players    = players;
        this.dices      = new Dice[6];
    }

    /**
     * ROLLED ALL DICES AT ONCE
     */
    public void rollDice() {
        for (Dice each : dices) {
            each.setCurrentSide(each.rollDice());
        }
    }

    /**
     * PRINT RESULT OF ALL ROLLED DICES
     */
    public void printRolledDice() {
        System.out.println("Red dice: " + dices[0].getCurrentSide() +
                           " | Yellow dice: " + dices[1].getCurrentSide() +
                           " | Green dice: " + dices[2].getCurrentSide() +
                           " | Blue dice: " + dices[3] +
                           " | White no.1 dice: " + dices[4] +
                           " | White no.2 dice: " + dices[5] + " |");
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

        Scanner keyIn                         = new Scanner(System.in);

        //ASK USER UNTILL GET THE VALID MOVE
        boolean valid = false;
        while (valid == false) {
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
                }
            }
        }

        keyIn.close();
    }

    /**
     * MOVE ON COLOURED + WHITE DICES
     * ASK USER => CHECK VALID => MAKE MOVE
     * @param p
     */
    public void makeRequestColouredMove(Player p) {

        Scanner keyIn                            = new Scanner(System.in);

        //ASK USER UNTILL GET A VALID MOVE
        boolean valid = false;
        while(valid == false) {
            System.out.print("Would you like to cross off a number on the game board using one of the coloured dice and a white dice?" +
                    " (anything other than 'yes' is taken to mean no): ");
            String choice                       = keyIn.nextLine();

            if(choice.equalsIgnoreCase("yes")) {
                System.out.print("Which white dice would you like to use? (White dice no.1 = 1, White dice no.2 = 2):");
                int whiteDice                   = keyIn.nextInt();
                System.out.print("What colour would you like to cross out? (R = red, Y = yellow, G = green, B = blue ): ");
                char colour                     = keyIn.nextLine().charAt(0);
                int colourDice                  = Move.convertColourtoNum(colour);

                int totalNumber = dices[whiteDice].getCurrentSide() + dices[colourDice].getCurrentSide();

                //CREATE MOVE AS REQUESTED
                Move request                = new Move(colour, totalNumber);
                valid                       = checkValidMove(p, request);

                //MAKE REQUESTED MOVE
                if(valid) {
                                 p.makeMove(request);
                                 checkColourFinished(p, colour);  //LOCK THE COLOUR IF THE REQUESTED MOVE LOCKS THAT COLOUR
                }
            } else {
                p.addNegativePoints(NEGPTS);
                System.out.println("For passing you get " + NEGPTS + " points. You now have " + p.getNegativePoints() + " points.");
            }
        }
        keyIn.close();

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
                if(m.getNumber() <= p.getlRed()) {
                    System.out.println("Invalid. Move " + m.getNumber() + " is already crossed off in " + m.getColour());
                    return false;
                }
                break;

            case 1:
                if(m.getNumber() <= p.getlYellow()) {
                    System.out.println("Invalid. Move " + m.getNumber() + " is already crossed off in " + m.getColour());
                    return false;
                }
                break;

            case 2:
                if(m.getNumber() >= p.getlGreen()) {
                    System.out.println("Invalid. Move " + m.getNumber() + " is already crossed off in " + m.getColour());
                    return false;
                }
                break;

            case 3:
                if(m.getNumber() >= p.getlBlue()) {
                    System.out.println("Invalid. Move " + m.getNumber() + " is already crossed off in " + m.getColour());
                    return false;
                }
        }

        //CHECK IF COLOUR IS ALREADY LOCKED
        if(checkColourFinished(p, m.getColour())) {
            System.out.println("This colour has already been locked");
            return false;
        }
        return true;
    }

    /**  ** ATTETION **
     * DO BOTH FUNCTION AT ONCE:
     * + CHECK A COLOUR IF IT HAS BEEN LOCKED
     * + CHECK TO LOCK COLOUR IF NEEDED
     * @param p
     * @param colour
     * @return
     */
    public boolean checkColourFinished(Player p, char colour) {

        int colourDigit = Move.convertColourtoNum(colour);
        //LOCK COLOUR
        switch(colourDigit) {
            case 0:
                if(lockR) {
                    return true;
                }
                if(p.getlRed() == 12) {
                    lockR = true;
                }
                break;

            case 1:
                if(lockY) {
                    return true;
                }
                if(p.getlYellow() == 12) {
                    lockY = true;
                }
                break;

            case 2:
                if(lockG) {
                    return true;
                }
                if(p.getlGreen() == 2) {
                    lockG = true;
                }
                break;

            case 3:
                if(lockB) {
                    return true;
                }
                if(p.getlBlue() == 2) {
                    lockB = true;
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
            return true;
        }

        for(Player each: players) {
            if(each.getNegativePoints() <= -20) {
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
        System.out.println("***** Move on white dice *****");
        System.out.println("The total for the white dice is " + getWhiteDiceTotal() + "\n");

        System.out.println(p.getName() + " it's your turn");
        p.printGameBoard();
        System.out.println("\n\n");

        //CALLING SUPPORT METHOD
        makeRequestedWhiteMove(p);


    }  //END OF METHOD

    /**
     * GENERATE MOVE WITH COLOURED DICE
     * @param p
     */
    public void playColourDiceMoves(Player p) {

        //DISPLAY INFORMATION FOR USER
        System.out.println(p.getName() + "it's your turn...\n");
        System.out.println("***** Move on any colour dice *****");

        p.printGameBoard();
        System.out.println();

        printRolledDice();
        System.out.println();

        //CALLING SUPPORT METHOD
        makeRequestColouredMove(p);
    }

    public void play() {


    }


}
