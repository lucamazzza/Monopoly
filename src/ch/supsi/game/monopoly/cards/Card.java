package ch.supsi.game.monopoly.cards;

/**
 * <p>
 * Class for representing a card in the game "Monopoly".
 * A card has an action, a description and possibly,
 * a target cell or an amount of money it gives you or
 * takes from you.
 * </p>
 * <b>Usage</b>:
 * <pre>
 * {@code
 * Card card = new Card(CardAction.GO_TO,
 *                      "prison",
 *                      "Go to prison");   // Instantiate a new card
 * CardAction c = card.getCardAction();    // Returns the action of the card
 * System.out.println(card);               // Prints the card detail
 * }
 * </pre>
 *
 * @author Andrea Masciocchi
 * @version 1.4.0
 */
public class Card {

    /**
     * The action performed by the card.
     */
    private final CardAction cardAction;

    /**
     * The name of the cell targeted by the card.
     */
    private final String cellName;

    /**
     * The amount of money targeted by the effect of the card.
     */
    private final int amount;

    /**
     * The description detail of the card.
     */
    private final String detail;

    /**
     * <p>
     * Private constructor for the card.
     * </p>
     * <p>
     * Initializes a card with all the possible parameters.
     * Used only internally.
     * </p>
     *
     * @param cardAction the action performed by the card
     * @param cellName the name of the cell targeted by the card
     * @param amount the amount of money targeted by the effect of the card
     * @param detail the description detail of the card
     */
    private Card(final CardAction cardAction, final String cellName, final int amount, final String detail) {
        if (cardAction == null) throw new IllegalArgumentException("cardAction cannot be null");
        if (detail == null || detail.isBlank() || detail.isEmpty())
            throw new IllegalArgumentException("detail cannot be null, blank nor empty");
        this.cardAction = cardAction;
        this.cellName = cellName;
        this.amount = amount;
        this.detail = detail;
    }

    /**
     * <p>
     * Public constructor for a card that moves the player to a cell.
     * </p>
     * <p>
     * Initializes a card with all the parameters except for the amount,
     * which is set to 0.
     * </p>
     *
     * @param cardAction the action performed by the card
     * @param cellName the name of the cell targeted by the card
     * @param detail the description detail of the card
     */
    public Card(final CardAction cardAction, final String cellName, final String detail){
        this(cardAction, cellName, 0, detail);
    }

    /**
     * <p>
     * Public constructor for a card that gives/takes money from the player.
     * </p>
     * <p>
     * Initializes a card with all the parameters except for the cellName,
     * which is set to null.
     * </p>
     *
     * @param cardAction the action performed by the card
     * @param amount the amount of money targeted by the effect of the card
     * @param detail the description detail of the card
     */
    public Card(final CardAction cardAction, final int amount, final String detail){
        this(cardAction, null, amount, detail);
    }

    /**
     * <p>
     * Returns the action of the card, as a {@link CardAction}.
     * </p>
     *
     * @return the action of the card
     */
    public CardAction getCardAction() {
        return cardAction;
    }

    /**
     * <p>
     * Returns the amount of money targeted by the effect of the card.
     * </p>
     *
     * @return the amount of money targeted by the effect of the card
     */
    public int getAmount() {
        return amount;
    }

    /**
     * <p>
     * Returns the name of the cell targeted by the card.
     * </p>
     *
     * @return the name of the cell
     */
    public String getCellName() {
        return cellName;
    }

    /**
     * Returns the card as a String.
     *
     * @see Object#toString()
     * @return the card
     */
    @Override
    public String toString() {
        return detail;
    }
}
