package casino.games;

public class Card {
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES, COLORED_JOKER, NON_COLORED_JOKER
    }

    private int number;
    private Suit suit;

    public Card(int number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        if (suit == Suit.COLORED_JOKER) {
            return "Colored Joker";
        } else if (suit == Suit.NON_COLORED_JOKER) {
            return "Non-Colored Joker";
        } else {
            return number + " of " + suit.toString().toLowerCase();
        }
    }
}
