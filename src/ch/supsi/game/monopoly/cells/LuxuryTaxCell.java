package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Bank;
import ch.supsi.game.monopoly.Constant;
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
     * Instantiates a new Luxury tax cell.
     */
    public LuxuryTaxCell() {
        super("Luxury Tax");
        this.tax = Constant.LUXURY_TAX_AMOUNT;
    }

    /**
     * Applies the effect of a specific cell on a player.
     *
     * <p>
     * When the luxury tax is applied, the player pays
     * 200.– to the bank.
     * </p>
     *
     * @param player the player to apply the effect on.
     */
    @Override
    public void applyEffect(Player player) {
        player.pay(this.tax);
        Bank.deposit(this.tax);
    }

    /**
     * Returns the name of the cell.
     *
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
        return "Pay " + this.tax + ".–";
    }
}
