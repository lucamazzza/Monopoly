package ch.supsi.game.monopoly.cells;

import ch.mazluc.util.ANSIUtility;

/**
 * <p>
 * This class represents th name of Proprieties cell of the game "Monopoly".
 * </p>
 * <p>
 * A propriety name has a name and a color, and can be blacklisted.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * ProprietyName pName = new ProprietyName("Short End", ANSIUtility.BROWN); // create a new ProprietyName
 * pName.getName();                                                         // get the name of the cell colorized
 * pName.isBlacklisted();                                                   // check if the name is blacklisted
 * pName.setBlacklisted(true);                                              // set the name as blacklisted
 * }
 * </pre>
 *
 * @author Luca Mazza
 * @version 1.2.0
 */
public class ProprietyName {

    /**
     * The name of the propriety.
     */
    private final String name;

    /**
     * <p>
     * True if the propriety is blacklisted.
     * </p>
     * <p>
     * Blacklisted proprieties are used to
     * distinguish when a cell already exists.
     * </p>
     */
    private boolean blacklisted;

    /**
     * The color of the cell.
     */
    private final int color;

    /**
     * <p>
     * Instantiates a new Propriety name.
     * </p>
     * @param name the name of the propriety
     * @param color the color of the propriety
     */
    public ProprietyName(final String name, final int color) {
        this.name = name;
        this.color = color;
    }

    /**
     * <p>
     * Sets the name blacklisted or not.
     * </p>
     *
     * @param blacklisted true if the name has to be blacklisted
     */
    public void setBlacklisted(final boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    /**
     * <p>
     * Checks if the name is blacklisted or not.
     * </p>
     *
     * @return true if the name is blacklisted, false if not
     */
    public boolean isBlacklisted() {
        return this.blacklisted;
    }

    /**
     * <p>
     * Gets the name of the propriety.
     * </p>
     * <b>Warning</b>:
     * <p>
     * The colorization of a String adds ANSI hidden characters to
     * the string. It's {@code length()} and {@code charAt(n)}s are affected.
     * </p>
     *
     * @return the name of the propriety colorized.
     */
    public String getName() {
        return ANSIUtility.colorize(this.name, this.color);
    }

    /**
     * <p>
     * Returns the color of the cell.
     * </p>
     *
     * @return the color of the cell
     */
    public int getColor() {
        return color;
    }
}
