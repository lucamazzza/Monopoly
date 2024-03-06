package ch.supsi.game.monopoly;

public class Cell {
    private int fee;
    private CellType type;

    public Cell(int fee, CellType type){
        this.fee = fee;
        this.type = type;
    }

    public int getFee() {
        return fee;
    }

    public CellType getType() {
        return type;
    }
}
