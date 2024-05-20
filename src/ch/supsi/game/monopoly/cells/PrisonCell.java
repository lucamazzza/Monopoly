package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;

/**
 * <p>
 * This class represents the "Go-To Prison" cell of the game "Monopoly".
 * </p>
 * <p>
 * Only players sent by the {@link GoToPrisonCell} are effectively in prison.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Cell cell = new PrisonCell();       // instantiate a new Prison cell
 * cell.getTitle();                    // get "Go To Prison"
 * cell.getDetail();                   // get ""
 * cell.applyEffect(player);           // does nothing
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.3.0
 */
public class PrisonCell extends Cell{

    /**
     * <p>
     * Instantiate a new Prison Cell.
     * </p>
     */
    public PrisonCell() {
        super("Prison");
    }

    /**
     * <p>
     * The prison has no effect on the player who lands.
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
