package ch.supsi.game.monopoly.cells;

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
 * TODO: ADD EXAMPLES
 * }
 * </pre>
 *
 * @author Ivo Herceg
 * @version 1.2.0
 */
public class ParkingCell extends Cell {
    // TODO: IMPLEMENT

    /**
     * Applies the effect of a specific cell on a player.
     *
     * <p>
     * TODO: EXPLAIN THE EFFECT
     * </p>
     *
     * @param player the player to apply the effect on.
     */
    @Override
    public void applyEffect(Player player) {
        // TODO: IMPLEMENT
    }

    /**
     * Returns the Type Name of the cell.
     * <p>
     * Used to display the type of the cell on the board.
     * </p>
     * <p>
     * This method has a static behaviour, even though
     * it is not static, as an `abstract` implementation
     * is not compatible.
     * </p>
     */
    @Override
    public String getCellTitle() {
        return "Parking";
    }
}
