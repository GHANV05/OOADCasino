package casino.games;

import casino.user.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack extends Game {
    private Deck deck;
    private List<Card> dealerHand;
    private Player player;
    private Scanner scanner;

    public Blackjack(String name, int maxPlayers) {
        super(name, maxPlayers);
        deck = DeckBuilder.createStandardDeck();
        dealerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public Blackjack(Player player) {
        super();
        this.player = player;
        deck = DeckBuilder.createStandardDeck();
        dealerHand = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    protected void initializeGame() {
        // Shuffle the deck before starting the game
        deck.shuffle();
    }

    @Override
    protected boolean isGameOver() {
        // Game over if the player's hand value is over 21 or if they run out of funds
        return getHandValue(player.getHand()) > 21 || player.getAccountBalance() <= 0;
    }

    @Override
    protected void playTurn() {
        System.out.println("\n" + player.getWholeName() + "'s Turn");
        dealCardToPlayer(player);
        showHands();
        while (!isPlayerDone()) {
            askPlayerAction();
            showHands();
        }
    }

    private void dealCardToPlayer(Player player) {
        Card card = deck.drawCard();
        if (card != null) {
            player.addToHand(card);
        } else {
            System.out.println("No more cards in the deck!");
        }
    }

    private void showHands() {
        System.out.println("Dealer's Hand: [Hidden], " + dealerHand.get(1));
        System.out.println(player.getWholeName() + "'s Hand: " + player.getHand() +
                " (Total Value: " + getHandValue(player.getHand()) + ")");
    }

    private boolean isPlayerDone() {
        int handValue = getHandValue(player.getHand());
        return handValue >= 21 || !askPlayerDrawAgain();
    }

    private boolean askPlayerDrawAgain() {
        System.out.print("Do you want to draw another card? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }

    private void askPlayerAction() {
        if (getHandValue(player.getHand()) < 21) {
            System.out.print("Do you want to draw another card? (yes/no): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                dealCardToPlayer(player);
            } else {
                player.recordLoss("Blackjack"); // Player folds, record as a loss
            }
        }
    }

    @Override
    protected Player determineWinner() {
        int playerValue = getHandValue(player.getHand());
        int dealerValue = getHandValue(dealerHand);
        if (playerValue > 21 || (dealerValue <= 21 && dealerValue > playerValue)) {
            System.out.println("Dealer wins!");
            player.recordLoss("Blackjack");
            return null;
        } else if (dealerValue > 21 || playerValue > dealerValue) {
            System.out.println(player.getWholeName() + " wins!");
            player.recordWin("Blackjack");
            return player;
        } else {
            System.out.println("It's a tie!");
            return null;
        }
    }

    @Override
    protected void endGame(Player winner) {
        System.out.println("\nGame Over");
        System.out.println("Dealer's Hand: " + dealerHand +
                " (Total Value: " + getHandValue(dealerHand) + ")");
        if (winner != null) {
            System.out.println(winner.getWholeName() + " wins!");
        } else {
            System.out.println("No winner this round.");
        }
        scanner.close();
    }

    private int getHandValue(List<Card> hand) {
        int value = 0;
        int numAces = 0;
        for (Card card : hand) {
            if (card.getNumber() == 1) {
                numAces++;
                value += 11;
            } else if (card.getNumber() >= 10) {
                value += 10;
            } else {
                value += card.getNumber();
            }
        }
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }
        return value;
    }
}

