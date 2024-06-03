package ch.supsi.game.monopoly;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.cells.Cell;
import ch.supsi.game.monopoly.cells.ProprietyCell;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

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
 * @version 1.3.0
 */
public class Player {

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
     * The position of the player on the board.
     */
    private int position;

    /**
     * PropertyChange support class, which allows to fire
     * {@link java.beans.PropertyChangeEvent}s and trigger
     * behaviour in a {@link PropertyChangeListener}.
     */
    private final PropertyChangeSupport support;

    /**
     * Flag that states if the player is in prison
     */
    private boolean isInPrison;

    /**
     * Flag that states if the player is a tax evader.
     */
    private boolean isEvader;

    /**
     * The amount of money the player evaded.
     */
    private double amountEvaded;

    /**
     * Times that the player tried to evade
     */
    private int timesTriedEvading;

    /**
     * A list of integers representing the proprieties, group by color, owned by the player.
     * <b>Example:</b>
     * <table>
     *     <tr>
     *      <th>Brown</th>
     *      <th>Cyan</th>
     *      <th>Pink</th>
     *      <th>Grey</th>
     *      <th>Red</th>
     *      <th>Yellow</th>
     *      <th>Green</th>
     *      <th>Blue</th>
     *     </tr>
     *     <tr>
     *      <td>1</td>
     *      <td>3</td>
     *      <td>0</td>
     *      <td>0</td>
     *      <td>0</td>
     *      <td>0</td>
     *      <td>0</td>
     *      <td>0</td>
     *     </tr>
     * </table>
     */
    private final int[] colorsOwned = new int[8];

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
    public Player(final String name, final char symbol){
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Player name must not be empty or blank.");
        }
        if (symbol < 21) {
            throw new IllegalArgumentException("Player symbol must not be a blank character");
        }
        this.name = name;
        this.symbol = symbol;
        this.isInPrison = false;
        this.timesTriedEvading = 0;
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * <p>
     * Getter for the position of the player on the board.
     * </p>
     * @return the position of the player
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * <p>
     * Sets the position of the player to a given position.
     * </p>
     *
     * @param position the position
     */
    public void setPosition(final int position) {
        if (position < 0 || position >= Constant.BOARD_SIZE) {
            throw new IllegalArgumentException("Position must be between 0 and " + (Constant.BOARD_SIZE - 1));
        }
        final int old = this.position;
        this.position = position;
        this.support.firePropertyChange("position", old, this.position);
    }
    /**
     * <p>
     * Moves the player by the specified number of cells.
     * </p>
     *
     * @param movement the number of cells to move
     */
    public void move(final int movement) {
        final int old = this.position;
        this.position = (this.position + movement) % Constant.BOARD_SIZE;
        this.support.firePropertyChange("position", old, this.position);
    }

    /**
     * <p>
     * If the player is in prison returns true.
     * </p>
     *
     * @return if the player is in prison
     */
    public boolean isInPrison() {
        return this.isInPrison;
    }

    /**
     * <p>
     * Set the prison flag.
     * </p>
     *
     * @param inPrison the boolean flag
     */
    public void setInPrison(final boolean inPrison) {
        isInPrison = inPrison;
    }

    /**
     * <p>
     * If the player is an evader returns true.
     * </p>
     *
     * @return if the player is in prison
     */
    public boolean isEvader() {
        return this.isEvader;
    }

    /**
     * <p>
     * Set the evader flag.
     * </p>
     *
     * @param isEvader the boolean flag
     */
    public void setEvader(final boolean isEvader) {
        this.isEvader = isEvader;
    }

    /**
     * <p>
     * Increment the amount of money the player evaded.
     * </p>
     *
     * @param amountEvaded the amount of money
     */
    public void incrementAmountEvaded(final double amountEvaded) {
        this.amountEvaded += amountEvaded;
    }

    /**
     * <p>
     * Returns the amount of money the player evaded.
     * </p>
     *
     * @return the amount of money
     */
    public double getAmountEvaded() {
        return this.amountEvaded;
    }

    /**
     * <p>
     * Set the amount of money the player evaded.
     * </p>
     *
     * @param amountEvaded the amount of money
     */
    public void setAmountEvaded(final double amountEvaded) {
        this.amountEvaded = amountEvaded;
    }

    /**
     * <p>
     * Get the times that the player tried to evade.
     * </p>
     *
     * @return the times the player tried to evade
     */
    public int getTimesTriedEvading() {
        return timesTriedEvading;
    }

    /**
     * <p>
     * Set the times the player tried evading from the prison.
     * </p>
     *
     * @param timesTriedEvading the times the player has already tried evading
     */
    public void setTimesTriedEvading(final int timesTriedEvading) {
        this.timesTriedEvading = timesTriedEvading;
    }

    /**
     * <p>
     * Getter for the symbol of the player.
     * </p>
     *
     * @return the symbol
     */
    public char getSymbol() {
        return symbol;
    }


