package ch.supsi.game.monopoly.cells;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;

/**
 * <p>
 * This class represents the "Go-To Prison" cell of the game "Monopoly".
 * </p>
 * <p>
 * The cell sends the player that lands on it to the {@link PrisonCell}.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Cell cell = new GoToPrisonCell();   // instantiate a new Go To Prison Cell
 * cell.getTitle();                    // get "Go To Prison"
 * cell.getDetail();                   // get ""
 * cell.applyEffect(player);           // move the player directly to prison
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.3.0
 */
public class GoToPrisonCell extends Cell{

    /**
     * Instantiates a new "Go to prison" cell.
     */
    public GoToPrisonCell() {
        super("Go To Prison");
    }

    /**
     * <p>
     * Move the player to the prison.
     * </p>
     *
     * @param player the player to apply the effect on.
     * @param game   the game to apply the effect on.
     */
    @Override
    public void applyEffect(final Player player, final Game game) {
        if (player.isEvader()) {

        }
        player.setPosition(Constant.PRISON_POSITION);
        player.setInPrison(true);
        ANSIUtility.printcf(
                "You are now in prison, to break out you need to roll for both dices the same number%n",
                ANSIUtility.BLUE
        );
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
