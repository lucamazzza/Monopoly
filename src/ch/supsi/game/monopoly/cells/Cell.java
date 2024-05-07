package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.movable.Player;

/**
 * <p>
 * This class represents a cell of the game "Monopoly".
 * </p>
 * <p>
 * Every cell has a list of players, currently on it.
 * </p>
 * <p>
 * See {@link ParkingCell}, {@link StartCell},
 * {@link ProprietyCell} and {@link LuxuryTaxCell}/{@link WealthTaxCell}
 * for concrete implementations and usage.
 * </p>
 *
 * @author Luca Mazza
 * @version 1.2.0
 */
public abstract class Cell {

    /**
     * The list of players currently on the cell.
     */
    private final Player[] players = new Player[Constant.PLAYER_NUMBER];

    /**
     * The title of the cell, used to display it on the board.
     */
    private final String title;
    private Player owner;

    /**
     * Constructor of the class.
     * <p>
     * As an abstract class, this is not used to create instances.
     * It is only used as a base class for the other cells, which
     * are concrete implementations.
     * </p>
     * <p>
     * For this reason this is declared as {@code Package-Private}
     * </p>
     * @param title the title of the cell
     */
    Cell(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public Player getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(Player owner) {
        if (owner == null)
            return;
        this.owner = owner;
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
     * Returns the name of the cell.
     *
     * <p>
     * Used to display the name of the cell on the board.
     * </p>
     *
     * @return the name of the cell
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the description of the cell.
     *
     * <p>
     * Used to display the detail of the cell on the board.
     * </p>
     *
     * @return the description of the cell
     */
    public abstract String getDetail();
}
