package ch.supsi.game.monopoly;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.cells.*;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * <p>
 * Class representing the board of the game "Monopoly".
 * </p>
 * <p>
 * The board contains {@link Cell}s, with their relative fees and types.
 * Players, on their turn, move around the board.
 * </p>
 * <p>
 * As for version 1.1.0, the board is defined in {@link Constant}, as a 16-cells
 * all-around matrix, on which players move clockwise, starting from the bottom right.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Board board = new Board(5,5);    // instantiate a new board with 5 rows and 5 columns.
 * System.out.print(board);         // print the board
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @author Luca Mazza
 * @author Ivo Herceg
 * @version 1.4.0
 */
public class Board {

    /**
     * <p>
     * The representation matrix of the board, made out of cells.
     * </p>
     * <p>
     * This field is in charge of managing how the board
     * is represented graphically on the console.
     * </p>
     */
    private final Cell[][] boardCells;

    /**
     * <p>
     * The working matrix of the board, made out of cells.
     * </p>
     * <p>
     * This field is in charge of managing how the players
     * move on the board.
     * </p>
     */
    private Cell[] cells;

    /**
     * Random generator instance.
     */
    private final Random random = new Random();

    /**
     * The different names a propriety cell can assume, instances of {@link ProprietyName}.
     */
    private static final ProprietyName[] nameBank = {
            // BROWN
            new ProprietyName("Short End", ANSIUtility.BROWN),
            new ProprietyName("Tight End", ANSIUtility.BROWN),
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
     * <p>
     * Constructor of the Board class.
     * </p>
     * <p>
     * The board size is set to `rows` x `cols`, given the two parameters.
     * </p>
     * <p>
     * If these values are set to less than the specified board size, in class
     * {@link Constant}, they are set to the specified board size.
     * </p>
     */
    public Board() {
        this.boardCells = new Cell[Constant.BOARD_HEIGHT][Constant.BOARD_WIDTH];
        this.cells = new Cell[Constant.BOARD_SIZE];
        initBoard();
    }

    /**
     * <p>
     * Returns a random rent between 50 and 150.
     * </p>
     *
     * @return the random rent
     */
    private int getRandomRent() {
        return this.random.nextInt(50, 150);
    }

    /**
     * <p>
     * Returns a random purchase price between 150 and 500.
     * </p>
     *
     * @return the random purchase price
     */
    private int getRandomPurchasePrice() {
        return this.random.nextInt(150, 500);
    }

    /**
     * <p>
     * Returns a random house building price between 75 and 125.
     * </p>
     *
     * @return the random house building price
     */
    private int getRandomHousePrice() {
        return this.random.nextInt(75, 125);
    }

    /**
     * <p>
     * Returns a random hotel building price between 95 and 175.
     * </p>
     *
     * @return the random hotel building price
     */
    private int getRandomHotelPrice() {
        return this.random.nextInt(95, 175);
    }

    /**
     * <p>
     * Initializes the board.
     * </p>
     * <p>
     * Initializes the board, giving it a working form.
     * </p>
     * <p>
     * Firstly, static-position cells are initialized,
     * like {@link ParkingCell}, {@link StartCell},
     * and {@link ProprietyCell} (stations).
     * Then the rest of the board is randomly generated.
     * </p>
     */
    private void initBoard(){
        this.cells[Constant.START_POSITION] = new StartCell();
        this.cells[Constant.PARKING_POSITION] = new ParkingCell();
        this.cells[Constant.PRISON_POSITION] = new PrisonCell();
        this.cells[Constant.GO_TO_PRISON_POSITION] = new GoToPrisonCell();
        this.initFixedProprietyCells();
        this.initTaxCells();
        this.initCardCells();
        this.initTaxEvasionCells();
        this.initRandomProprietyCells();
        int row = Constant.BOARD_HEIGHT - 1;
        int col = Constant.BOARD_WIDTH - 1;
        int rowAdd = 0;
        int colAdd = -1;
        for (int i = 0; i < Constant.BOARD_SIZE; i++) {
            this.boardCells[row][col] = this.cells[i];
            if (col == 0 && row == Constant.BOARD_HEIGHT - 1) {
                colAdd = 0;
                rowAdd = -1;
            } else if (col == 0 && row == 0) {
                colAdd = 1;
                rowAdd = 0;
            } else if (col == Constant.BOARD_WIDTH - 1 && row == 0) {
                colAdd = 0;
                rowAdd = 1;
            } else if (col == Constant.BOARD_WIDTH - 1 && row == Constant.BOARD_HEIGHT) {
                colAdd = -1;
                rowAdd = 0;
            }
            col += colAdd;
            row += rowAdd;
        }
    }

    /**
     * <p>
     * Initializes the tax cells ({@link WealthTaxCell} and {@link LuxuryTaxCell}).
     * </p>
     * <p>
     * The tax cells are inserted in random positions in the board.
     * </p>
     */
    private void initTaxCells() {
        int i = 0;
        while (i < Constant.TAX_CELLS_QTY){
            int pos = this.random.nextInt(1, Constant.BOARD_SIZE);
            if (this.cells[pos] != null) {
                continue;
            }
            if (i == 0) {
                this.cells[pos] = new LuxuryTaxCell();
            } else {
                this.cells[pos] = new WealthTaxCell();
            }
            i++;
        }
    }

    /**
     * <p>
     * Initializes the card cells ({@link ChanceCell} and {@link UnexpectedCell}).
     * </p>
     * <p>
     * The card cells are inserted in random positions in the board.
     * </p>
     */
    private void initCardCells() {
        int i = 0;
        while (i < Constant.CHANCE_CELLS_QTY - 1) {
            int pos = this.random.nextInt(1, Constant.BOARD_SIZE);
            if (this.cells[pos] != null) {
                continue;
            }
            this.cells[pos] = new ChanceCell();
            i++;
        }
        i = 0;
        while (i < Constant.UNEXPECTED_CELLS_QTY){
            int pos = this.random.nextInt(1, Constant.BOARD_SIZE);
            if(this.cells[pos] != null){
                continue;
            }
            this.cells[pos] = new UnexpectedCell();
            i++;
        }
    }

    private void initTaxEvasionCells() {
        int i = 0;
        while (i < Constant.EVADE_CELLS_QTY){
            int pos = this.random.nextInt(1, Constant.BOARD_SIZE);
            if (this.cells[pos] != null) {
                continue;
            }
            this.cells[pos] = new TaxEvasionCell();
            i++;
        }
    }
    /**
     * <p>
     * Initializes the fixed propriety cells ({@link ProprietyCell}).
     * </p>
     * <p>
     * The fixed propriety cells are inserted in fixed positions in the board,
     * given in the {@link Constant} class.
     * </p>
     */
    private void initFixedProprietyCells() {
        final Cell nStation = new ProprietyCell(
                new ProprietyName("North Station", ANSIUtility.DEFAULT), getRandomRent(),
                getRandomPurchasePrice(),
                getRandomHousePrice(),
                getRandomHotelPrice(),
                false
        );
        final Cell sStation = new ProprietyCell(
                new ProprietyName("South Station", ANSIUtility.DEFAULT),
                getRandomRent(),getRandomPurchasePrice(),
                getRandomHousePrice(),
                getRandomHotelPrice(),
                false
        );
        final Cell eStation = new ProprietyCell(
                new ProprietyName("East Station", ANSIUtility.DEFAULT),
                getRandomRent(),
                getRandomPurchasePrice(),
                getRandomHousePrice(),
                getRandomHotelPrice(),
                false
        );
        final Cell wStation = new ProprietyCell(
                new ProprietyName("West Station", ANSIUtility.DEFAULT),
                getRandomRent(),
                getRandomPurchasePrice(),
                getRandomHousePrice(),
                getRandomHotelPrice(),
                false
        );
        this.cells[Constant.NORTH_STATION_POSITION] = nStation;
        this.cells[Constant.SOUTH_STATION_POSITION] = sStation;
        this.cells[Constant.EAST_STATION_POSITION] = eStation;
        this.cells[Constant.WEST_STATION_POSITION] = wStation;
    }

    /**
     * <p>
     * Initializes the random propriety cells ({@link ProprietyCell}).
     * </p>
     * <p>
     * The random propriety cells are inserted in random positions in the board.
     * </p>
     */
    private void initRandomProprietyCells() {
        int i = 0;
        while (i < Constant.PROPRIETY_CELLS_QTY) {
            int pos = this.random.nextInt(1, Constant.BOARD_SIZE);
            if (this.cells[pos] != null) {
                continue;
            }
            int nameIndex;
            do {
                nameIndex = this.random.nextInt(0, nameBank.length);
            } while (nameBank[nameIndex].isBlacklisted());
            if (nameBank[nameIndex].getColor() == 0) {
                this.cells[pos] = new ProprietyCell(
                        nameBank[nameIndex],
                        getRandomRent(),
                        getRandomPurchasePrice(),
                        getRandomHousePrice(),
                        getRandomHotelPrice(),
                        false
                );
            } else {
                this.cells[pos] = new ProprietyCell(
                        nameBank[nameIndex],
                        getRandomRent(),
                        getRandomPurchasePrice(),
                        getRandomHousePrice(),
                        getRandomHotelPrice()
                );
            }
            nameBank[nameIndex].setBlacklisted(true);
            i++;
        }
    }

    /**
     * <p>
     * Generates a graphical representation of the board.
     * </p>
     * <p>
     * This method is in charge of giving a visual representation of the board
     * on the console.
     * It starts by iterating on the length and width of the board, and concatenating
     * different types of character, to build the structure of the board.
     * </p>
     * <p>
     * Finally, cell parameters are inserted inside the cells, like the type, the fee and
     * the player(s) currently on the cell.
     * </p>
     *
     * @return the graphical representation of the board, as a string.
     */
    private String generateBoard() {
        final StringBuilder sb = new StringBuilder();
        for (int row = 0; row < Constant.BOARD_HEIGHT; row++) {
            this.generateBoardFrame(row, sb);
            this.generateBoardContent(row, sb);
        }
        sb.append("-".repeat(Constant.CELL_WIDTH * Constant.BOARD_WIDTH));
        return sb.toString();
    }

    /**
     * <p>
     * Generates, in a {@link StringBuilder}, the frame of the board.
     * </p>
     *
     * @param row the row of the board
     * @param sb the {@link StringBuilder} that will contain the frame
     */
    private void generateBoardFrame(final int row, StringBuilder sb) {
        if (row == 0 || row == 1 || row == (Constant.BOARD_HEIGHT - 1)) {
            sb.append("-".repeat(Constant.CELL_WIDTH * Constant.BOARD_WIDTH));
        } else {
            sb.append("-".repeat(Constant.CELL_WIDTH));
            sb.append(" ".repeat(Constant.CELL_WIDTH * (Constant.BOARD_WIDTH - 2)));
            sb.append("-".repeat(Constant.CELL_WIDTH));
        }
        sb.append("\n");
    }

    /**
     * <p>
     * Generates, in a {@link StringBuilder}, the content of the board.
     * </p>
     *
     * @param row the row of the board
     * @param sb the {@link StringBuilder} that will contain the content
     */
    private void generateBoardContent(final int row, StringBuilder sb) {
        for (int d = 0; d < Constant.CELL_DETAILS; d++) {
            for (int col = 0; col < Constant.BOARD_WIDTH; col++) {
                if (this.boardCells[row][col] == null) {
                    sb.append(" ".repeat(Constant.CELL_WIDTH));
                    continue;
                }
                sb.append("|");
                String detail;
                if (this.boardCells[row][col] instanceof ProprietyCell)
                    detail = getProprietyCellDetail(row, col, d);
                else detail = getCellDetail(row, col, d);
                sb.append(detail);
                String tmp = ANSIUtility.decolorize(detail);
                sb.append(" ".repeat(Math.max(0, (Constant.CELL_WIDTH - 2) - tmp.length())));
                sb.append("|");

            }
            sb.append("\n");
        }
    }

    /**
     * <p>
     * Returns the detail of a cell, given the row and column
     * and the detail number.
     * </p>
     *
     * @param row the row of the board
     * @param col the column of the board
     * @param d the detail number
     * @return the detail, as a String
     */
    private String getCellDetail(int row, int col, int d) {
        switch (d) {
            case 0:
                return this.boardCells[row][col].getTitle();
            case 1:
                return String.valueOf(this.boardCells[row][col].getDetail());
            case 2, 3, 4:
                return "";
            default:
                StringBuilder detail = new StringBuilder();
                for (Player player : this.boardCells[row][col].getPlayers()) {
                    if (player != null)
                        if (player.isEvader())
                            detail.append(ANSIUtility.colorize(
                                    new String(player.getSymbol() + ""), ANSIUtility.RED)
                            ).append(" ");
                        else detail.append(player.getSymbol()).append(" ");
                }
                return detail.toString();
        }
    }

    /**
     * <p>
     * Returns the detail of a {@link ProprietyCell}, given the row and column
     * and the detail number.
     * </p>
     *
     * @param row the row of the board
     * @param col the column of the board
     * @param d the detail number
     * @return the detail, as a String
     */
    private String getProprietyCellDetail(int row, int col, int d) {
        final ProprietyCell pc = (ProprietyCell) boardCells[row][col];
        switch (d) {
            case 0: return pc.getTitle();
            case 1: return String.valueOf(pc.getDetail());
            case 2: return pc.getOwner() != null ?
                        "Owner " + pc.getOwner().getSymbol() :
                        "Buy " + pc.getPurchasePrice();
            case 3: return String.valueOf(pc.getBuildingPrice());
            case 4: return String.valueOf(pc.showBuildings());
            default:
                StringBuilder detail = new StringBuilder();
                for (Player player : pc.getPlayers()) {
                    if (player != null)
                        detail.append(player.getSymbol()).append(" ");
                }
                return detail.toString();
        }
    }

    /**
     * <p>
     * Getter for the working cell array.
     * </p>
     *
     * @return the working cell array.
     */
    public Cell[] getCells() {
        return cells;
    }

    /**
     * <p>
     * Setter for the working cell array.
     * </p>
     *
     * @param cells the cell array.
     */
    public void setCells(final Cell[] cells) {
        this.cells = cells;
    }

    /**
     * <p>
     * Returns a cell from the board given its index.
     * </p>
     *
     * @param index the index of the cell to return
     * @return the cell at the given index
     */
    public Cell getCell(final int index) {
        return this.cells[index];
    }

    /**
     * <p>
     * Returns the board, as a String.
     * </p>
     *
     * @return * the output of {@link Board#generateBoard()}, as a string.
     */
    @Override
    public String toString() {
        return generateBoard();
    }

    /**
     * <p>
     * Collects all cells of a given color and returns them in an HashSet.
     * </p>
     *
     * @param color the color to look for
     * @return the cells of the given color
     */
    public Set<Cell> getAllProprietiesOfColor(final int color) {
        final Set<Cell> cellsOfSameColor = new LinkedHashSet<>();
        for (Cell cell : cells) {
            if (cell instanceof ProprietyCell pc && (pc.getColor() == color))
                cellsOfSameColor.add(pc);
        }
        return cellsOfSameColor;
    }
}