package ch.supsi.game.monopoly.movable;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.Board;
import ch.supsi.game.monopoly.Constant;
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
     * Determines whether or not the player can build a propriety,
     * which means that they own at least all proprieties of one color.
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
     * Adds a propriety to the colors owned array.
     *
     * @param cell the cell the player bought
     */
    public void addColor(Cell cell) {
        if(cell instanceof ProprietyCell pc){
            int tmp = pc.getColor();
            if (indexColor(tmp) == -1) return;
            this.colorsOwned[indexColor(tmp)]++;
        }
    }

    /**
     * Shows the proprieties owned by the player on which they can build.
     *
     * @param buildOptions the cells the player can build upon
     */
    public void showBuildOptions(Cell[] buildOptions){
        System.out.println("Choose where you want to build: ");
        for (int i = 0; i < buildOptions.length; i++) {
            if (buildOptions[i] != null)
                System.out.printf("%2s. %s%n", (i+1), buildOptions[i].getTitle());
        }
    }

    /**
     * Counts the proprieties the player can build upon.
     *
     * @return the count of proprieties.
     */
    public int getBuildableProprietiesCount() {
        int buildableProprietiesCounter = 0;
        if (this.colorsOwned[Constant.BROWN_COLOR_INDEX] == Constant.BROWN_PROPRIETIES_AMOUNT)
            buildableProprietiesCounter += Constant.BROWN_PROPRIETIES_AMOUNT;
        if (this.colorsOwned[Constant.BLUE_COLOR_INDEX] == Constant.BLUE_PROPRIETIES_AMOUNT)
            buildableProprietiesCounter += Constant.BLUE_PROPRIETIES_AMOUNT;
        for (int j : this.colorsOwned) {
            if (j == Constant.OTHER_PROPRIETIES_AMOUNT)
                buildableProprietiesCounter += Constant.OTHER_PROPRIETIES_AMOUNT;
        }
        return buildableProprietiesCounter;
    }

    /**
     * Retrieves all the cells the player can build upon.
     *
     * @param board the board to look cells into
     * @return the cells the user can build upom
     */
    public Cell[] getBuildOptions(Board board){
        int buildableProprietiesCounter = getBuildableProprietiesCount();
        Cell[] buildOptions = new Cell[buildableProprietiesCounter];
        int buildOptionIndex = 0;
        Cell[] temp;
        if (this.colorsOwned[Constant.BROWN_COLOR_INDEX] == Constant.BROWN_PROPRIETIES_AMOUNT) {
            temp = board.getAllProprietiesOfColor(ANSIUtility.BROWN);
            for (Cell cell : temp) {
                buildOptions[buildOptionIndex] = cell;
                buildOptionIndex++;
            }
        }
        if (this.colorsOwned[Constant.BLUE_COLOR_INDEX] == Constant.BLUE_PROPRIETIES_AMOUNT) {
            temp = board.getAllProprietiesOfColor(ANSIUtility.BLUE);
            for (Cell cell : temp) {
                buildOptions[buildOptionIndex] = cell;
                buildOptionIndex++;
            }
        }
        for (int i = 1; i < this.colorsOwned.length; i++) {
            if (this.colorsOwned[i] == Constant.OTHER_PROPRIETIES_AMOUNT){
                temp = board.getAllProprietiesOfColor(getColorAt(i));
                for (Cell cell : temp) {
                    buildOptions[buildOptionIndex] = cell;
                    if(buildOptionIndex != buildableProprietiesCounter - 1)
                        buildOptionIndex++;
                }
            }
        }
        return buildOptions;
    }

    /**
     * Converts an index in the {@link this#colorsOwned} array to a color code.
     *
     * @param index the index to look for
     * @return the color code corresponding to the index
     */
    private int getColorAt(int index) {
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
     * Converts a color code to an index in the {@link this#colorsOwned} array.
     *
     * @param color The color code to look for
     * @return the index in the array
     */
    private int indexColor(int color){
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
