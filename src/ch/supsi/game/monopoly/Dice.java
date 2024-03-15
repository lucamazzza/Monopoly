package ch.supsi.game.monopoly;

import java.util.Random;
public class Dice {
    private int loBound;
    private int hiBound;
    private final Random random = new Random();
    private int currentValue;

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
    public int getCurrentValue() {
        return currentValue;
    }

    public void roll(){
        this.currentValue = random.nextInt(loBound,hiBound + 1);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.valueOf(this.currentValue);
    }

}
