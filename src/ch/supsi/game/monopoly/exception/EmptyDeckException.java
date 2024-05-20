package ch.supsi.game.monopoly.exception;

/**
 * <p>
 * Exception thrown when the deck is empty and the user
 * tries to pick a card from it.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * throw new DeckEmptyException("Cannot pick a card from an empty deck");
 * }
 * </pre>
 *
 * @author Luca Mazza
 * @version 1.4.0
 */
public class EmptyDeckException extends Exception {

    /**
     * <p>
     * Constructor for the {@link EmptyDeckException}.
     * </p>
     *
     * @param message the message to display
     */
    public EmptyDeckException(String message) {
        super(message);
    }
}
