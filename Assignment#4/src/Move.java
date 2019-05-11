/**
 * @AUTHOR: Duke Ngn
 * COMP248 -- WINTER 2018
 * This class handle requested move. It will make the move on player gameboard
 */

public class Move {
    char colour;
    int number;

    /**
     * GETTERS
     * SETTERS
     */
    public char getColour() {
        return colour;
    }

    public void setColour(char colour) {
        this.colour = colour;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * CONSTRUCTOR
     * @param colour
     * @param number
     */
    public Move(char colour, int number) {
        this.colour = colour;
        this.number = number;
    }

    /**
     * CONVERT CHAR COLOUR ---> INTEGER
     * @param colour
     * @return
     */
    public static int convertColourtoNum(char colour) {
        int result = 0; //DEFAULT
        switch(colour) {
            case 'R':
                result = 0;
                break;
            case 'Y':
                result = 1;
                break;
            case 'G':
                result = 2;
                break;
            case 'B':
                result = 3;
                break;
        }

        return result;
    }

    public String toString() {
        String msg = "move at colour " + this.getColour() + ", number " + this.getNumber();
        return msg;
    }


    //END OF CLASS

}
