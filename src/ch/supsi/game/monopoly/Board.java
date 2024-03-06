package ch.supsi.game.monopoly;

public class Board {
    private Cell[][] boardCells;
    private Cell[] cells;

    public Board(int rows, int cols) {
        this.boardCells = new Cell[rows][cols];
        this.cells = new Cell[(rows-1)*(cols-1)];

        int row = Constant.BOARD_HEIGHT-1;
        int col = Constant.BOARD_WIDTH-1;
        int rowAdd = 0;
        int colAdd = -1;
        Cell cell;
        for(int i = 0; i < cells.length; i++){
            if(i==0){
                cell = new Cell(CellType.START);
                this.boardCells[row][col] = cell;
                this.cells[i] = cell;
            }else {
                cell = new Cell(CellType.TOLL);
                this.boardCells[row][col] = cell;
                this.cells[i] = cell;
            }
            if(col == 0 && row == Constant.BOARD_HEIGHT-1){
                colAdd = 0;
                rowAdd = -1;
            }else if(col == 0 && row == 0){
                colAdd = 1;
                rowAdd = 0;
            }else if(col == Constant.BOARD_WIDTH-1 && row == 0){
                colAdd = 0;
                rowAdd = 1;
            }else if(col == Constant.BOARD_WIDTH-1 && row == Constant.BOARD_HEIGHT){
                colAdd = -1;
                rowAdd = 0;
            }
            col+=colAdd;
            row+=rowAdd;
        }
    }

    private String generateBoard(){
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < Constant.BOARD_WIDTH; row++){
            if(row == 0 || row == 1 || row == Constant.BOARD_WIDTH-1 || row == Constant.BOARD_WIDTH){
                for(int i = 0; i < Constant.CELL_WIDTH*Constant.BOARD_WIDTH; i++){
                    sb.append("-");
                }
            }else{
                for(int i = 0; i < Constant.CELL_WIDTH; i++){
                    sb.append("-");
                }
                for(int i = 0; i < Constant.CELL_WIDTH*(Constant.BOARD_WIDTH-2); i++){
                    sb.append(" ");
                }
                for(int i = 0; i < Constant.CELL_WIDTH; i++){
                    sb.append("-");
                }
            }
            sb.append("\n");

            for(int d = 0; d < Constant.CELL_DETAILS; d++){
                for(int col = 0; col < Constant.BOARD_WIDTH; col++){
                    if(boardCells[row][col] == null){
                        for(int i = 0; i < Constant.CELL_WIDTH; i++){
                            sb.append(" ");
                        }
                    }else{
                        sb.append("|");
                        String detail = "";
                        if(d == 0){
                            detail = boardCells[row][col].getType().toString();
                        }else if(d == 1){
                            detail = String.valueOf(boardCells[row][col].getFee());
                        }else if(d == Constant.CELL_DETAILS-1){
                            for(int i = 0; i < boardCells[row][col].getPlayers().length; i++){
                                if(boardCells[row][col].getPlayers()[i] != null) {
                                    detail += boardCells[row][col].getPlayers()[i].getSymbol() + " ";
                                }
                            }
                        }
                        sb.append(detail);
                        for(int i = 0; i < 22-detail.length(); i++){
                            sb.append(" ");
                        }
                        sb.append("|");
                    }
                }
                sb.append("\n");
            }
        }
        for(int i = 0; i < Constant.CELL_WIDTH*Constant.BOARD_WIDTH; i++){
            sb.append("-");
        }
        return sb.toString();
    }

    private String debugBoard(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < boardCells.length; i++){
            for(int j = 0; j < boardCells[i].length; j++){
                if(boardCells[i][j] != null) {
                    sb.append("cella");
                }else{
                    sb.append("     ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Cell[] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        //return debugBoard();
        return generateBoard();
    }
}