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
}