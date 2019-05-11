public class Dice {

    String colour;
    int currentSide;

    /**
     * CONSTRUCTOR
     */
    public Dice() {
        this.colour = "white";
        this.currentSide = rollDice();
    }

    public Dice(String colour) {
        this.colour = colour;
        this.currentSide = rollDice();
    }

    /**
     * GETTERS
     * SETTERS
     */
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(int currentSide) {
        this.currentSide = currentSide;
    }

    @Override
    public String toString() {
         String out = "The colour of this dice is " + this.colour + ", the current side is " + this.currentSide;
         return out;
    }

    /**
     * RANDOMLY GENERATE A DICE
     * @return
     */
    public int rollDice() {
        int rand = (int)Math.random() * (6 - 1);
        return rand;
    }
}
