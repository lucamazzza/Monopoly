package ch.supsi.game.monopoly.cells;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;
import ch.supsi.game.monopoly.ScannerUtils;

public class TaxEvasionCell extends Cell {

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
     * As an abstract implementation, you must implement
     * a version of this method for each concrete cell.
     * </p>
     * <p>
     * In this case it is mandatory to use Dynamic Dispatch
     * to apply the effect on a player, from a call of a
     * variable type `Cell`.
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
