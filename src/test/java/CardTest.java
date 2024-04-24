import casino.games.blackjack.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardTest {
    @Test
    public void testCardConstructor(){
        for(int i = 1; i <= 13; i++){
            Card card = new Card(i, Card.Suit.Hearts);
            assertNotNull(card);
            Card card1 = new Card(i, Card.Suit.Diamonds);
            assertNotNull(card1);
            Card card2 = new Card(i, Card.Suit.Clubs);
            assertNotNull(card2);
            Card card3 = new Card(i, Card.Suit.Spades);
            assertNotNull(card3);
        }

        Card card4 = new Card(Card.Joker.Red);
        assertNotNull(card4);

        Card card5 = new Card(Card.Joker.Black);
        assertNotNull(card5);
    }

    @Test
    public void testCardGetNumber(){
        for(int i = 1; i <= 13; i++){
            Card card = new Card(i, Card.Suit.Hearts);
            assert(card.getNumber() == i);
            Card card1 = new Card(i, Card.Suit.Diamonds);
            assert(card1.getNumber() == i);
            Card card2 = new Card(i, Card.Suit.Clubs);
            assert(card2.getNumber() == i);
            Card card3 = new Card(i, Card.Suit.Spades);
            assert(card3.getNumber() == i);
        }

        Card card4 = new Card(Card.Joker.Red);
        assert(card4.getNumber() == 0);

        Card card5 = new Card(Card.Joker.Black);
        assert(card5.getNumber() == 0);
    }

    @Test
    public void testCardGetSuit(){
        for(int i = 1; i <= 13; i++){
            Card card = new Card(i, Card.Suit.Hearts);
            assert(card.getSuit() == Card.Suit.Hearts);
            Card card1 = new Card(i, Card.Suit.Diamonds);
            assert(card1.getSuit() == Card.Suit.Diamonds);
            Card card2 = new Card(i, Card.Suit.Clubs);
            assert(card2.getSuit() == Card.Suit.Clubs);
            Card card3 = new Card(i, Card.Suit.Spades);
            assert(card3.getSuit() == Card.Suit.Spades);
        }

        Card card4 = new Card(Card.Joker.Red);
        assert(card4.getSuit() == Card.Suit.Joker);

        Card card5 = new Card(Card.Joker.Black);
        assert(card5.getSuit() == Card.Suit.Joker);
    }

    @Test
    public void testCardIsJoker(){
        for(int i = 1; i <= 13; i++){
            Card card = new Card(i, Card.Suit.Hearts);
            assert(!card.isJoker());
            Card card1 = new Card(i, Card.Suit.Diamonds);
            assert(!card1.isJoker());
            Card card2 = new Card(i, Card.Suit.Clubs);
            assert(!card2.isJoker());
            Card card3 = new Card(i, Card.Suit.Spades);
            assert(!card3.isJoker());
        }

        Card card4 = new Card(Card.Joker.Red);
        assert(card4.isJoker());

        Card card5 = new Card(Card.Joker.Black);
        assert(card5.isJoker());
    }

    @Test
    public void testCardJokerColor(){
        for(int i = 1; i <= 13; i++){
            Card card = new Card(i, Card.Suit.Hearts);
            assert(card.getJokerColor() == Card.Joker.Null);
            Card card1 = new Card(i, Card.Suit.Diamonds);
            assert(card1.getJokerColor() == Card.Joker.Null);
            Card card2 = new Card(i, Card.Suit.Clubs);
            assert(card2.getJokerColor() == Card.Joker.Null);
            Card card3 = new Card(i, Card.Suit.Spades);
            assert(card3.getJokerColor() == Card.Joker.Null);
        }

        Card card4 = new Card(Card.Joker.Red);
        assert(card4.getJokerColor() == Card.Joker.Red);

        Card card5 = new Card(Card.Joker.Black);
        assert(card5.getJokerColor() == Card.Joker.Black);
    }

    @Test
    public void testCardGetName(){
        for(int i = 1; i <= 13; i++){
            Card card = new Card(i, Card.Suit.Hearts);
            Card card1 = new Card(i, Card.Suit.Diamonds);
            Card card2 = new Card(i, Card.Suit.Clubs);
            Card card3 = new Card(i, Card.Suit.Spades);

            if(i == 1) {
                assertEquals(card.getCardName(), "Ace of " + Card.Suit.Hearts.toString());
                assertEquals(card1.getCardName(), "Ace of " + Card.Suit.Diamonds.toString());
                assertEquals(card2.getCardName(), "Ace of " + Card.Suit.Clubs.toString());
                assertEquals(card3.getCardName(), "Ace of " + Card.Suit.Spades.toString());
            }

            else if(i == 11) {
                assertEquals(card.getCardName(), "Jack of " + Card.Suit.Hearts.toString());
                assertEquals(card1.getCardName(), "Jack of " + Card.Suit.Diamonds.toString());
                assertEquals(card2.getCardName(), "Jack of " + Card.Suit.Clubs.toString());
                assertEquals(card3.getCardName(), "Jack of " + Card.Suit.Spades.toString());
            }

            else if(i == 12){
                assertEquals(card.getCardName(), "Queen of " + Card.Suit.Hearts.toString());
                assertEquals(card1.getCardName(), "Queen of " + Card.Suit.Diamonds.toString());
                assertEquals(card2.getCardName(), "Queen of " + Card.Suit.Clubs.toString());
                assertEquals(card3.getCardName(), "Queen of " + Card.Suit.Spades.toString());
            }

            else if(i == 13){
                assertEquals(card.getCardName(), "King of " + Card.Suit.Hearts.toString());
                assertEquals(card1.getCardName(), "King of " + Card.Suit.Diamonds.toString());
                assertEquals(card2.getCardName(), "King of " + Card.Suit.Clubs.toString());
                assertEquals(card3.getCardName(), "King of " + Card.Suit.Spades.toString());
            }

            else {
                assertEquals(card.getCardName(), i + " of " + Card.Suit.Hearts.toString());
                assertEquals(card1.getCardName(), i + " of " + Card.Suit.Diamonds.toString());
                assertEquals(card2.getCardName(), i + " of " + Card.Suit.Clubs.toString());
                assertEquals(card3.getCardName(), i + " of " + Card.Suit.Spades.toString());
            }

        }

        Card card4 = new Card(Card.Joker.Red);
        assertEquals(card4.getCardName(), "Red Joker");

        Card card5 = new Card(Card.Joker.Black);
        assertEquals(card5.getCardName(), "Black Joker");
    }
}
