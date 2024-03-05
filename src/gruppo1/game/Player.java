package gruppo1.game;

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
        return balance;
    }
    public void setBalance(int amount){
        balance = amount;
    }
    public void print(){
        System.out.println("gruppo1.game.Player: " + name + "(" + symbol + ")" + " Balance: " + balance);
    }
}
