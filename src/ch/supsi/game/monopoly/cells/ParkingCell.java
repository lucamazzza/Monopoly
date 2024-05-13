package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.movable.Player;

/**
 * <p>
 * This class represents the Parking cell of the game "Monopoly".
 * </p>
 * <p>
 * The start has a neuter behaviour; once the player lands on it,
 * it has no effect on it.
 * </p>
 * <pre>
 * {@code
 * Cell parking = new ParkingCell();    // instantiate new ParkingCell w/ polymorphism
 * parking.applyEffect();               // does nothing
 * parking.getCellTitle();              // prints: "Parking"
 * }
 * </pre>
 *
 * @author Ivo Herceg
 * @version 1.2.0
 */
public class ParkingCell extends Cell {

    /**
     * Instantiates a new Parking cell.
     */
    public ParkingCell() {
        super("Parking");
    }

    /**
     * Instantiates a new Parking cell with a custom title
     * TEMPORARY, TO DISPLAY BLANK CELLS
     * @param title the title.
     */
    public ParkingCell(String title) {
        super(title);
    }

    /**
     * Applies the effect of a specific cell on a player.
     *
     * <p>
     * This method does nothing.
     * </p>
     *
     * @param player the player to apply the effect on.
     */
    @Override
    public void applyEffect(Player player) {
        // does nothing
    }

    /**
     * Returns the description of the cell.
     *
     * <p>
     * Used to display the detail of the cell on the board.
     * </p>
     *
     * @return the description of the cell
     */
    @Override
    public String getDetail() {
        return "";
    }
}
