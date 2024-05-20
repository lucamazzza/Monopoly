package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Bank;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Game;
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
 * Cell cell = new StartCell();
 * cell.getTitle();                    // get "Luxury Tax"
 * cell.getDetail();                   // get "Get <tax_amount>.–"
 * cell.applyEffect(player);           // apply the effect of the start cell
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.2.0
 */
public class StartCell extends Cell{

    /**
     * The amount of money received on the start cell.
     */
    private final int receivedAmount;

    /**
     * <p>
     * Instantiate a new Start Cell.
     * </p>
     */
    public StartCell() {
        super("Start");
        this.receivedAmount = Constant.START_CELL_AMOUNT;
    }

    /**
     * <p>
     * Applies the effect of a specific cell on a player.
     * </p>
     * <p>
     * When the luxury tax is applied, the player pays
     * 200.– to the bank.
     * </p>
     *
     * @param player the player to apply the effect on.
     * @param game the game to apply the effect on.
     */
    @Override
    public void applyEffect(final Player player, final Game game) {
        player.receive(this.receivedAmount);
        Bank.getInstance().withdraw(this.receivedAmount);
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
        return "Get " + this.receivedAmount + ".–";
    }
}
