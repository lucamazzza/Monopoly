package ch.supsi.game.monopoly;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void setPosition() {
        final Player player = new Player("Test",'T');
        player.setPosition(0);
        assertEquals(0, player.getPosition());
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            final int position = random.nextInt(0, Constant.BOARD_SIZE);
            player.setPosition(position);
            assertEquals(position, player.getPosition());
        }
        assertThrows(IllegalArgumentException.class, () -> player.setPosition(-100));
        assertThrows(IllegalArgumentException.class, () -> player.setPosition(Constant.BOARD_SIZE));
        assertThrows(IllegalArgumentException.class, () -> player.setPosition(100000));
    }

    @Test
    void move() {
        final Player player = new Player("Test",'T');
        player.setPosition(0);
        final Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            final int movement = random.nextInt(0, Constant.BOARD_SIZE - 1);
            final int oldPosition = player.getPosition();
            player.move(movement);
            assertEquals((oldPosition + movement) % Constant.BOARD_SIZE, player.getPosition());
            assertTrue(player.getPosition() >= 0 && player.getPosition() < Constant.BOARD_SIZE);
        }
    }

    @Test
    void receive() {
        final Player player = new Player("Test",'T');
        final Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            final double amount = random.nextInt(0, 10000);
            player.receive(amount);
            assertTrue(player.getBalance() >= amount);
        }
    }

    @Test
    void pay() {
        final Player player = new Player("Test", 't');
        final Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            final double amount = random.nextInt(0, 10000);
            player.pay(amount);
            assertTrue(player.getBalance() < amount);
        }
    }
}