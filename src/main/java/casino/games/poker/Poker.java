package casino.games.poker;

import casino.games.Game;
import casino.games.blackjack.Card;
import casino.games.blackjack.Deck;
import casino.games.blackjack.DeckBuilder;
import casino.games.blackjack.Hand;
import casino.user.Player;

import java.util.Scanner;

public class Poker extends Game {
    private Scanner scanner;
    private Hand playerHand;
    private Hand opponentHand;
    private double pot;
    private double bet;
    private Deck deck;
    boolean isGameOver;
    public Poker(String name, int maxPlayers) {
        super(name, maxPlayers);
        scanner = new Scanner(System.in);
        playerHand = new Hand();
        opponentHand = new Hand();
        pot = 0;
        bet = 0;
    }

    public Poker() {

    }

    @Override
    protected void initializeGame() {
        System.out.println("+============================+");
        System.out.println("Poker Game Initializing...");
        System.out.println("+============================+");
        deck = new Deck(6, false);
        deck.shuffleCards();
        System.out.println("+============================+");
        System.out.println("Beginning the game...");
        System.out.println("+============================+");

        playerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        opponentHand.addCard(deck.drawCard());
        opponentHand.addCard(deck.drawCard());
        pot = 0;
        bet = 0;
        System.out.println("Your hand: " + playerHand.returnCards());

    }

    private boolean makeWager(){
        System.out.println("Minimum buy-in is $10. Do you accept?");
        System.out.println("-------------------------");
        System.out.println("(A) Yes");
        System.out.println("(B) No");
        System.out.println("-------------------------");
        System.out.print("Type Your Option:");
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice){
            case "A":
                bet = 10;
                return false;
            case "B":
                bet = 0;
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
        }

        mainPlayer.withdraw(bet);
        return true;
    }

    @Override
    protected boolean isGameOver() {
        return false;
    }

    @Override
    protected void playTurn() {

    }

    @Override
    protected Player determineWinner() {
        return null;
    }

    @Override
    protected void endGame(Player winner) {

    }
}
