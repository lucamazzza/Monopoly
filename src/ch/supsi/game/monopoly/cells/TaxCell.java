package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Player;

/**
 * <p>
 * This class represents the Tax cell of the game "Monopoly".
 * </p>
 * <p>
 * There are two types of Tax cell, with different fees.
 * The lux tax, imposes the player the payment of 200.–.
 * The income tax, imposes the player the payment of 10% of its balance.
 * </p>
 * <pre>
 * {@code
 * TODO: ADD EXAMPLES
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.2.0
 */
public class TaxCell extends Cell {
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
     * This method has a static behaviour, for `Start`
     * and `Parking` cells, even though it is not static,
     * as an `abstract` implementation is not compatible.
     * </p>
     */
    @Override
    public String getCellTitle() {
        // TODO: IMPLEMENT
        return null;
    }
}
