package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;

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
     * <p>
     * Instantiates a new Parking cell.
     * </p>
     */
    public ParkingCell() {
        super("Parking");
    }

    /**
     * <p>
     * Applies the effect of a specific cell on a player.
     * </p>
     * <p>
     * This method does nothing.
     * </p>
     *
     * @param player the player to apply the effect on.
     * @param game the game to apply the effect on.
     */
    @Override
    public void applyEffect(final Player player, final Game game) {
        // does nothing
    }

    /**
     * <p>
     * Returns the description of the cell.
     * </p>
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
