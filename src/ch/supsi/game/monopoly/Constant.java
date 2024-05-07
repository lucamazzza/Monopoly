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
     * The start amount of money to receive in the start cell.
     */
    public static final int START_CELL_AMOUNT = 100;

    /**
     * The amount of money to pay in the luxury tax.
     */
    public static final int LUXURY_TAX_AMOUNT = 100;

    /**
     * The percentage of the wealth tax.
     */
    public static final int WEALTH_TAX_PERCENTAGE = 10;

    /**
     * The number of players in the game.
     */
    public static final int PLAYER_NUMBER = 4;

    /**
     * The number of dices in the game.
     */
    public static final int NUMBER_OF_DICES = 2;

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
    public static final int BOARD_WIDTH = 9;

    /**
     * The height of the board.
     */
    public static final int BOARD_HEIGHT = 9;

    /**
     * The width of the cell.
     */
    public static final int CELL_WIDTH = 24;

    /**
     * The number of descriptive rows contained by the cell.
     */
    public static final int CELL_DETAILS = 6;

    /**
     * The total number of cells in the board.
     */
    public static final int BOARD_SIZE = (BOARD_WIDTH - 1) * 2 + (BOARD_HEIGHT - 1) * 2;

    /**
     * Start cell position on the game loop board.
     */
    public static final int START_POSITION = 0;

    /**
     * Parking cell position on the game loop board.
     */
    public static final int PARKING_POSITION = (BOARD_WIDTH - 1) + (BOARD_HEIGHT - 1);

    /**
     * South Station cell position on the game loop board.
     */
    public static final int SOUTH_STATION_POSITION = (int) Math.ceil(BOARD_WIDTH / 2.0) - 1;

    /**
     * West Station cell position on the game loop board.
     */
    public static final int WEST_STATION_POSITION = SOUTH_STATION_POSITION + (BOARD_WIDTH - 1)/2 + (BOARD_HEIGHT - 1)/2;

    /**
     * North Station cell position on the game loop board.
     */
    public static final int NORTH_STATION_POSITION = WEST_STATION_POSITION + (BOARD_WIDTH - 1)/2 + (BOARD_HEIGHT - 1)/2;

    /**
     * East Station cell position on the game loop board.
     */
    public static final int EAST_STATION_POSITION = NORTH_STATION_POSITION + (BOARD_WIDTH - 1)/2 + (BOARD_HEIGHT - 1)/2;

    /**
     * Number of tax cells.
     */
    public static final int TAX_CELLS_QTY = 2;

    /**
     * Number of station cells.
     */
    public static final int STATION_CELLS_QTY = 4;

    /**
     * Number of start cells.
     */
    public static final int START_CELLS_QTY = 1;

    /**
     * Number of parking cells.
     */
    public static final int PARKING_CELLS_QTY = 1;

    /**
     * Number of propriety cells.
     */
    public static final int PROPRIETY_CELLS_QTY =
            BOARD_SIZE - TAX_CELLS_QTY - STATION_CELLS_QTY - START_CELLS_QTY - PARKING_CELLS_QTY;

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