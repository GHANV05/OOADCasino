package casino.games.poker;

import casino.games.Game;
import casino.games.blackjack.Card;
import casino.games.blackjack.Deck;
import casino.games.blackjack.DeckBuilder;
import casino.games.blackjack.Hand;
import casino.user.Player;
import casino.games.poker.HandEvaluator;

import java.util.Scanner;

public class Poker extends Game {
    private int turnCount = 0;
    private Scanner scanner;
    private Hand playerHand;
    private Hand opponentHand;
    private Hand communityHand;
    private double pot;
    private double bet;
    private Deck deck;
    boolean gameIsOver = false;
    public Poker(String name, int maxPlayers, Player player) {
        super(name, maxPlayers);
        this.mainPlayer = player;
        scanner = new Scanner(System.in);
        playerHand = new Hand();
        opponentHand = new Hand();
        communityHand = new Hand();
        turnCount = 0;
        pot = 0;
        bet = 0;
    }

    public Poker() {

    }


    @Override
    protected void initializeGame() {
        scanner = new Scanner(System.in);
        playerHand = new Hand();
        opponentHand = new Hand();
        gameIsOver = false;
        turnCount = 0;
        System.out.println("+============================+");
        System.out.println("Poker Game Initializing...");
        System.out.println("+============================+");
        DeckBuilder deckBuilder = new DeckBuilder()
                .addStandardDeck(6)
                .addJokers(0);
        this.deck = deckBuilder.build();
        deck.shuffleCards();
        System.out.println("+============================+");
        System.out.println("Beginning the game...");
        System.out.println("+============================+");

        boolean playerBuysIn = false;

        System.out.println("Welcome to one-on-one Poker- aka Texas Hold 'Em!");

        while(!playerBuysIn){
            playerBuysIn = buyIn();
        }

        if(gameIsOver){
            isGameOver();
        }

        pot = 0;
        bet = 0;

    }

    private boolean buyIn(){
        System.out.println("Minimum buy-in is $20. Do you accept?");
        System.out.println("-------------------------");
        System.out.println("(A) Yes.");
        System.out.println("(B) No.");
        System.out.println("-------------------------");
        System.out.print("Type Your Option:");
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice){
            case "A":
                bet = 20;
                break;
            case "B":
                bet = 0;
                gameIsOver = true;
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                return false;
        }

        mainPlayer.withdraw(bet);
        return true;
    }

    public boolean isGameOver() {
        return gameIsOver;
    }

    @Override
    protected void playTurn() {
        if(gameIsOver){
           return;
        }

        if(turnCount == 0){
            playerHand.addCard(deck.drawCard());
            playerHand.addCard(deck.drawCard());
            playerHand.addCard(deck.drawCard());
            playerHand.addCard(deck.drawCard());
            playerHand.addCard(deck.drawCard());
            opponentHand.addCard(deck.drawCard());
            opponentHand.addCard(deck.drawCard());
            opponentHand.addCard(deck.drawCard());
            opponentHand.addCard(deck.drawCard());
            opponentHand.addCard(deck.drawCard());

            System.out.println("The dealer has dealt both you and your opponent a full hand of cards");
            System.out.print("Player's hand:  ");
            for (Card card : playerHand.returnCards()) {
                System.out.print(card.getCardName() + " ");
            }
            turnCount++;
        }

        else {

            if (turnCount < 3) {
                System.out.println("Your turn:");
                System.out.println("(A) Bet");
                System.out.println("(B) Call");
                System.out.println("(C) Fold");
                System.out.print("Choose your action: ");
                String choice = scanner.nextLine().trim().toUpperCase();

                switch (choice) {
                    case "A":
                        bet();
                        break;
                    case "B":
                        call();
                        break;
                    case "C":
                        fold();
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        playTurn();
                        break;
                }

            }

            else {gameIsOver = true;}

        }



    }

    private void bet() {
        System.out.print("Enter your bet amount: $");
        bet = Double.parseDouble(scanner.nextLine().trim());
        pot += bet;
        mainPlayer.withdraw(bet);
        System.out.println("You bet $" + bet);

        HandEvaluator playerEvaluator = new HandEvaluator();
        int playerHandValue = playerEvaluator.evaluateHand(playerHand.returnCards());

        HandEvaluator opponentEvaluator = new HandEvaluator();
        int opponentHandValue = opponentEvaluator.evaluateHand(opponentHand.returnCards());


        if(playerHandValue > opponentHandValue ){
            System.out.println("Your hand is better than your opponent's!");
            mainPlayer.deposit(pot*2);
            gameIsOver = true;
        } else {
            System.out.println("Your hand is NOT better than your opponent's!");
            gameIsOver = true;
        }
    }

    private void call() {
        double opponentBet = bet;
        pot += opponentBet;
        System.out.println("Opponent calls $" + opponentBet);

        HandEvaluator playerEvaluator = new HandEvaluator();
        int playerHandValue = playerEvaluator.evaluateHand(playerHand.returnCards());

        HandEvaluator opponentEvaluator = new HandEvaluator();
        int opponentHandValue = opponentEvaluator.evaluateHand(opponentHand.returnCards());


        if(playerHandValue > opponentHandValue ){
            System.out.println("Your hand is better than your opponent's!");
            mainPlayer.deposit(pot*2);
            gameIsOver = true;
        } else {
            System.out.println("Your hand is NOT better than your opponent's!");
            gameIsOver = true;
        }
    }

    private void fold() {
        System.out.println("You fold.");
        bet = 0;
        System.out.println("You win nothing at all.");
        gameIsOver = true;
    }

    @Override
    protected Player determineWinner() {
        return mainPlayer;
    }

    @Override
    protected void endGame(Player winner) {

    }
}
