package ch.supsi.game.monopoly.cards;

import ch.supsi.game.monopoly.Constant;
import ch.supsi.game.monopoly.exception.EmptyDeckException;
import ch.supsi.game.monopoly.exception.IllegalCardException;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

/**
 * <p>
 * Class representing a deck of {@link Card}s.
 * </p>
 * <p>
 * A deck of card can be shuffled, you can pick a card
 * from the top or put a card back at the bottom.
 * </p>
 * <p>
 * The cards are stored in a {@link Deque};
 * </p>
 * @author Luca Mazza
 * @version 1.4.0
 */
public class Deck {

    /**
     * The cards in the deck.
     */
    private Deque<Card> cards;

    /**
     * <p>
     * Constructor for the {@link Deck} class.
     * </p>
     * <p>
     * The deck is created from a file, containing in a
     * "semicolon separated format" all the cards.
     * </p>
     *
     * @param filename the name of the file containing the cards
     */
    public Deck(final String filename) {
        if (filename == null || filename.isBlank() || filename.isEmpty())
            throw new IllegalArgumentException("Filename cannot be null, blank nor empty");
        this.cards = new ArrayDeque<>();
        this.createDeckFromFile(filename);
    }

    /**
     * <p>
     * Picks a card from the top of the deck.
     * </p>
     * <p>
     * Once the card is picked, it is removed from the deck
     * and returned.
     * </p>
     *
     * @return the card
     * @throws EmptyDeckException when the deck is empty
     */
    public Card pick() throws EmptyDeckException {
        if (this.cards.isEmpty()) throw new EmptyDeckException("Cannot pick a card from an empty deck");
        return this.cards.poll();
    }

    /**
     * <p>
     * Puts a card back at the bottom of the deck.
     * </p>
     *
     * @param card the card
     * @throws IllegalCardException when the card is null
     */
    public void putBack(final Card card) throws IllegalCardException {
        if (card == null) throw new IllegalCardException("Cards cannot be null");
        this.cards.add(card);
    }

    /**
     * <p>
     * Shuffles the deck, using the {@link Collections#shuffle}
     * </p>
     *
     * @throws EmptyDeckException when the deck is empty
     */
    public void shuffle() throws EmptyDeckException {
        if (this.cards.isEmpty()) throw new EmptyDeckException("Cannot pick a card from an empty deck");
        Collections.shuffle(new ArrayList<>(this.cards));
    }

    /**
     * <p>
     * Creates the deck from a file, given the filename.
     * </p>
     * <p>
     * Uses a {@link BufferedReader} to read the file and
     * splits the lines using the {@link Constant#FILE_SEPARATOR}.
     * </p>
     * <p>
     * Retrieves then all information about the card and puts them
     * in the corresponding field.
     * </p>
     *
     * @param fileName the name of the file
     */
    private void createDeckFromFile(final String fileName) {
        File file = new File(fileName);
        try (FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] fragments = strLine.split(Constant.FILE_SEPARATOR);
                if(fragments[1].equals(CardAction.GO_TO.getAction())){
                    this.cards.add(new Card(CardAction.GO_TO, fragments[2], fragments[0]));
                }else if(fragments[1].equals(CardAction.RECEIVE.getAction())){
                    this.cards.add(new Card(CardAction.RECEIVE, Integer.parseInt(fragments[2]), fragments[0]));
                }else if(fragments[1].equals(CardAction.PAY.getAction())) {
                    this.cards.add(new Card(CardAction.PAY, Integer.parseInt(fragments[2]), fragments[0]));
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
