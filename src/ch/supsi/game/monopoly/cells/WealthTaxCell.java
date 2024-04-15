package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Bank;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.movable.Player;

/**
 * <p>
 * This class represents the "Wealth Tax" cell of the game "Monopoly".
 * </p>
 * <p>
 * The wealth tax imposes the player the payment of 200.–.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Cell cell = new WealthTaxCell();    // instantiate a new Luxury Tax Cell
 * cell.getTitle();                    // get "Wealth Tax"
 * cell.getDetail();                   // get "Pay <tax_percentage>%"
 * cell.applyEffect(player);           // apply the effect of the wealth tax
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.2.0
 */
public class WealthTaxCell extends Cell{

    /**
     * The percentage of the wealth tax.
     */
    private final double percentage;

    /**
     * Constructor of the WealthTaxCell class.
     */
    public WealthTaxCell() {
        super("Wealth Tax");
        this.percentage = Constant.WEALTH_TAX_PERCENTAGE;
    }

    /**
     * Applies the effect of a specific cell on a player.
     *
     * <p>
     * When the wealth tax is applied, the player pays
     * the {@link WealthTaxCell#percentage} of its balance
     * to the bank.
     * </p>
     *
     * @param player the player to apply the effect on.
     */
    @Override
    public void applyEffect(Player player) {
        double tax = player.getBalance() / this.percentage;
        player.pay(tax);
        Bank.deposit(tax);
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
        return "Pay " + this.percentage + "%";
    }
}