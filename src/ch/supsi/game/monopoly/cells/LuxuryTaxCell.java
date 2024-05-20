package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Bank;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;

/**
 * <p>
 * This class represents the "Luxury Tax" cell of the game "Monopoly".
 * </p>
 * <p>
 * The luxury tax imposes the player the payment of 200.–.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Cell cell = new LuxuryTaxCell();    // instantiate a new Luxury Tax Cell
 * cell.getTitle();                    // get "Luxury Tax"
 * cell.getDetail();                   // get "Pay <tax_amount>.–"
 * cell.applyEffect(player);           // apply the effect of the luxury tax
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.2.0
 */
public class LuxuryTaxCell extends Cell{

    /**
     * The amount of money to pay.
     */
    private final int tax;

    /**
     * <p>
     * Instantiates a new Luxury tax cell.
     * </p>
     */
    public LuxuryTaxCell() {
        super("Luxury Tax");
        this.tax = Constant.LUXURY_TAX_AMOUNT;
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
        player.pay(this.tax);
        Bank.getInstance().deposit(this.tax);
    }

    /**
     * <p>
     * Returns the name of the cell.
     * </p>
     * <p>
     * Used to display the name of the cell on the board.
     * </p>
     *
     * @return the name of the cell
     */
    @Override
    public String getTitle() {
        return "Luxury Tax";
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
        return "Pay " + this.tax + ".–";
    }
}
