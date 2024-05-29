package ch.supsi.game.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    @Test
    void roll() {
        Dice dice = new Dice(1,6);
        for (int i = 0; i < 10000; i++) {
            dice.roll();
            assertTrue(dice.getCurrentValue() >= 1 && dice.getCurrentValue() <= 6);

        }
        for (int i = 0; i < 10000; i++) {
            dice.roll();
            assertFalse(dice.getCurrentValue() < 1 || dice.getCurrentValue() > 6);
        }
    }
}