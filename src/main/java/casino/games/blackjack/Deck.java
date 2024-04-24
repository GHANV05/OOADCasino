package casino.games.blackjack;

import casino.games.blackjack.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {

    }

    public Deck(int numberOfDecks, boolean withJokers) {
        for(int i = 0; i < numberOfDecks; i++){
            for(Card.Suit suit : Card.Suit.values()) {
                if(suit != Card.Suit.Joker) {
                    for (int number = 1; number <= 13; number++) {
                        cards.add(new Card(number, suit));
                    }
                }
            }
            if(withJokers){
                cards.add(new Card(Card.Joker.Red));
                cards.add(new Card(Card.Joker.Black));
            }
        }
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public List<Card> returnCards(){
        return cards;
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public void addCard(List<Card> newCards) {
        cards.addAll(newCards);
    }

}

