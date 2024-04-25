package casino.games.blackjack;

import casino.games.Game;
import casino.user.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blackjack extends Game {

    private Scanner scanner;

    private Deck deck;

    private Hand playersHand;

    private Hand dealersHand;

    private double moneyGained = 0;

    private double wager = 0;

    int turnCount = 0;


    public Blackjack(String name, int maxPlayers) {
        super(name, maxPlayers);
    }


    @Override
    protected void initializeGame() {
        System.out.println("+============================+");
        System.out.println("Blackjack Game Initializing...");
        System.out.println("+============================+");
        dealersHand = new Hand();
        playersHand = new Hand();
        deck = new Deck(2, false);

        int buyInChips = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("How much money in chips would you like to buy? (minimum: $100)");
            String input = scanner.nextLine();
            try {
                buyInChips = Integer.parseInt(input);
                if (buyInChips >= 100) {
                    validInput = true;
                } else {
                    System.out.println("Minimum buy-in is $100. Please enter a valid amount.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer value.");
            }
        }

        moneyGained -= buyInChips;

        System.out.println("+============================+");
        System.out.println("Beginning the game...");
        System.out.println("+============================+");

        int firstWager = 0;
        boolean validInput1 = false;
        while (!validInput1) {
            System.out.println("How much would you like to wager? (minimum: $5)");
            String input1 = scanner.nextLine();
            try {
                firstWager = Integer.parseInt(input1);
                if (firstWager >= 5) {
                    validInput1 = true;
                } else {
                    System.out.println("Minimum wager is $5. Please enter a valid amount.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer value.");
            }
        }

        wager = firstWager;

    }


    @Override
    protected boolean isGameOver() {
        if(playersHand.calculateHandValue() == 21) {
            return true;
        }
        else if(playersHand.calculateHandValue() > 21){
            return true;
        }
        else if(playersHand.calculateHandValue() < 21 && dealersHand.calculateHandValue() < 17){
            return false;
        }
        return false;
    }

    @Override
    protected void playTurn() {

        if(turnCount == 0){
            System.out.println("Turn # " + turnCount);

            dealersHand.addCard(deck.drawCard());
            System.out.println("The dealer has drawn a card...");

            playersHand.addCard(deck.drawCard());
            System.out.println("You have drawn a card: " + playersHand.returnCards().get(0).getCardName());

            dealersHand.addCard(deck.drawCard());
            System.out.println("The dealer has drawn a card: " + dealersHand.returnCards().get(1).getCardName());

            playersHand.addCard(deck.drawCard());
            System.out.println("You have drawn a card: " +  playersHand.returnCards().get(1).getCardName());

            boolean gameStatus = isGameOver();

            turnCount++;
        }

        if(turnCount == 1){

            System.out.println("Turn # " + turnCount);

            System.out.println("-------------------------");
            System.out.print("Your hand:  ");
            for(Card card : playersHand.returnCards()){
                System.out.print(card.getCardName());
            }
            System.out.println("-------------------------");
            System.out.print("Dealer's hand: ");
            for(Card card : dealersHand.returnCards()){
                if(card == dealersHand.returnCards().get(0)){
                    System.out.print("? of ?");
                }
                else {System.out.print(card.getCardName());}
            }
            System.out.println("-------------------------");
            System.out.println("Select an option:");
            System.out.println("-------------------------");
            System.out.println("(A) Stand (do nothing)");
            System.out.println("(B) Hit (take on another card)");
            System.out.println("(C) Double Down (take on another card, double your wager)");
            System.out.println("(D) Split (create two hands of cards of equal value");
            System.out.println("-------------------------");
            System.out.print("Type Your Option:");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice){
                case "A":

                    break;
                case "B":

                    break;
                case "C":

                    break;
                case "D":

                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

        }

    }

    @Override
    protected Player determineWinner() {
        return null;
    }

    @Override
    protected void endGame(Player winner) {

    }
}

