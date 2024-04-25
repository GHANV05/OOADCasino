import casino.games.blackjack.Card;
import casino.games.blackjack.Deck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeckTest {

    @Test
    public void testDeckConstructor(){
        Deck deck = new Deck();
        assertNotNull(deck);
        Deck deck1 = new Deck(1, false);
        assertNotNull(deck1);
        Deck deck2 = new Deck(1, true);
        assertNotNull(deck2);
    }

    @Test
    public void testDeckReturnCards(){
        List<Card> cards = new ArrayList<>();
        Deck deck = new Deck();
        assertEquals(deck.returnCards(), cards);

        Deck deck1 = new Deck(1, false);
        assertNotNull(deck1.returnCards());
    }

    @Test
    public void testDeckDrawCard(){
        Deck deck = new Deck(1, false);
    }

    @Test
    public void testDeckAddCards(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(1, Card.Suit.Hearts));

        List<Card> cards1 = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            for(Card.Suit suit : Card.Suit.values()) {
                if(suit != Card.Suit.Joker) {
                    for (int number = 1; number <= 13; number++) {
                        cards1.add(new Card(number, suit));
                    }
                }
            }
        }

        Deck deck = new Deck();
        deck.addCard(cards);
        assertEquals(cards, deck.returnCards());

        Deck deck1 = new Deck();
        deck1.addCard(cards1);
        assertEquals(cards1, deck1.returnCards());

    }

}
