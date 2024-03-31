package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Player;

/**
 * <p>
 * This class represents the Start cell of the game "Monopoly".
 * </p>
 * <p>
 * The start cell gives the player, each time they pass on it 100.–.
 * There is only one in the whole board.
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
public class StartCell extends Cell{
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
     * This method has a static behaviour even though
     * it is not static, as an `abstract` implementation
     * is not compatible.
     * </p>
     */
    @Override
    public String getCellTitle() {
        return "Start";
    }
}
