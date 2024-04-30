package casino.games.blackjack;

import java.util.ArrayList;
import java.util.List;

public class DeckBuilder {
    private List<Card> cards = new ArrayList<>();

    public DeckBuilder addStandardDeck(int numberOfDecks) {
        for (int i = 0; i < numberOfDecks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                if (suit != Card.Suit.Joker) {
                    for (int number = 1; number <= 13; number++) {
                        cards.add(new Card(number, suit));
                    }
                }
            }
        }
        return this;
    }

    public DeckBuilder addJokers(int numberOfJokers) {
        for (int i = 0; i < numberOfJokers; i++) {
            cards.add(new Card(Card.Joker.Red));
            cards.add(new Card(Card.Joker.Black));
        }
        return this;
    }

    public Deck build() {
        Deck deck = new Deck();
        deck.addCard(cards);
        return deck;
    }
}
