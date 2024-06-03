package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Bank;
import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;
import ch.supsi.game.monopoly.cards.Card;
import ch.supsi.game.monopoly.exception.NoCellFoundException;

/**
 * <p>
 * This class represents a cell of the game "Monopoly".
 * </p>
 * <p>
 * Every cell has a list of players, currently on it.
 * </p>
 * <p>
 * See {@link ParkingCell}, {@link StartCell},
 * {@link ProprietyCell}, {@link LuxuryTaxCell}/{@link WealthTaxCell},
 * {@link PrisonCell}, {@link GoToPrisonCell} and {@link ChanceCell}/
 * {@link UnexpectedCell} for concrete implementations and usage.
 * </p>
 *
 * @author Luca Mazza
 * @version 1.3.0
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

    /**
     * The owner of the cell. {@code null} if owned by the bank or not own able.
     */
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
     * Returns the current owner of the cell.
     *
     * @return the owner
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Sets the owner of the cell.
     *
     * @param owner the owner
     */
    public void setOwner(final Player owner) {
        this.owner = owner;
    }

    /**
     * Setter for the list of players currently on the cell.
     *
     * @param player the player to add to the list of players
     */
    public void setPlayer(final Player player) {
        int i = 0;
        while (this.players[i] != null) i++;
        this.players[i] = player;
    }

    /**
     * Removes a player from the list of players.
     *
     * @param playerToRemove the player to remove
     */
    public void removePlayer(final Player playerToRemove) {
        for (int i = 0; i < players.length; i++){
            if (players[i] != null && players[i].equals(playerToRemove))
                players[i] = null;
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
     * @param player the player to apply the effect on
     * @param game the game the cell is in
     */
    public abstract void applyEffect(final Player player, final Game game);

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

    /**
     * Performs the action of the card on the player.
     *
     * @param game The game instance the cell is in
     * @param player The player on the cell
     * @param card The card to use
     */
    void selectCardAction(final Game game, final Player player, final Card card) {
        System.out.println(card);
        switch (card.getCardAction()) {
            case GO_TO:
                int previousPosition = player.getPosition();
                if (card.getCellName().equalsIgnoreCase("prison")) {
                    player.setPosition(Constant.PRISON_POSITION);
                    player.setInPrison(true);
                } else {
                    try {
                        player.setPosition(game.getCellIndexByName(card.getCellName()));
                    } catch (NoCellFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (game.hasPlayerPassedStart() && game.hasPlayerPassedStartWithCards(previousPosition)) {
                    player.receive(Constant.START_CELL_AMOUNT);
                    Bank.getInstance().withdraw(Constant.START_CELL_AMOUNT);
                }
                break;
            case PAY:
                player.pay(card.getAmount());
                Bank.getInstance().deposit(card.getAmount());
                break;
            case RECEIVE:
                player.receive(card.getAmount());
                Bank.getInstance().withdraw(card.getAmount());
                break;
        }
    }
}
