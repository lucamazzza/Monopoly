package ch.supsi.game.monopoly;

import java.util.Random;
public class Dice {
    private int loBound;
    private int hiBound;
    private final Random random = new Random();

    public Dice(int loBound, int hiBound) {
        this.loBound = loBound;
        this.hiBound = hiBound;
    }
    public int roll(){
        return random.nextInt(loBound,hiBound + 1);
    }
    public void print(){
        System.out.println("You rolled: " + roll());
    }
}