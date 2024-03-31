package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Player;

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
 *
 * @author Andrea Masciocchi
 * @author Ivo Herceg
 * @version 1.1.0
 */
public abstract class Cell {

    /**
     * The list of players currently on the cell.
     */
    private final Player[] players = new Player[Constant.PLAYER_NUMBER];

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

    public abstract void applyEffect(Player player);

    public abstract String getCellTitle();
}