    /**
     * <p>
     * Getter for the name of the player.
     * </p>
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * <p>
     * Getter for the balance of the player.
     * </p>
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
    public void receive(final double amount){
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
    public void pay(final double amount){
        if (amount < 1) {
            return;
        }
        this.balance -= amount;
    }

    /**
     * <p>
     * Determines whether the player can build a propriety,
     * which means that they own at least all proprieties of one color.
     * </p>
     *
     * @return true if the player can build, false otherwise
     */
    public boolean canBuild(){
        if (this.colorsOwned[Constant.BROWN_COLOR_INDEX] == Constant.BROWN_PROPRIETIES_AMOUNT) return true;
        if (this.colorsOwned[Constant.BLUE_COLOR_INDEX] == Constant.BLUE_PROPRIETIES_AMOUNT) return true;
        for (int i : this.colorsOwned){
            if (i == Constant.OTHER_PROPRIETIES_AMOUNT) return true;
        }
        return false;
    }

    /**
     * <p>
     * Adds a propriety to the colors owned array.
     * </p>
     *
     * @param cell the cell the player bought
     */
    public void addColor(final Cell cell) {
        if(cell instanceof ProprietyCell pc){
            int tmp = pc.getColor();
            if (indexColor(tmp) == -1) return;
            this.colorsOwned[indexColor(tmp)]++;
        }
    }

    /**
     * <p>
     * Shows the proprieties owned by the player on which they can build.
     * </p>
     *
     * @param buildOptions the cells the player can build upon
     */
    public void showBuildOptions(final Cell[] buildOptions){
        System.out.println("Choose where you want to build: ");
        for (int i = 0; i < buildOptions.length; i++) {
            if (buildOptions[i] != null)
                System.out.printf("%2s. %s%n", (i+1), buildOptions[i].getTitle());
        }
    }

    /**
     * <p>
     * Retrieves all the cells the player can build upon.
     * </p>
     *
     * @param board the board to look cells into
     * @return the cells the user can build upon
     */
    public Cell[] getBuildOptions(final Board board){
        final LinkedHashSet<Cell> buildOptions = new LinkedHashSet<>();
        if (this.colorsOwned[Constant.BROWN_COLOR_INDEX] == Constant.BROWN_PROPRIETIES_AMOUNT)
            buildOptions.addAll(board.getAllProprietiesOfColor(ANSIUtility.BROWN));
        if (this.colorsOwned[Constant.BLUE_COLOR_INDEX] == Constant.BLUE_PROPRIETIES_AMOUNT)
            buildOptions.addAll(board.getAllProprietiesOfColor(ANSIUtility.BLUE));
        for (int i = 1; i < this.colorsOwned.length; i++) {
            if (this.colorsOwned[i] == Constant.OTHER_PROPRIETIES_AMOUNT){
                buildOptions.addAll(board.getAllProprietiesOfColor(getColorAt(i)));
            }
        }
        return buildOptions.toArray(new Cell[0]);
    }

    /**
     * <p>
     * Converts an index in the {@link this#colorsOwned} array to a color code.
     * </p>
     *
     * @param index the index to look for
     * @return the color code corresponding to the index
     */
    private int getColorAt(final int index) {
        return switch (index) {
            case 0 -> ANSIUtility.BROWN;
            case 1 -> ANSIUtility.CYAN;
            case 2 -> ANSIUtility.MAGENTA;
            case 3 -> ANSIUtility.WHITE;
            case 4 -> ANSIUtility.RED;
            case 5 -> ANSIUtility.BRIGHT_YELLOW;
            case 6 -> ANSIUtility.GREEN;
            case 7 -> ANSIUtility.BLUE;
            default -> -1;
        };
    }

    /**
     * <p>
     * Converts a color code to an index in the {@link this#colorsOwned} array.
     * </p>
     *
     * @param color The color code to look for
     * @return the index in the array
     */
    private int indexColor(final int color){
        return switch (color) {
            case ANSIUtility.BROWN -> 0;
            case ANSIUtility.CYAN -> 1;
            case ANSIUtility.MAGENTA -> 2;
            case ANSIUtility.WHITE -> 3;
            case ANSIUtility.RED -> 4;
            case ANSIUtility.BRIGHT_YELLOW -> 5;
            case ANSIUtility.GREEN -> 6;
            case ANSIUtility.BLUE -> 7;
            default -> -1;
        };
    }

    /**
     * <p>
     * Returns the player's stats as a String.
     * </p>
     *
     * @return the player's stats
     */
    @Override
    public String toString(){
        return String.format("[%c: %s] Your Balance: %.2f%n", this.symbol, this.name, this.balance);
    }

    /**
     * <p>
     * Checks if the player is equal to another player.
     * </p>
     *
     * @param o the player to compare
     * @return true if the players are equal, false if not
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return this.symbol == player.symbol;
    }

    /**
     * <p>
     * Hashes the player, given their name and symbol.
     * </p>
     *
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.symbol);
    }

    /**
     * <p>
     * Adds a new listener to the {@code PropertyChange} trigger class ({@code this}).
     * </p>
     *
     * @param pcl a PropertyChangeListener object describing the event listener
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    /**
     * <p>
     * Removes a listener to the {@code PropertyChange} trigger class ({@code this}).
     * </p>
     *
     * @param pcl a PropertyChangeListener object describing the event listener
     */
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        this.support.removePropertyChangeListener(pcl);
    }
}
