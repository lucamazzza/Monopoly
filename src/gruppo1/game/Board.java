package gruppo1.game;

public class Board {
    private Cell[][] cells;

    public Board(int rows, int cols) {
        this.cells = new Cell[rows][cols];
    }
}
