package ch.supsi.game.monopoly;

public class Player {
    private String name;
    private char symbol;
    private int balance;

    public Player(String name, char symbol){
        this.name = name;
        this.symbol = symbol;
        this.balance = Constant.PLAYER_START_AMOUNT;
    }

    public char getSymbol() {
        return symbol;
    }
    public String getName() {
        return name;
    }
    public int getBalance() {
        return this.balance;
    }
    public void receive(int amount){
        if (amount < 1) {
            return;
        }
        this.balance += amount;
    }
    }
    @Override
    public String toString(){
        return String.format("[%c: %s] Your Balance: %d.–%n", this.symbol, this.name, this.balance);
    }
    }
}
