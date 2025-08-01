package casino.games.blackjack;

import casino.games.Game;
import casino.user.Player;

import java.util.Objects;
import java.util.Scanner;

public class Blackjack extends Game {

    /******************************************************************/

    private Scanner scanner;

    /******************************************************************/
    private Deck deck;

    private Hand playersHand;

    private Hand dealersHand;

    /******************************************************************/
    private double moneyGained = 0;

    private double wager = 0;

    int turnCount = 0;

    boolean gameIsOver = false;

    boolean testBlackjack = false;

    /******************************************************************/


    public Blackjack(String name, int maxPlayers, Player player) {
        super(name, maxPlayers);
        this.name = "Blackjack";
        if(Objects.equals(name, "testblackjack")){
            testBlackjack = true;
        }
        this.mainPlayer = player;
    }


    @Override
    public void initializeGame() {
        turnCount = 0;
        gameIsOver = false;
        this.scanner = new Scanner(System.in);
        System.out.println("+============================+");
        System.out.println("Blackjack Game Initializing...");
        System.out.println("+============================+");
        dealersHand = new Hand();
        playersHand = new Hand();
        DeckBuilder deckBuilder = new DeckBuilder()
                .addStandardDeck(2) // Add two standard decks
                .addJokers(0); // Add four jokers
        this.deck = deckBuilder.build();
        deck.shuffleCards();

        System.out.println("+============================+");
        System.out.println("Beginning the game...");
        System.out.println("+============================+");
        boolean makeWager = !testBlackjack;
        while(makeWager){
            makeWager = makeWager();
        }

    }

    private boolean makeWager(){
        System.out.println("How much would you like to wager?");
        System.out.println("-------------------------");
        System.out.println("(A) $5");
        System.out.println("(B) $20");
        System.out.println("(C) $50");
        System.out.println("(D) $100");
        System.out.println("-------------------------");
        System.out.print("Type Your Option:");
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice){
            case "A":
                wager = 5;
                return false;
            case "B":
                wager = 20;
                return false;
            case "C":
                wager = 50;
                return false;
            case "D":
                wager = 100;
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
        }

