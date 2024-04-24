import casino.games.blackjack.Card;
import casino.games.blackjack.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {

    @Test
    public void testCalculateHandValue() {

        Hand hand = new Hand();
        hand.addCard(new Card(1, Card.Suit.Hearts));
        hand.addCard(new Card(5, Card.Suit.Clubs));
        hand.addCard(new Card(10, Card.Suit.Spades));

        assertEquals(16, hand.calculateHandValue());

        hand.addCard(new Card(1, Card.Suit.Diamonds));

        assertEquals(17, hand.calculateHandValue());

        hand.addCard(new Card(10, Card.Suit.Hearts));

        // Test the calculateHandValue method after adding more cards
        assertEquals(27, hand.calculateHandValue());

        Hand hand1 = new Hand();
        hand1.addCard(new Card(1, Card.Suit.Hearts));
        hand1.addCard(new Card(13, Card.Suit.Spades));

        assertEquals(21, hand1.calculateHandValue());

    }
}
