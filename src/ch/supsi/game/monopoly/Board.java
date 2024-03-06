package ch.supsi.game.monopoly;

public class Board {
    private Cell[][] cells;

    public Board(int rows, int cols) {
        this.cells = new Cell[rows][cols];
    }

    private String generateBoard(){
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < Constant.BOARD_WIDTH; row++){
            if(row == 0 || row == 1 || row == Constant.BOARD_WIDTH){
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
                    if(cells[row][col] == null){
                        for(int i = 0; i < Constant.CELL_WIDTH; i++){
                            sb.append(" ");
                        }
                    }else{
                        sb.append("|");
                        sb.append(cells[row][col].getType().toString() + " " + cells[row][col].getFee() + "$");
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

    @Override
    public String toString() {
        return generateBoard();
    }
}