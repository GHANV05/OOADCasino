package casino.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            if (suit != Card.Suit.COLORED_JOKER && suit != Card.Suit.NON_COLORED_JOKER) {
                for (int number = 1; number <= 13; number++) {
                    cards.add(new Card(number, suit));
                }
            }
        }
        // Adding Joker cards
        cards.add(new Card(0, Card.Suit.COLORED_JOKER));
        cards.add(new Card(0, Card.Suit.NON_COLORED_JOKER));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // Remove and return the top card
        }
        return null; // Deck is empty
    }

    public List<Card> returnCards(){
        return cards;
    }

    public int size() {
        return cards.size();
    }

    @Override
    public String toString() {
        return "Deck with " + size() + " cards";
    }
}

