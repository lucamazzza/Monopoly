package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Player;

/**
 * <p>
 * This class represents a cell of the game "Monopoly".
 * </p>
 * <p>
 * Every cell has a list of players, currently on it.
 * </p>
 * <p>
 * See {@link ParkingCell}, {@link StartCell},
 * {@link ProprietyCell} and {@link TaxCell}
 * for concrete implementations and usage.
 * </p>
 *
 * @author Andrea Masciocchi
 * @author Ivo Herceg
 * @author Luca Mazza
 * @version 1.2.0
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

    /**
     * Applies the effect of a specific cell on a player.
     * <p>
     * As an abstract implementation, you must implement
     * a version of this method for each concrete cell.
     * </p>
     * <p>
     * In this case it is mandatory to use Dynamic Dispatch
     * to apply the effect on a player, from a call of a
     * variable type `Cell`.
     * </p>
     *
     * @param player the player to apply the effect on.
     */
    public abstract void applyEffect(Player player);

    /**
     * Returns the Type Name of the cell.
     * <p>
     * Used to display the type of the cell on the board.
     * </p>
     * <p>
     * This method has a static behaviour, for `Start`
     * and `Parking` cells, even though it is not static,
     * as an `abstract` implementation is not compatible.
     * </p>
     */
    public abstract String getCellTitle();
}
