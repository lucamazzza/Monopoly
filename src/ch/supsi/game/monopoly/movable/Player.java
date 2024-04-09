package ch.supsi.game.monopoly.movable;

import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * <p>
 * This class represents a player.
 * </p>
 * <p>
 * The player has a name, a symbol, a balance and a position on the board.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Player player = new Player("Luca",'L');  // instantiate a new Player
 * player.addPropertyListener(this);        // listen to the player's position
 * player.move(2);                          // moves the player
 * player.receive(100);                     // receive some money
 * player.pay(50);                          // pay some money
 * System.out.println(player);              // print the player's stats
 * }
 * </pre>
 *
 * @author Ivo Herceg
 * @version 1.2.0
 */
public class Player extends PlayerMovementDelegate implements Movable{

    /**
     * The name of the player.
     */
    private final String name;

    /**
     * The symbol of the player.
     */
    private final char symbol;

    /**
     * The balance of the player.
     */
    private double balance;

    /**
     * Delegate to be used by the player to move on the board.
     */
    private final Movable delegate;


    /**
     * <p>
     * Constructor of the Player class.
     * </p>
     * <p>
     * Firstly the player is given a name and symbol. These are checked for empty or
     * blank values and then the player is created (by the arguments).
     * </p>
     *
     * @param name the name of the player
     * @param symbol the symbol of the player
     */
    public Player(String name, char symbol){
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Player name must not be empty or blank.");
        }
        if (symbol < 21) {
            throw new IllegalArgumentException("Player symbol must not be a blank character");
        }
        this.name = name;
        this.symbol = symbol;
        this.delegate = new PlayerMovementDelegate();
    }

    /**
     * Getter for the position of the player on the board.
     *
     * @return the position of the player
     */
    @Override
    public int getPosition() {
        return this.delegate.getPosition();
    }

    /**
     * Moves the player by the specified number of cells.
     *
     * @param movement the number of cells to move
     */
    @Override
    public void move(int movement) {
        this.delegate.move(movement);
    }

    /**
     * Getter for the symbol of the player.
     *
     * @return the symbol
     */
    public char getSymbol() {
        return symbol;
    }


    /**
     * Getter for the name of the player.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Getter for the balance of the player.
     *
     * @return the balance
     */
    public double getBalance() {
        return this.balance;
    }


    /**
     * <p>
     * Method in charge of handling the player's receiving money.
     * </p>
     * <p>
     * When called, the balance of the player is increased by the amount of money
     * received.
     * </p>
     *
     * @param amount the amount of money received
     */
    public void receive(double amount){
        if (amount < 1) {
            return;
        }
        this.balance += amount;
    }


    /**
     * <p>
     * Method in charge of handling the player's paying money.
     * </p>
     * <p>
     * When called, the balance of the player is decreased by the amount of money
     * asked.
     * </p>
     *
     * @param amount the amount of money paid
     */
    public void pay(double amount){
        if (amount < 1) {
            return;
        }
        this.balance -= amount;
    }

    /**
     * Returns the player's stats as a String.
     *
     * @return the player's stats
     */
    @Override
    public String toString(){
        return String.format("[%c: %s] Your Balance: %.2f%n", this.symbol, this.name, this.balance);
    }

    /**
     * Checks if the player is equal to another player.
     *
     * @param o the player to compare
     * @return true if the players are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return this.symbol == player.symbol && Objects.equals(this.name, player.name);
    }

    /**
     * Hashes the player, given their name and symbol.
     *
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.symbol);
    }

    /**
     * Adds a new listener to the {@code PropertyChange} trigger class ({@code this}).
     *
     * @param pcl a PropertyChangeListener object describing the event listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.delegate.addPropertyChangeListener(pcl);
    }

    /**
     * Removes a listener to the {@code PropertyChange} trigger class ({@code this}).
     *
     * @param pcl a PropertyChangeListener object describing the event listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        this.delegate.removePropertyChangeListener(pcl);
    }
}
