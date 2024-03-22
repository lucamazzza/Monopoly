package ch.supsi.game.monopoly;

/**
 * <p>
 * Constants storage utility class.
 * </p>
 * <p>
 * This class is in charge of storing all the constants used in the game.
 * </p>
 *
 * @author Andrea Masciocchi
 * @author Ivo Herceg
 * @version 1.1.0
 */
public final class Constant {

    /**
     * The start amount of money in the bank.
     */
    public static final int BANK_START_AMOUNT = 1_000_000;

    /**
     * The start amount of money of the player, given by the bank
     * at the start of the game.
     */
    public static final int PLAYER_START_AMOUNT = 2000;

    /**
     * The number of players in the game.
     */
    public static final int PLAYER_NUMBER = 2;

    /**
     * The minimum value of the dice.
     */
    public static final int DICE_MIN_VALUE = 1;

    /**
     * The maximum value of the dice.
     */
    public static final int DICE_MAX_VALUE = 6;

    /**
     * The width of the board.
     */
    public static final int BOARD_WIDTH = 5;

    /**
     * The height of the board.
     */
    public static final int BOARD_HEIGHT = 5;

    /**
     * The width of the cell.
     */
    public static final int CELL_WIDTH = 24;

    /**
     * The number of descriptive rows contained by the cell.
     */
    public static final int CELL_DETAILS = 5;

    /**
     * <p>
     * Private constructor for utility class.
     * </p>
     * <p>
     * Prevents the instantiation of this class, as a utility class.
     * </p>
     */
    private Constant() {
        throw new IllegalStateException("Utility class");
    }
}