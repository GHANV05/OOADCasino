package casino.games;

public class DeckBuilder {
    public static Deck createStandardDeck() {
        return new Deck();
    }
    public static Deck createLargeDeck(int numDecks) {
        Deck largeDeck = new Deck();
        for (int i = 1; i < numDecks; i++) {
            largeDeck.returnCards().addAll(new Deck().returnCards());
        }
        return largeDeck;
    }
}

