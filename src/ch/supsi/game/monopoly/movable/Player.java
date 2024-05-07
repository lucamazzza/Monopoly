package ch.supsi.game.monopoly.movable;

import ch.supsi.game.monopoly.Board;
import ch.supsi.game.monopoly.cells.Cell;
import ch.supsi.game.monopoly.cells.ProprietyCell;
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
     *
     */
    private final int[] colorsOwned = new int[8];

    /**
     *
     * @return
     */
    public boolean canBuild(){
        if (colorsOwned[0]==2)
            return true;
        for (int i:colorsOwned){
            if (i==3)
                return true;
        }
        return false;
    }

    /**
     *
     * @param cell
     */
    public void addColor(Cell cell) {
        if(cell instanceof ProprietyCell pc){
            int tmp = pc.getColor();
            colorsOwned[getIndexOfColor(tmp)]++;
        }
    }

    /**
     *
     * @param buildOptions
     */
    public void showBuildOptions(Cell[] buildOptions){
        System.out.println("Choose where you want to build: ");
        for (int i = 0; i < buildOptions.length; i++) {
            if (buildOptions[i] != null)
                System.out.println(i+1 + ". " + buildOptions[i].getTitle());
        }
    }

    /**
     *
     * @param board
     * @return
     */
    public Cell[] getBuildOptions(Board board){
        int numberOfBuildableProprietes = 0;
        if (colorsOwned[0]==2)
            numberOfBuildableProprietes += 2;
        if (colorsOwned[7]==2)
            numberOfBuildableProprietes += 2;
        for (int j : colorsOwned) {
            if (j == 3) {
                numberOfBuildableProprietes += 3;
            }
        }
        Cell[] buildOptions = new Cell[numberOfBuildableProprietes];
        int indexOfbuildOptions = 0;
        Cell[] temp;
        if (colorsOwned[0]==2) {
            temp = board.getAllProprietesOfColor(getColorOfIndex(0));
            for (Cell cell : temp) {
                buildOptions[indexOfbuildOptions] = cell;
                indexOfbuildOptions++;
            }
        }
        if (colorsOwned[7]==2) {
            temp = board.getAllProprietesOfColor(getColorOfIndex(7));
            for (Cell cell : temp) {
                buildOptions[indexOfbuildOptions] = cell;
                indexOfbuildOptions++;
            }
        }
        for (int i = 1; i < colorsOwned.length; i++) {
            if (i==7)
                continue;
            if (colorsOwned[i] == 3){
                temp = board.getAllProprietesOfColor(getColorOfIndex(i));
                for (Cell cell : temp) {
                    buildOptions[indexOfbuildOptions] = cell;
                    if(indexOfbuildOptions!=numberOfBuildableProprietes-1)
                        indexOfbuildOptions++;
                }
            }
        }
        return buildOptions;
    }
    //TODO: Browski no
    private int getColorOfIndex(int indexOfColor) {
        if (indexOfColor == 0)
            return 33;
        else if (indexOfColor == 1)
            return 36;
        else if (indexOfColor == 2)
            return 35;
        else if (indexOfColor == 3)
            return 37;
        else if (indexOfColor == 4)
            return 31;
        else if (indexOfColor == 5)
            return 93;
        else if (indexOfColor == 6)
            return 32;
        else if (indexOfColor == 7)
            return 34;
        else
            return 33;
    }
    private int getIndexOfColor(int color){
        if (color == 33)
            return 0;
        else if (color == 36)
            return 1;
        else if (color == 35)
            return 2;
        else if (color == 37)
            return 3;
        else if (color == 31)
            return 4;
        else if (color == 93)
            return 5;
        else if (color == 32)
            return 6;
        else if (color == 34)
            return 7;
        else
            return 0;
    }

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
