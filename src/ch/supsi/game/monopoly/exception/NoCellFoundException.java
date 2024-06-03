package ch.supsi.game.monopoly.exception;

/**
 * <p>
 * Exception thrown when no cell is found, given its name.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * throw new NoCellFoundException("Cell " + name + " not found");
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.4.0
 */
public class NoCellFoundException extends Exception{

    /**
     * <p>
     * Constructor for the {@link NoCellFoundException}.
     * </p>
     *
     * @param message the message to display
     */
    public NoCellFoundException(String message){
        super(message);
    }
}
