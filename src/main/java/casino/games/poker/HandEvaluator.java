package casino.games.poker;
import casino.games.blackjack.Card;

import java.util.*;

public class HandEvaluator {

    public HandEvaluator(){}

    public static int evaluateHand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("A poker hand must have exactly 5 cards.");
        }

        // Sort the cards by their face value for easier evaluation
        Collections.sort(cards, Comparator.comparingInt(Card::getNumber));

        // Check for special hands first (e.g., straight, flush, full house, etc.)
        if (isStraightFlush(cards)) {
            return 9; // Straight Flush
        }
        if (isFourOfAKind(cards)) {
            return 8; // Four of a Kind
        }
        if (isFullHouse(cards)) {
            return 7; // Full House
        }
        if (isFlush(cards)) {
            return 6; // Flush
        }
        if (isStraight(cards)) {
            return 5; // Straight
        }
        if (isThreeOfAKind(cards)) {
            return 4; // Three of a Kind
        }
        if (isTwoPair(cards)) {
            return 3; // Two Pair
        }
        if (isPair(cards)) {
            return 2; // Pair
        }

        // If none of the above, it's a High Card hand
        return 1;
    }

    private static boolean isStraightFlush(List<Card> cards) {
        return isStraight(cards) && isFlush(cards);
    }

    private static boolean isFourOfAKind(List<Card> cards) {
        Map<Integer, Integer> cardCounts = getCardCounts(cards);
        for (int count : cardCounts.values()) {
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFullHouse(List<Card> cards) {
        Map<Integer, Integer> cardCounts = getCardCounts(cards);
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;
        for (int count : cardCounts.values()) {
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasPair = true;
            }
        }
        return hasThreeOfAKind && hasPair;
    }

    private static boolean isFlush(List<Card> cards) {
        Card.Suit suit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStraight(List<Card> cards) {
        int prevNumber = cards.get(0).getNumber();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getNumber() != prevNumber + 1) {
                return false;
            }
            prevNumber = cards.get(i).getNumber();
        }
        return true;
    }

    private static boolean isThreeOfAKind(List<Card> cards) {
        Map<Integer, Integer> cardCounts = getCardCounts(cards);
        for (int count : cardCounts.values()) {
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTwoPair(List<Card> cards) {
        Map<Integer, Integer> cardCounts = getCardCounts(cards);
        int pairCount = 0;
        for (int count : cardCounts.values()) {
            if (count == 2) {
                pairCount++;
            }
        }
        return pairCount >= 2;
    }

    private static boolean isPair(List<Card> cards) {
        Map<Integer, Integer> cardCounts = getCardCounts(cards);
        for (int count : cardCounts.values()) {
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

    private static Map<Integer, Integer> getCardCounts(List<Card> cards) {
        Map<Integer, Integer> cardCounts = new HashMap<>();
        for (Card card : cards) {
            int number = card.getNumber();
            cardCounts.put(number, cardCounts.getOrDefault(number, 0) + 1);
        }
        return cardCounts;
    }
}

