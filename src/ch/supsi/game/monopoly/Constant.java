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
 * @version 1.3.0
 */
public final class Constant {

    // BANK ------------------------------------------------------------------------------------------------------------
    /**
     * The start amount of money in the bank.
     */
    public static final int BANK_START_AMOUNT = 1_000_000;

    // PLAYER ----------------------------------------------------------------------------------------------------------
    /**
     * The start amount of money of the player, given by the bank
     * at the start of the game.
     */
    public static final int PLAYER_START_AMOUNT = 2000;

    // BOARD -----------------------------------------------------------------------------------------------------------
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
    public static final int BOARD_WIDTH = 11;

    /**
     * The height of the board.
     */
    public static final int BOARD_HEIGHT = 11;

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
     * Prison cell position on the game loop board.
     */
    public static final int PRISON_POSITION = BOARD_WIDTH - 1;

    /**
     * Go to prison cell position on the game loop board.
     */
    public static final int GO_TO_PRISON_POSITION = (BOARD_WIDTH - 1) * 2 + (BOARD_HEIGHT - 1);

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
     * Number of prisons cells
     */
    public static final int PRISON_CELLS_QTY = 1;

    /**
     * Number of go to prisons cells
     */
    public static final int GO_TO_PRISON_CELLS_QTY = 1;

    /**
     * Number of extra cells.
     */
    public static final int EXTRA_CELLS_QTY = 6;

    /**
     * Number of propriety cells.
     */
    public static final int PROPRIETY_CELLS_QTY =
            BOARD_SIZE - TAX_CELLS_QTY - STATION_CELLS_QTY - START_CELLS_QTY - PARKING_CELLS_QTY - PRISON_CELLS_QTY - GO_TO_PRISON_CELLS_QTY - EXTRA_CELLS_QTY;

    // PROPRIETIES -----------------------------------------------------------------------------------------------------
    /**
     * The index of the brown color in the `colorsOwned` array in class `Player`.
     */
    public static final int BROWN_COLOR_INDEX = 0;

    /**
     * The index of the blue color in the `colorsOwned` array in class `Player`.
     */
    public static final int BLUE_COLOR_INDEX = 7;

    /**
     * The amount of the brown colored cells in the board.
     */
    public static final int BROWN_PROPRIETIES_AMOUNT = 2;

    /**
     * The amount of the blue colored cells in the board.
     */
    public static final int BLUE_PROPRIETIES_AMOUNT = 2;

    /**
     * The amount of the differently colored cells in the board, per color.
     */
    public static final int OTHER_PROPRIETIES_AMOUNT = 3;

    /**
     * The maximum number of houses on a cell.
     */
    public static final int MAX_NUMBER_HOUSES = 4;

    /**
     * The rent increase if the propriety has a hotel.
     */
    public static final int PROPRIETY_HOTEL_RENT_INCREASE = 100;

    /**
     * The rent increases for every house built on the propriety.
     */
    public static final int PROPRIETY_HOUSE_RENT_INCREASE = 15;

    /**
     * The tax to evade the prison.
     */
    public static final int PRISON_TAX = 50;


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