package ch.supsi.game.monopoly.movable;

import java.beans.PropertyChangeListener;

/**
 * Interface for movable objects.
 *
 * <p>
 * A movable object, in this context the {@link Player} is
 * an object which has a position and, with this position
 * a behaviour can be triggered through a {@link PropertyChangeListener}.
 * </p>
 *
 * @author Luca Mazza
 * @version 1.2.0
 */
public interface Movable {

    /**
     * Moves the player by the specified number of cells.
     * @param movement the number of cells to move
     */
    void move(int movement);

    /**
     * Returns the position of the player.
     * @return the position
     */
    int getPosition();

    /**
     * Set the position of the player
     * @param position
     */
    void setPosition(int position);

    /**
     * Adds a new listener to the {@code PropertyChange} trigger class ({@code this}).
     *
     * @param pcl a PropertyChangeListener object describing the event listener
     */
    void addPropertyChangeListener(PropertyChangeListener pcl);

    /**
     * Adds a new listener to the {@code PropertyChange} trigger class ({@code this}).
     *
     * @param pcl a PropertyChangeListener object describing the event listener
     */
    void removePropertyChangeListener(PropertyChangeListener pcl);
}
