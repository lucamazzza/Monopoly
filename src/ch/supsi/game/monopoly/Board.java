package ch.supsi.game.monopoly;

public class Board {

    /**
     *
     */
    private final Cell[][] boardCells;

    /**
     *
     */
    private final Cell[] cells;

    /**
     *
     * @param rows
     * @param cols
     */
    public Board(int rows, int cols) {
        rows = Math.max(rows, Constant.BOARD_HEIGHT);
        cols = Math.max(cols, Constant.BOARD_WIDTH);
        this.boardCells = new Cell[rows][cols];
        this.cells = new Cell[(rows - 1) * (cols - 1)];
        initBoard();
    }

    private void initBoard(){
        int row = this.boardCells.length - 1;
        int col = this.boardCells[0].length - 1;
        int rowAdd = 0;
        int colAdd = -1;
        Cell cell;
        for(int i = 0; i < cells.length; i++){
            if (i == 0) {
                cell = new Cell(CellType.START);
            } else {
                cell = new Cell(CellType.TOLL);
            }
            this.boardCells[row][col] = cell;
            this.cells[i] = cell;
            if (col == 0 && row == Constant.BOARD_HEIGHT - 1) {
                colAdd = 0;
                rowAdd = -1;
            } else if (col == 0 && row == 0){
                colAdd = 1;
                rowAdd = 0;
            } else if (col == Constant.BOARD_WIDTH-1 && row == 0) {
                colAdd = 0;
                rowAdd = 1;
            } else if (col == Constant.BOARD_WIDTH-1 && row == Constant.BOARD_HEIGHT) {
                colAdd = -1;
                rowAdd = 0;
            }
            col += colAdd;
            row += rowAdd;
        }
    }

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
                            detail = new StringBuilder(boardCells[row][col].getType().toString());
                        } else if (d == 1) {
                            detail = new StringBuilder(String.valueOf(boardCells[row][col].getFee()));
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

    public Cell[] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return generateBoard();
    }
}