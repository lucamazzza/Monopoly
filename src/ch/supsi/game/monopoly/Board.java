package ch.supsi.game.monopoly;

public class Board {
    private Cell[][] cells;

    public Board(int rows, int cols) {
        this.cells = new Cell[rows][cols];
    }
}