        mainPlayer.withdraw(wager);
        return true;
    }

    @Override
    public boolean isGameOver() {
        return gameIsOver;
    }

    @Override
    public void playTurn() {

        System.out.println("Turn # " + turnCount);

        if(turnCount == 0){

            dealersHand.addCard(deck.drawCard());
            System.out.println("The dealer has drawn a card...");

            playersHand.addCard(deck.drawCard());
            System.out.println("You have drawn a card: " + playersHand.returnCards().get(0).getCardName());

            dealersHand.addCard(deck.drawCard());
            System.out.println("The dealer has drawn a card: " + dealersHand.returnCards().get(1).getCardName());

            playersHand.addCard(deck.drawCard());
            System.out.println("You have drawn a card: " +  playersHand.returnCards().get(1).getCardName());

            if(playersHand.calculateHandValue() == 21){
                System.out.println("-------------------------");
                System.out.println("Congrats! You got a Blackjack!");
                if(dealersHand.calculateHandValue() < 21){
                    System.out.println("Your hand defeated the dealer's hand. You won!");
                    System.out.println("Your wager has been tripled and returned to you.");
                    mainPlayer.recordWin(name);
                    moneyGained += wager*3;
                } else {
                    System.out.println("The dealer also had a Blackjack! It's a push!");
                    System.out.println("Your wager has been returned to you.");
                    mainPlayer.recordWin(name);
                    moneyGained += wager;
                }
                gameIsOver = true;
            }

        }

        else {

            System.out.println("-------------------------");
            System.out.print("Dealer's hand:  ");
            for(Card card : dealersHand.returnCards()){
                if(turnCount == 1 && card == dealersHand.returnCards().get(0)) {
                    System.out.print("? of ? " );
                }
                else {System.out.print(card.getCardName() + " ");}
            }
            System.out.println("\n-------------------------");
            System.out.print("Your hand:  ");
            for(Card card : playersHand.returnCards()){
                System.out.print(card.getCardName() + " ");
            }
            System.out.println("\n-------------------------");
            System.out.println("Select an option:");
            System.out.println("-------------------------");
            System.out.println("(A) Stand (do nothing)");
            System.out.println("(B) Hit (take on another card)");
            System.out.println("(C) Surrender (give up your hand)");
            System.out.println("-------------------------");
            System.out.print("Type Your Option:");

            String choice;

            if(testBlackjack){
                choice = "C";
            }
            else {
                choice = scanner.nextLine().trim().toUpperCase();
            }

            boolean playerStillPlaying = true;

            switch (choice){
                case "A":
                    playerStillPlaying = playerChoiceStand();
                    break;
                case "B":
                    playerStillPlaying = playerChoiceHit();
                    break;
                case "C":
                    playerStillPlaying = playerChoiceSurrender();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if(!playerStillPlaying) {
                System.out.println("-------------------------");
                gameIsOver = true;
            }

            else {
                boolean continueTurn = dealersTurn();
                if(!continueTurn){
                    if(dealersHand.calculateHandValue() > 21 || dealersHand.calculateHandValue() < playersHand.calculateHandValue()){
                        System.out.println("-------------------------");
                        System.out.println("You won! You wager has been doubled and returned to you.");
                        mainPlayer.recordWin(name);
                        moneyGained += wager*2;
                    } else {
                        System.out.println("-------------------------");
                        System.out.println("You lost! You wager will not be returned to you.");
                        mainPlayer.recordLoss(name);
                    }
                    gameIsOver = true;
                }
            }

        }

        turnCount++;
    }

    private boolean playerChoiceStand(){
        System.out.println("-------------------------");
        System.out.println("You have chosen to stand.");

        boolean dealerDraws = true;
        while(dealerDraws){
            dealerDraws = dealersTurn();
        }

        if(dealersHand.calculateHandValue() > 21 || dealersHand.calculateHandValue() < playersHand.calculateHandValue()){
            System.out.println("-------------------------");
            System.out.println("You won! You wager has been doubled and returned to you.");
            mainPlayer.recordWin(name);
            moneyGained += wager*2;
        } else {
            System.out.println("-------------------------");
            System.out.println("You lost! You wager will not be returned to you.");
            mainPlayer.recordWin(name);
        }
        return false;
    }

    private boolean playerChoiceHit(){
        System.out.println("-------------------------");
        System.out.println("You have chosen to hit.");
        playersHand.addCard(deck.drawCard());
        System.out.println("You have drawn a card: " + playersHand.returnCards().get(playersHand.returnCards().size()-1).getCardName());
        if(playersHand.calculateHandValue() > 21){
            System.out.println("You have been busted!");
            System.out.println("Your wager will not be returned to you.");
            mainPlayer.recordLoss(name);
            return false;
        }
        return true;
    }

    private boolean playerChoiceSurrender(){
        System.out.println("-------------------------");
        System.out.println("You have chosen to surrender.");
        System.out.println("Half your wager is returned to you.");
        mainPlayer.recordLoss(name);
        moneyGained += wager/2;
        return false;
    }

    private boolean dealersTurn() {
        System.out.println("-------------------------");
        System.out.print("Dealer's hand:  ");
        for (Card card : dealersHand.returnCards()) {
            System.out.print(card.getCardName() + " ");
        }
        System.out.println("\n-------------------------");

        if (dealersHand.calculateHandValue() < 17) {
            dealersHand.addCard(deck.drawCard());
            System.out.println("The dealer has drawn a card: " + dealersHand.returnCards().get(dealersHand.returnCards().size() - 1).getCardName());
            if(dealersHand.calculateHandValue() > 21){
                System.out.println("-------------------------");
                System.out.println("The dealer has busted!");
                mainPlayer.recordWin(name);
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public Player determineWinner() {
        if (moneyGained > 0){
            mainPlayer.deposit(moneyGained);
        }
        if(moneyGained < 0){
            mainPlayer.withdraw(moneyGained);
        }
        gameIsOver = true;
        return mainPlayer;

    }

    @Override
    public void endGame(Player winner) {
        gameIsOver = true;
    }
}

