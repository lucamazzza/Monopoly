package ch.supsi.game.monopoly.cells;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.Bank;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;

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
     * <p>
     * Constructor of the WealthTaxCell class.
     * </p>
     */
    public WealthTaxCell() {
        super("Wealth Tax");
        this.percentage = Constant.WEALTH_TAX_PERCENTAGE;
    }

    /**
     * <p>
     * Applies the effect of a specific cell on a player.
     * </p>
     * <p>
     * When the wealth tax is applied, the player pays
     * the {@link WealthTaxCell#percentage} of its balance
     * to the bank.
     * </p>
     *
     * @param player the player to apply the effect on.
     * @param game   the game to apply the effect on.
     */
    @Override
    public void applyEffect(final Player player, final Game game) {
        double tax = player.getBalance() / this.percentage;
        if (player.isEvader()) {
            player.incrementAmountEvaded(tax);
            ANSIUtility.printcf("As tax evader, you do not pay...%n", ANSIUtility.RED);
            return;
        }
        player.pay(tax);
        Bank.getInstance().deposit(tax);
        ANSIUtility.printcf("Paid %s$ to the bank.%n", ANSIUtility.BRIGHT_YELLOW, tax);
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
        return "Pay " + this.percentage + "%";
    }
}
