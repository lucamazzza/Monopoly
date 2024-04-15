package ch.supsi.game.monopoly.cells;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.movable.Player;

import static ch.supsi.game.monopoly.Bank.deposit;

/**
 * <p>
 * This class represents the Propriety cell of the game "Monopoly".
 * </p>
 * <p>
 * The cell has a unique name and a color.
 * </p>
 * <p>
 * The names of the different cells are defined in the {@link ProprietyCell#nameBank}
 * field, which contains {@link ProprietyName} objects.
 * </p>
 * <p>
 * Finally, every cell has a fee, assigned randomly
 * </p>
 * <pre>
 * {@code
 * Cell cell = new ProprietyCell(ProprietyCell.nameBank[i], 10);    // create a new ProprietyCell
 * cell.getTitle();                                                 // get the name of the cell
 * cell.getDetail();                                                // get the detail of the cell
 * cell.applyEffect(player);                                        // apply the effect of the cell
 * }
 * </pre>
 *
 * @author Luca Mazza
 * @author Ivo Herceg
 * @author Andrea Masciocchi
 * @version 1.2.0
 */
public class ProprietyCell extends Cell{

    /**
     * The name of the cell, as defined in {@link ProprietyCell#nameBank}.
     */
    private final ProprietyName name;

    /**
     * The different names a propriety cell can assume, instances of {@link ProprietyName}.
     * <p>
     * As it is a static field, it is accessible throughout the code, and is constant
     * so that accessibility issues are avoided.
     * </p>
     */
    public static final ProprietyName[] nameBank = {
            // BROWN
            new ProprietyName("Short End", ANSIUtility.YELLOW),
            new ProprietyName("Tight End", ANSIUtility.YELLOW),
            // CYAN
            new ProprietyName("Bastioni Gran Sasso", ANSIUtility.CYAN),
            new ProprietyName("Viale Monterosa", ANSIUtility.CYAN),
            new ProprietyName("Viale Vesuvio", ANSIUtility.CYAN),
            // PINK
            new ProprietyName("Via Accademia", ANSIUtility.MAGENTA),
            new ProprietyName("Corso Ateneo", ANSIUtility.MAGENTA),
            new ProprietyName("Piazza Università", ANSIUtility.MAGENTA),
            // GREY
            new ProprietyName("Via Verdi", ANSIUtility.WHITE),
            new ProprietyName("Corso Raffaello", ANSIUtility.WHITE),
            new ProprietyName("Piazza Dante", ANSIUtility.WHITE),
            // RED
            new ProprietyName("Via Marco Polo", ANSIUtility.RED),
            new ProprietyName("Corso Magellano", ANSIUtility.RED),
            new ProprietyName("Largo Colombo", ANSIUtility.RED),
            // YELLOW
            new ProprietyName("Viale Costantino", ANSIUtility.BRIGHT_YELLOW),
            new ProprietyName("Viale Traiano", ANSIUtility.BRIGHT_YELLOW),
            new ProprietyName("Piazza Giulio Cesare", ANSIUtility.BRIGHT_YELLOW),
            // GREEN
            new ProprietyName("Via Roma", ANSIUtility.GREEN),
            new ProprietyName("Corso Impero", ANSIUtility.GREEN),
            new ProprietyName("Largo Augusto", ANSIUtility.GREEN),
            // BLUE
            new ProprietyName("Viale dei Giardini", ANSIUtility.BLUE),
            new ProprietyName("Parco della Vittoria", ANSIUtility.BLUE),
            // BLACK
            new ProprietyName("Water Works", ANSIUtility.DEFAULT),
            new ProprietyName("Electric Company", ANSIUtility.DEFAULT)
    };

    /**
     * The rent of the cell.
     */
    private final int rent;

    /**
     * Instantiates a new ProprietyCell with a name and a rent.
     *
     * @param title the name of the cell
     * @param rent  the rent of the cell
     */
    public ProprietyCell(ProprietyName title, int rent) {
        super(title.getName());
        this.name = title;
        if (rent < 0) {
            throw new IllegalArgumentException("The rent must be positive.");
        }
        this.rent = rent;
    }

    /**
     * Applies the effect of a specific cell on a player.
     *
     * <p>
     * When a player lands on a cell, he must pay the rent to the bank.
     * </p>
     *
     * @param player the player to apply the effect on.
     */
    @Override
    public void applyEffect(Player player) {
        player.pay(this.rent);
        deposit(this.rent);
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
        return name.getName();
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
        return "Pay " + this.rent + ".–";
    }
}
