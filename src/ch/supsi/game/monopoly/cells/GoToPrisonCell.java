package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.movable.Player;

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
 * Cell cell = new GoToPrisonCell();   // instantiate a new Luxury Tax Cell
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
     * Move the player to the prison.
     *
     * @param player the player to apply the effect on.
     */
    @Override
    public void applyEffect(Player player) {
        player.setPosition(Constant.PRISON_POSITION);
        player.setInPrison(true);
        System.out.println("You are now in prison, to evade (like the Daltons) you need to roll for both dices the same number");
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
