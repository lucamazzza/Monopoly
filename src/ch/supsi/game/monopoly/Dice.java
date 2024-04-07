package ch.supsi.game.monopoly;

import java.util.Random;

/**
 * <p>
 * This class represents a dice.
 * </p>
 * <p>
 * The dice has a number of sides and a random value between 1 and the number of sides.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Dice dice = new Dice(1,6);   // instantiate a new D-6 dice
 * dice.roll();                 // roll the dice
 * System.out.println(dice);    // print the dices value
 * }
 * </pre>
 *
 * @author Ivo Herceg
 * @version 1.1.0
 */
public class Dice {

    /**
     * The lower bound of the dice.
     */
    private final int loBound;

    /**
     * The upper bound of the dice.
     */
    private final int hiBound;

    /**
     * The random number generator instance.
     */
    private final Random random = new Random();

    /**
     * The current value of the dice
     */
    private int currentValue;

    /**
     * <p>
     * Constructor of the Dice class.
     * </p>
     * <p>
     * Firstly the dice bounds are set, defining the number of faces.
     * This is done given the upper and lower bounds, as parameter.
     * If these values are not valid, so lower than 1 these are set to 1 and 1+1
     * if low is greater than high, these are inverted.
     * </p>
     *
     * @param loBound the lower bound of the dice
     * @param hiBound the upper bound of the dice
     */
    public Dice(int loBound, int hiBound) {
        if (loBound < 1) {
            loBound = 1;
        }
        if (hiBound < 1) {
            hiBound = loBound + 1;
        }
        this.loBound = Math.min(loBound, hiBound);
        this.hiBound = Math.max(loBound, hiBound);
    }

    /**
     * Returns the current value of the dice, as an integer.
     *
     * @return the current value
     */
    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * Rolls the dice, updating the current value of the dice, in {@link Dice#currentValue}.
     */
    public void roll(){
        this.currentValue = random.nextInt(loBound,hiBound + 1);
    }

    /**
     * Returns the dice value as a String.
     *
     * @return the dice value
     */
    @Override
    public String toString() {
        return String.valueOf(this.currentValue);
    }

}
