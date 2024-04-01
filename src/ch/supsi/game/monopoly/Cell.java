package ch.supsi.game.monopoly;

import java.util.Random;

/**
 * <p>
 * This class represents a cell of the game "Monopoly".
 * </p>
 * <p>
 * Every cell has a fee, a type and a list of players, currently on it.
 * </p>
 * <p>
 * Toll cells have a random fee, starting from -150 to -50.
 * The start cell gives 100 to the player(s) passing it.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Cell cell = new Cell(CellType.TOLL);
 * cell.setPlayer(player);
 * cell.removePlayer(player);
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @author Ivo Herceg
 * @version 1.1.0
 */
public class Cell {

    /**
     * The fee of the cell.
     */
    private final int fee;

    /**
     * The type of the cell.
     */
    private final CellType type;

    /**
     * The list of players currently on the cell.
     */
    private final Player[] players;

    /**
     * <p>
     * Constructor of the `Cell` class.
     * </p>
     * <p>
     * Creates a new cell, given its type.
     * </p>
     * <p>
     * Starts by setting the type, and then the players list.
     * then, if the cell is of type `TOLL`, it sets a random fee,
     * otherwise, if it's of type `START`, it sets the fee to 100,
     * else it sets the fee to 0.
     * </p>
     *
     * @param type the type of the cell.
     */
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

    /**
     * Getter for the fee of the cell.
     *
     * @return the fee
     */
    public int getFee() {
        return fee;
    }

    /**
     * Getter for the type of the cell.
     *
     * @return the type
     */
    public CellType getType() {
        return type;
    }

    /**
     * Setter for the list of players currently on the cell.
     *
     * @param player the player to add to the list of players
     */
    public void setPlayer(Player player) {
        int i = 0;
        while (this.players[i] != null) {
            i++;
        }
        this.players[i] = player;
    }

    /**
     * Removes a player from the list of players.
     *
     * @param playerToRemove the player to remove
     */
    public void removePlayer(Player playerToRemove) {
        for (int i = 0; i < players.length; i++){
            if (players[i] == playerToRemove){
                players[i] = null;
            }
        }
    }

    /**
     * Getter for the list of players currently on the cell.
     *
     * @return the array of players on the cell
     */
    public Player[] getPlayers() {
        return players;
    }
}
