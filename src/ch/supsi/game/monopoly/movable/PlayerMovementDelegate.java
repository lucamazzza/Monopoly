package ch.supsi.game.monopoly.movable;

import ch.supsi.game.monopoly.Constant;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Delegate managing the movement of the player.
 *
 * <p>
 * Manages the position of the player, triggering the changes
 * through a {@link java.beans.PropertyChangeEvent}.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * class Player implements Movable{
 *     // ...
 *     Movable delegate = new PlayerMovementDelegate();
 *     // ...
 *     public void move(int movement) {
 *         this.delegate.move(movement);
 *     }
 *     // ...
 *     // implemented methods
 *     // ...
 * }
 * }
 * </pre>
 *
 * @author Luca Mazza
 * @version 1.2.0
 */
public class PlayerMovementDelegate implements Movable {

    /**
     * The position of the player on the board.
     */
    private int position;

    /**
     * PropertyChange support class, which allows to fire
     * {@link java.beans.PropertyChangeEvent}s and trigger
     * behaviour in a {@link PropertyChangeListener}.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Moves the player by the specified number of cells.
     *
     * @param movement the number of cells to move
     */
    @Override
    public void move(int movement) {
        int old = this.position;
        this.position = (this.position + movement) % Constant.BOARD_SIZE;
        this.support.firePropertyChange("position", old, this.position);
    }

    /**
     * Returns the position of the player.
     * @return the position
     */
    @Override
    public int getPosition() {
        return this.position;
    }

    /**
     * Adds a new listener to the {@code PropertyChange} trigger class ({@code this}).
     *
     * @param pcl a PropertyChangeListener object describing the event listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }

    /**
     * Removes a listener to the {@code PropertyChange} trigger class ({@code this}).
     *
     * @param pcl a PropertyChangeListener object describing the event listener
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        this.support.removePropertyChangeListener(pcl);
    }
}
