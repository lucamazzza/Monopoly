package ch.supsi.game.monopoly.cells;

import ch.supsi.game.monopoly.Game;
import ch.supsi.game.monopoly.Player;
import ch.supsi.game.monopoly.cards.Card;

/**
 * <p>
 * This class represents the "Unexpected" cell of the game "Monopoly".
 * </p>
 * <p>
 * Once a player lands on this cell, he has to pick a card from the deck of
 * Chance cards and apply its effect.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Cell cell = new UnexpectedCell();   // instantiate a new Unexpected Cell
 * cell.getTitle();                    // get "Unexpected"
 * cell.getDetail();                   // get ""
 * cell.applyEffect(player);           // pick a card and apply its effect
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.3.0
 */
public class UnexpectedCell extends Cell{

    /**
     * <p>
     * Instantiates a new "Unexpected" cell.
     * </p>
     */
    public UnexpectedCell() {
        super("Unexpected");
    }

    /**
     * <p>
     * Applies the effect of a specific cell on a player.
     * </p>
     * <p>
     * Picks a card from the deck of Chance cards and applies its effect,
     * using method {@link super#selectCardAction(Game, Player, Card)} of
     * abstract class {@link Cell}.
     * </p>
     *
     * @param player the player to apply the effect on
     * @param game the game the cell is in
     */
    @Override
    public void applyEffect(final Player player, final Game game) {
        final Card card = game.pickCardFromUnexpectedDeck();
        super.selectCardAction(game, player, card);
    }

    /**
     * <p>
     * Returns the description of the cell.
     * </p>
     * <p>
     * Used to display the detail of the cell on the board.
     * </p>
     *
     * @return the description of the cell
     */
    @Override
    public String getDetail() {
        return "Pick an unexpected";
    }
}
