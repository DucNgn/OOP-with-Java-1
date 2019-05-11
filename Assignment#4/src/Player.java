public class Player {

    String name;
    String[][] gameBoard;
    int lRed, lYellow, lGreen, lBlue;
    int negativePoints;

    /**
     * DEFAULT CONSTRUCTOR
     */
    public Player() {
        System.out.println("Hello from default construc player");
        lRed = lYellow = lGreen = lBlue =0;
        this.negativePoints = 0;
        initializeGameboard();
    }

    /**
     * SET NAME FOR PLAYER
     * @param name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * GETTERS
     * SETTERS
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getlRed() {
        return lRed;
    }

    public void setlRed(int lRed) {
        this.lRed = lRed;
    }

    public int getlYellow() {
        return lYellow;
    }

    public void setlYellow(int lYellow) {
        this.lYellow = lYellow;
    }

    public int getlGreen() {
        return lGreen;
    }

    public void setlGreen(int lGreen) {
        this.lGreen = lGreen;
    }

    public int getlBlue() {
        return lBlue;
    }

    public void setlBlue(int lBlue) {
        this.lBlue = lBlue;
    }

    public int getNegativePoints() {
        return negativePoints;
    }

    public void setNegativePoints(int negativePoints) {
        this.negativePoints = negativePoints;
    }

    /**
     * Initialize gameboard
     */
    public void initializeGameboard() {
        System.out.println("Initializing keyboard");
        this.gameBoard = new String[4][11];

        gameBoard[0][0] = " Red ";
        gameBoard[1][0] = " Yellow ";
        for(int i = 1; i<11; i++) {
            String temp = Integer.toString(i+1);
            gameBoard[0][i] = temp;
            gameBoard[1][i] = temp;
        }

        gameBoard[2][0] = " Green ";
        gameBoard[3][0] = " Blue ";
        for(int i = 10; i > 0; i--) {
            String temp = Integer.toString(i+1);
            gameBoard[2][i] = temp;
            gameBoard[3][i] = temp;
        }
    }

    /**
     * Add negative points to user's record
     * @param pts
     */
    public void addNegativePoints(int pts) {
        this.negativePoints = negativePoints + pts;
    }

    /**
     * PRINT OUT THE GAMEBOARD OF PLAYER
     */
    public void printGameBoard() {
        System.out.println(this.name + "'s GameBoard");
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 11; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println("");  //to the other line
        }
    }

    /**
     * MAKE MOVE AS REQUESTED
     * MAKE CHANGE IN LAST MOVE
     * @param m
     */
    public void makeMove(Move m) {
        int moveColour = Move.convertColourtoNum(m.getColour());
        gameBoard[moveColour][m.getNumber()] = "X";

        switch(moveColour) {
            case 0:
                this.setlRed(m.getNumber());
                break;

            case 1:
                this.setlYellow(m.getNumber());
                break;

            case 2:
                this.setlGreen(m.getNumber());
                break;

            case 3:
                this.setlBlue(m.getNumber());
                break;
        }
    }

    /**
     * RECURSIVE METHOD TO CALCULATE THE TOTAL POINT OF A COLUMN OF COLOUR
     * @param ctr
     * @return
     */
    public int totalOfEachColor(int ctr) {
        if(ctr == 1) {
            return 1;
        } else {
          return ctr + totalOfEachColor(ctr-1);
        }
    }

    /**
     * Calculate the total points player got
     * @return
     */
    public int getBoardTotal() {
        int total =0;
        for(int i = 0; i<4; i++) {
            int count = 0;
            for(int j = 1; j<11; j++) {
                if(gameBoard[i][j].equals("X")) {
                    count++;
                }
            }
            total = total + totalOfEachColor(count);
        }
        total = total - this.negativePoints;
        return total;
    }
}
