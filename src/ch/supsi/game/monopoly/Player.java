package ch.supsi.game.monopoly;

/**
 * <p>
 * This class represents a player.
 * </p>
 * <p>
 * The player has a name, a symbol, a balance and a position on the board.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Player player = new Player("Luca",'L');  // Instantiate a new Player
 * player.setPosition(0);                   // Set the players position
 * player.receive(100);                     // Receive some money
 * player.pay(50);                          // Pay some money
 * System.out.println(player);              // Print the player's stats
 * }
 * </pre>
 *
 * @author Ivo Herceg
 * @version 1.1.0
 */
public class Player {

    /**
     * The name of the player.
     */
    private final String name;

    /**
     * The symbol of the player.
     */
    private final char symbol;

    /**
     * The balance of the player.
     */
    private int balance;

    /**
     * The position of the player on the board.
     */
    private int position;

    /**
     * <p>
     * Constructor of the Player class.
     * </p>
     * <p>
     * Firstly the player is given a name and symbol. These are checked for empty or
     * blank values and then the player is created (by the arguments).
     * </p>
     *
     * @param name the name of the player
     * @param symbol the symbol of the player
     */
    public Player(String name, char symbol){
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Player name must not be empty or blank.");
        }
        if (symbol < 21) {
            throw new IllegalArgumentException("Player symbol must not be a blank character");
        }
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Getter for the position of the player on the board.
     *
     * @return the position of the player
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Setter for the position of the player on the board.
     *
     * @param diceValue the value of the dice
     */
    public void setPosition(int diceValue) {
        position = (position + diceValue) % 16 ;
    }

    /**
     * Getter for the symbol of the player.
     *
     * @return the symbol
     */
    public char getSymbol() {
        return symbol;
    }


    /**
     * Getter for the name of the player.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Getter for the balance of the player.
     *
     * @return the balance
     */
    public int getBalance() {
        return this.balance;
    }


    /**
     * <p>
     * Method in charge of handling the player's receiving money.
     * </p>
     * <p>
     * When called, the balance of the player is increased by the amount of money
     * received.
     * </p>
     *
     * @param amount the amount of money received
     */
    public void receive(int amount){
        if (amount < 1) {
            return;
        }
        this.balance += amount;
    }


    /**
     * <p>
     * Method in charge of handling the player's paying money.
     * </p>
     * <p>
     * When called, the balance of the player is decreased by the amount of money
     * asked.
     * </p>
     *
     * @param amount the amount of money paid
     */
    public void pay(int amount){
        if (amount < 1) {
            return;
        }
        this.balance -= amount;
    }

    /**
     * Returns the player's stats as a String.
     *
     * @return the player's stats
     */
    @Override
    public String toString(){
        return String.format("[%c: %s] Your Balance: %d.–%n", this.symbol, this.name, this.balance);
    }

    /**
     * Checks if the player is equal to another player.
     *
     * @param obj the player to compare
     * @return true if the players are equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.symbol == ((Player) obj).getSymbol();
    }
}
