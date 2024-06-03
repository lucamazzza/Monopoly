package ch.supsi.game.monopoly.cells;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;
import ch.supsi.game.monopoly.ScannerUtils;

/**
 * <p>
 * This class represents the "Tax evasion" cell of the game "Monopoly".
 * </p>
 * <p>
 * When a player lands on it, they can become a tax evader.
 * A tax evader does not pay anything to other players or the bank until they are caught.
 * There can only be one tax evader per game and once the player is caught
 * they can no longer be a tax evader.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Cell cell = new TaxEvasionCell();   // instantiate a new Tax Evasion Cell
 * cell.getTitle();                    // get "Tax Evasion"
 * cell.getDetail();                   // get "Want to be naughty?"
 * cell.applyEffect(player);           // the player can choose to become a tax evader.
 * }
 * </pre>
 *
 * @author Luca Mazza
 * @version 1.4.1
 */
public class TaxEvasionCell extends Cell {

    /**
     * Flag that enables the cell effect.
     */
    private boolean active;
    /**
     * Constructor of the class.
     * <p>
     * The name of the cell is set to "Tax Evasion"
     * and the cell is set to active.
     * </p>
     */
    public TaxEvasionCell() {
        super(ANSIUtility.colorize("Tax Evasion", ANSIUtility.BRIGHT_RED));
        this.active = true;
    }

    /**
     * Applies the effect of a specific cell on a player.
     * <p>
     * Gives the option to the player to become a tax evader.
     * </p>
     *
     * @param player the player to apply the effect on
     * @param game   the game the cell is in
     */
    @Override
    public void applyEffect(Player player, Game game) {
        if (!this.active) return;
        ScannerUtils su = new ScannerUtils();
        ANSIUtility.printcf("Do you want to evade taxes? (y/n)%n", ANSIUtility.RED);
        player.setEvader(su.readBoolean());
        if (player.isEvader()) {
            ANSIUtility.printcf(Constant.TAX_EVASION_MESSAGE, ANSIUtility.RED);
            this.active = false;
        }
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
        return "Want to be naughty?";
    }
}
