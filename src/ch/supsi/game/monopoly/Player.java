package ch.supsi.game.monopoly;

public class Player {
    private String name;
    private char symbol;
    private int balance;
    private int position;
    public Player(String name, char symbol){
        this.name = name;
        this.symbol = symbol;
    }
    public int getPosition() {
        return this.position;
    }
    public void setPosition(int diceValue) {
        position = (position + diceValue) % 16 ;
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
    public void pay(int amount){
        if (amount < 1) {
            return;
        }
        this.balance -= amount;
    }
    @Override
    public String toString(){
        return String.format("[%c: %s] Your Balance: %d.–%n", this.symbol, this.name, this.balance);
    }
}
