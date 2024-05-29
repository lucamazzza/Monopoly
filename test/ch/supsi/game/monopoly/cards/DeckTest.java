package ch.supsi.game.monopoly.cards;

import ch.supsi.game.monopoly.exception.EmptyDeckException;
import ch.supsi.game.monopoly.exception.IllegalCardException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void pick() {
        Deck deck = new Deck("Imprevisti.txt");
        for (int i = 0; i < 13; i++){
            assertDoesNotThrow(deck::pick);
        }
        assertThrows(EmptyDeckException.class, deck::pick);
    }

    @Test
    void putBack() {
        Deck deck = new Deck("Imprevisti.txt");
        for (int i = 0; i < 10000; i++) {
            AtomicReference<Card> card = new AtomicReference<>();
            assertDoesNotThrow(() -> card.set(deck.pick()));
            assertDoesNotThrow(() -> deck.putBack(card.get()));
            assertThrows(IllegalCardException.class, () -> deck.putBack(null));
        }
    }
}