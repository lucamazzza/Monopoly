package ch.supsi.game.monopoly;

import ch.supsi.game.monopoly.cells.Cell;
import ch.supsi.game.monopoly.cells.ParkingCell;
import ch.supsi.game.monopoly.cells.ProprietyCell;
import ch.supsi.game.monopoly.cells.StartCell;

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
 * Board board = new Board(5,5);    // Instantiate a new board with 5 rows and 5 columns.
 * System.out.print(board);         // Print the board
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @author Luca Mazza
 * @author Ivo Herceg
 * @version 1.2.0
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
     *
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public Board(int rows, int cols) {
        rows = Math.max(rows, Constant.BOARD_HEIGHT);
        cols = Math.max(cols, Constant.BOARD_WIDTH);
        this.boardCells = new Cell[rows][cols];
        this.cells = new Cell[Constant.BOARD_SIZE];
        initBoard();
    }

    /**
     * <p>
     * Initializes the board.
     * </p>
     * <p>
     * Initializes the board, giving it a working form.
     * </p>
     * <p>
     * Starts by iterating on the length and width of the board, and
     * instantiating a `Cell` for each position.
     * </p>
     * <p>
     * The first cell, low-right, is the `START` cell, which gives the player
     * 100.-.
     * The other cells are all `TOLL` cells, which apply a random fee on the player.
     * </p>
     */
    private void initBoard(){
        Cell start = new StartCell();
        Cell parking = new ParkingCell();
        Cell nStation = new ProprietyCell();
        Cell sStation = new ProprietyCell();
        Cell eStation = new ProprietyCell();
        Cell wStation = new ProprietyCell();
        this.cells[Constant.START_POSITION] = start;
        this.cells[Constant.PARKING_POSITION] = parking;
        this.cells[Constant.NORTH_STATION_POSITION] = nStation;
        this.cells[Constant.SOUTH_STATION_POSITION] = sStation;
        this.cells[Constant.EAST_STATION_POSITION] = eStation;
        this.cells[Constant.WEST_STATION_POSITION] = wStation;
        for (int i = 0; i < Constant.TAX_CELLS_QTY; i++){
            // TODO: INIT TAX CELLS
        }
        for (int i = 0; i < Constant.PROPRIETY_CELLS_QTY; i++) {
            // TODO: INIT PROPRIETY CELLS
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
        for (int row = 0; row < Constant.BOARD_WIDTH; row++) {
            if (row == 0 || row == 1 || row == Constant.BOARD_WIDTH - 1) {
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
                            detail = new StringBuilder(boardCells[row][col].toString());
                        } else if (d == 1) {
                            //detail = new StringBuilder(String.valueOf(boardCells[row][col].getFee()));
                        } else if (d == Constant.CELL_DETAILS-1) {
                            for (int i = 0; i < boardCells[row][col].getPlayers().length; i++) {
                                if (boardCells[row][col].getPlayers()[i] != null) {
                                    detail.append(boardCells[row][col].getPlayers()[i].getSymbol()).append(" ");
                                }
                            }
                        }
                        sb.append(detail);
                        sb.append(" ".repeat(Math.max(0, 22 - detail.length())));
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
     * Method that returns the board, as a String.
     *
     * @return the output of {@link Board#generateBoard()}, as a string.
     */
    @Override
    public String toString() {
        return generateBoard();
    }
}