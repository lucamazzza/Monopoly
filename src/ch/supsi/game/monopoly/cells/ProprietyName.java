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
     * True if the propriety is blacklisted.
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
     * Instantiates a new Propriety name.
     *
     * @param name the name of the propriety
     * @param color the color of the propriety
     */
    public ProprietyName(String name, int color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Sets the name blacklisted or not.
     *
     * @param blacklisted true if the name has to be blacklisted
     */
    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    /**
     * Checks if the name is blacklisted or not.
     *
     * @return true if the name is blacklisted, false if not
     */
    public boolean isBlacklisted() {
        return this.blacklisted;
    }

    /**
     * Gets the name of the propriety.
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
}
