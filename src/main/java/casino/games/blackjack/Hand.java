package casino.games.blackjack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand extends Deck {
    public int calculateHandValue(){
        int handValue = 0;
        int numberOfAces = 0;

        for(Card card : cards) {
            int number = card.getNumber();
            if (number > 10) { number = 10;}
            if (number == 1) { numberOfAces += 1;}
            handValue += number;
        }

        while (numberOfAces > 0 && handValue + 10 <= 21) {
            handValue += 10;
            numberOfAces--;
        }

        return handValue;
    }
}
