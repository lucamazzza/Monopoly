package ch.supsi.game.monopoly;

import ch.mazluc.util.ANSIUtility;
import ch.supsi.game.monopoly.cells.*;
import java.util.Random;

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
 * @version 1.3.0
 */
public class Board {

    /**
     * <p>
     * The representation matrix of the board, made out of cells.
     * </p>
     * This field is in charge of managing how the board
     * is represented graphically on the console.
     */
    private final Cell[][] boardCells;

    /**
     * <p>
     * The working matrix of the board, made out of cells.
     * </p>
     * This field is in charge of managing how the players
     * move on the board.
     */
    private final Cell[] cells;

    /**
     * Random generator instance.
     */
    private final Random random = new Random();

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
     * Returns a random rent between 50 and 150.
     *
     * @return the random rent
     */
    private int getRandomRent() {
        return this.random.nextInt(50, 150);
    }

    /**
     * Returns a random purchase price between 150 and 500.
     *
     * @return the random purchase price
     */
    private int getRandomPurchasePrice() {
        return this.random.nextInt(150, 500);
    }

    /**
     * Returns a random house building price between 75 and 125.
     *
     * @return the random house building price
     */
    private int getRandomHousePrice() {
        return this.random.nextInt(75, 125);
    }

    /**
     * Returns a random hotel building price between 95 and 175.
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
     * <p></p>
     */
    private void initBoard(){
        Cell start = new StartCell();
        Cell parking = new ParkingCell();
        Cell nStation = new ProprietyCell(
                new ProprietyName("North Station", ANSIUtility.DEFAULT), getRandomRent(),
                getRandomPurchasePrice(),
                getRandomHousePrice(),
                getRandomHotelPrice()
        );
        Cell sStation = new ProprietyCell(
                new ProprietyName("South Station", ANSIUtility.DEFAULT),
                getRandomRent(),getRandomPurchasePrice(),
                getRandomHousePrice(),
                getRandomHotelPrice()
        );
        Cell eStation = new ProprietyCell(
                new ProprietyName("East Station", ANSIUtility.DEFAULT),
                getRandomRent(),
                getRandomPurchasePrice(),
                getRandomHousePrice(),
                getRandomHotelPrice()
        );
        Cell wStation = new ProprietyCell(
                new ProprietyName("West Station", ANSIUtility.DEFAULT),
                getRandomRent(),
                getRandomPurchasePrice(),
                getRandomHousePrice(),
                getRandomHotelPrice()
        );
        Cell prison = new PrisonCell();
        Cell goToPrison = new GoToPrisonCell();
        this.cells[Constant.START_POSITION] = start;
        this.cells[Constant.PARKING_POSITION] = parking;
        this.cells[Constant.NORTH_STATION_POSITION] = nStation;
        this.cells[Constant.SOUTH_STATION_POSITION] = sStation;
        this.cells[Constant.EAST_STATION_POSITION] = eStation;
        this.cells[Constant.WEST_STATION_POSITION] = wStation;
        this.cells[Constant.PRISON_POSITION] = prison;
        this.cells[Constant.GO_TO_PRISON_POSITION] = goToPrison;
        for (int i = 0; i < Constant.TAX_CELLS_QTY; i++){
            int pos = this.random.nextInt(1, Constant.BOARD_SIZE);
            if (this.cells[pos] != null) {
                i--;
                continue;
            }
            if (i == 0) {
                this.cells[pos] = new LuxuryTaxCell();
            } else {
                this.cells[pos] = new WealthTaxCell();
            }
        }
        for (int i = 0; i < Constant.EXTRA_CELLS_QTY; i++) {
            int pos = this.random.nextInt(1, Constant.BOARD_SIZE);
            if (this.cells[pos] != null) {
                i--;
                continue;
            }
            this.cells[pos] = new ParkingCell();
        }
        for (int i = 0; i < Constant.PROPRIETY_CELLS_QTY; i++) {
            int pos = this.random.nextInt(1, Constant.BOARD_SIZE);
            if (this.cells[pos] != null) {
                i--;
                continue;
            }
            int nameIndex;
            do {
                nameIndex = this.random.nextInt(0, ProprietyCell.nameBank.length);
            } while (ProprietyCell.nameBank[nameIndex].isBlacklisted());
            this.cells[pos] = new ProprietyCell(
                    ProprietyCell.nameBank[nameIndex],
                    getRandomRent(),
                    getRandomPurchasePrice(),
                    getRandomHousePrice(),
                    getRandomHotelPrice()
            );
            ProprietyCell.nameBank[nameIndex].setBlacklisted(true);
        }
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
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < Constant.BOARD_HEIGHT; row++) {
            if (row == 0 || row == 1 || row == (Constant.BOARD_HEIGHT - 1)) {
                sb.append("-".repeat(Constant.CELL_WIDTH * Constant.BOARD_WIDTH));
            } else {
                sb.append("-".repeat(Constant.CELL_WIDTH));
                sb.append(" ".repeat(Constant.CELL_WIDTH * (Constant.BOARD_WIDTH - 2)));
                sb.append("-".repeat(Constant.CELL_WIDTH));
            }
            sb.append("\n");
            for (int d = 0; d < Constant.CELL_DETAILS; d++) {
                for (int col = 0; col < Constant.BOARD_WIDTH; col++) {
                    if (boardCells[row][col] == null) {
                        sb.append(" ".repeat(Constant.CELL_WIDTH));
                    } else {
                        sb.append("|");
                        StringBuilder detail = new StringBuilder();
                        if (d == 0) {
                            detail = new StringBuilder(boardCells[row][col].getTitle());
                        } else if (d == 1) {
                            detail = new StringBuilder(String.valueOf(boardCells[row][col].getDetail()));
                        } else if (d == 2) {
                            if (boardCells[row][col].getOwner()!=null) {
                                detail = new StringBuilder("Owner ");
                                detail.append(boardCells[row][col].getOwner().getSymbol());
                            } else {
                                if(boardCells[row][col] instanceof ProprietyCell tmp){
                                    detail = new StringBuilder("Buy ");
                                    detail.append(tmp.getPurchasePrice());
                                }
                            }
                        } else if (d == 3) {
                            if(boardCells[row][col] instanceof ProprietyCell tmp){
                                detail = new StringBuilder(String.valueOf(tmp.getBuildingPrice()));
                            }
                        }else if (d == 4) {
                            if(boardCells[row][col] instanceof ProprietyCell tmp){
                                detail = new StringBuilder(String.valueOf(tmp.showBuildings()));
                            }
                        } else {
                            for (int i = 0; i < boardCells[row][col].getPlayers().length; i++) {
                                if (boardCells[row][col].getPlayers()[i] != null) {
                                    detail.append(boardCells[row][col].getPlayers()[i].getSymbol()).append(" ");
                                }
                            }
                        }
                        sb.append(detail);
                        String tmp = detail.toString().replaceAll("\u001B\\[[\\d;]*[^\\d;]","");
                        sb.append(" ".repeat(Math.max(0, (Constant.CELL_WIDTH - 2) - tmp.length())));
                        sb.append("|");
                    }
                }
                sb.append("\n");
            }
        }
        sb.append("-".repeat(Constant.CELL_WIDTH * Constant.BOARD_WIDTH));
        return sb.toString();
    }

    /**
     * Getter for the working cell array.
     *
     * @return the working cell array.
     */
    public Cell[] getCells() {
        return cells;
    }

    /**
     * Returns a cell from the board given its index.
     *
     * @param index the index of the cell to return
     * @return the cell at the given index
     */
    public Cell getCell(int index) {
        return this.cells[index];
    }

    /**
     * Method that returns the board, as a String.
     * @return * the output of {@link Board#generateBoard()}, as a string.
     */
    @Override
    public String toString() {
        return generateBoard();
    }

    /**
     * Collects all cells of a given color and returns them.
     *
     * @param color the color to look for
     * @return the cells of the given color
     */
    public Cell[] getAllProprietiesOfColor(int color) {
        int cellsOfSameColorCounter;
        if (color == ANSIUtility.BROWN) cellsOfSameColorCounter = Constant.BROWN_PROPRIETIES_AMOUNT;
        else if (color == ANSIUtility.BLUE) cellsOfSameColorCounter = Constant.BLUE_PROPRIETIES_AMOUNT;
        else cellsOfSameColorCounter = Constant.OTHER_PROPRIETIES_AMOUNT;
        Cell[] cellsOfSameColor = new Cell[cellsOfSameColorCounter];
        int j = 0;
        for (Cell cell : cells) {
            if (cell instanceof ProprietyCell proprietyCell && (proprietyCell.getColor() == color))
                cellsOfSameColor[j++] = proprietyCell;
        }
        return cellsOfSameColor;
    }
}