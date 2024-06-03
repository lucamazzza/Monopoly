package ch.supsi.game.monopoly.exception;

/**
 * <p>
 * Exception thrown when an illegal card is inserted in a deck.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * throw new IllegalCardException("Cards cannot be null");
 * }
 * </pre>
 *
 * @author Luca Mazza
 * @version 1.4.0
 */
public class IllegalCardException extends Exception {

    /**
     * <p>
     * Constructor for {@link IllegalCardException}.
     * </p>
     *
     * @param message the message to display
     */
    public IllegalCardException(String message){
        super(message);
    }
}
