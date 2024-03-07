package ch.supsi.game.monopoly;

import java.util.Random;

public class Cell {
    private int fee;
    private CellType type;
    private Player[] players;

    public Cell(CellType type){
        this.type = type;
        this.players = new Player[Constant.PLAYER_NUMBER];
        if(this.type == CellType.TOLL){
            Random random = new Random();
            this.fee = random.nextInt(-150,-50);
        }else if(this.type == CellType.START){
            this.fee = 100;
        }else{
            this.fee = 0;
        }
    }

    public int getFee() {
        return fee;
    }

    public CellType getType() {
        return type;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }
}
