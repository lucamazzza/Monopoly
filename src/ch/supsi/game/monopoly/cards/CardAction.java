package ch.supsi.game.monopoly.cards;

/**
 * <p>
 * Enum representing the actions of the cards in the game "Monopoly".
 * </p>
 * <p>
 * There are three types of actions: "goto", "pay" and "receive";
 * <li> goto: transfers the player to a certain cell;
 * <li> pay: transfers money from the player to the bank;
 * <li> receive: transfers money from the bank to the player.
 * </p>
 *
 * @author Andrea Masciocchi
 * @version 1.4.0
 */
public enum CardAction {

    /**
     * <p>
     * The "goto" action, which transfers the player to a certain cell.
     * </p>
     */
    GO_TO ("goto"),

    /**
     * <p>
     * The "pay" action, which transfers money from the player to the bank.
     * </p>
     */
    PAY ("pay"),

    /**
     * <p>
     * The "receive" action, which transfers money from the bank to the player.
     * </p>
     */
    RECEIVE ("receive");

    /**
     * The action of the card, as a String.
     */
    private final String action;

    /**
     * <p>
     * Constructor of the enum.
     * </p>
     * @param action the action of the card
     */
    CardAction(final String action) {
        if (action == null || action.isEmpty() || action.isBlank())
            throw new IllegalArgumentException();
        this.action = action;
    }

    /**
     * <p>
     * Returns the action of the card.
     * </p>
     *
     * @return the action of the card
     */
    public String getAction() {
        return this.action;
    }
}
