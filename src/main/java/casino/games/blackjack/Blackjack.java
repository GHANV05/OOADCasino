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
//
//        wager = firstWager;
//        moneyGained -= firstWager;

    }
//
//    private boolean gamePlayerHas21(){
//
//        if(turnCount == 0){
//            System.out.println("Congrats! You got a Blackjack!");
//            System.out.print("The dealer reveals his hand... ");
//            for(Card card : dealersHand.returnCards()){
//                System.out.print(card.getCardName());
//            }
//            if(dealersHand.calculateHandValue() == 21){
//                System.out.println("The dealer also has a blackjack!");
//                System.out.println("Your wager is returned to you. Good day!");
//                moneyGained += wager;
//            }
//            else {
//                System.out.println("You have won this game of Blackjack!");
//                System.out.println("Your wager has been tripled and returned to you. Good day!");
//                moneyGained += wager*3;
//            }
//        }
//
//        else {
//            System.out.println("Congrats! You got a 21!");
//            System.out.print("The dealer reveals his hand... ");
//            for(Card card : dealersHand.returnCards()){
//                System.out.print(card.getCardName());
//            }
//            if(dealersHand.calculateHandValue() < 17){
//
//                while(dealersHand.calculateHandValue() < 17){
//                    dealersHand.addCard(deck.drawCard());
//                    System.out.println("The dealer has drawn a card: " + dealersHand.returnCards().get(dealersHand.returnCards().size() - 1).getCardName());
//                }
//
//            }
//
//            if(dealersHand.calculateHandValue() == 21){
//                System.out.println("The dealer also has a 21.");
//                System.out.println("Your wager is returned to you. Good day!");
//                moneyGained = 0;
//            }
//
//            else if(dealersHand.calculateHandValue() >= 21){
//                System.out.println("The dealer has busted!");
//                System.out.println("Your wager has been doubled and returned to you. Good day!");
//                moneyGained += wager*2;
//            }
//
//            else {
//                System.out.println("Your hand has beaten the dealer!");
//                System.out.println("Your wager has been doubled and returned to you. Good day!");
//                moneyGained += wager*2;
//            }
//        }
//
//
//        return true;
//    }


    @Override
    protected boolean isGameOver() {

//        if(playersHand.calculateHandValue() == 21) {
//            return gamePlayerHas21();
//        }
//
//        else if(playersHand.calculateHandValue() > 21){
//            System.out.print("You're hand has busted!");
//            System.out.println("You've lost your wager.");
//            return true;
//        }
//
//        else if(playersHand.calculateHandValue() < 21){
//            System.out.print("The dealer reveals his hand... ");
//            for(Card card : dealersHand.returnCards()){
//                System.out.print(card.getCardName());
//            }
//            while(dealersHand.calculateHandValue() < 17){
//                dealersHand.addCard(deck.drawCard());
//                System.out.println("The dealer has drawn a card: " + dealersHand.returnCards().get(dealersHand.returnCards().size() - 1).getCardName());
//            }
//
//            if(dealersHand.calculateHandValue() == playersHand.calculateHandValue() ){
//                System.out.println("The dealer's hand is equivalent to yours.");
//                System.out.println("Your wager is returned to you. Good day!");
//                moneyGained = 0;
//            }
//
//            else if(dealersHand.calculateHandValue() >= 21){
//                System.out.println("The dealer has busted!");
//                System.out.println("Your wager has been doubled and returned to you. Good day!");
//                moneyGained += wager*2;
//            }
//
//            else if(dealersHand.calculateHandValue() > playersHand.calculateHandValue()){
//                System.out.println("The dealer's hand has beaten yours.");
//                System.out.println("You've lost your wager.");
//            }
//
//            else {
//                System.out.println("Your hand has beaten the dealer!");
//                System.out.println("Your wager has been doubled and returned to you. Good day!");
//                moneyGained += wager*2;
//            }
//
//            return false;
//        }
        return false;
    }

    @Override
    protected void playTurn() {
//
//        if(turnCount == 0){
//            System.out.println("Turn # " + turnCount);

//            dealersHand.addCard(deck.drawCard());
//            System.out.println("The dealer has drawn a card...");
//
//            playersHand.addCard(deck.drawCard());
//            System.out.println("You have drawn a card: " + playersHand.returnCards().get(0).getCardName());
//
//            dealersHand.addCard(deck.drawCard());
//            System.out.println("The dealer has drawn a card: " + dealersHand.returnCards().get(1).getCardName());
//
//            playersHand.addCard(deck.drawCard());
//            System.out.println("You have drawn a card: " +  playersHand.returnCards().get(1).getCardName());
//
//            boolean gameStatus = isGameOver();

//            turnCount++
//            }
//
//        if(turnCount == 1){
//
//            System.out.println("Turn # " + turnCount);
//
//            System.out.println("-------------------------");
//            System.out.print("Your hand:  ");
//            for(Card card : playersHand.returnCards()){
//                System.out.print(card.getCardName());
//            }
//            System.out.println("-------------------------");
//            System.out.print("Dealer's hand: ");
//            for(Card card : dealersHand.returnCards()){
//                if(card == dealersHand.returnCards().get(0)){
//                    System.out.print("? of ?");
//                }
//                else {System.out.print(card.getCardName());}
//            }
//            System.out.println("-------------------------");
//            System.out.println("Select an option:");
//            System.out.println("-------------------------");
//            System.out.println("(A) Stand (do nothing)");
//            System.out.println("(B) Hit (take on another card)");
//            System.out.println("(C) Surrender (give up your hand");
//            System.out.println("-------------------------");
//            System.out.print("Type Your Option:");
//            String choice = scanner.nextLine().trim().toUpperCase();
//
//            switch (choice){
//                case "A":
//
//                    break;
//                case "B":
//
//                    break;
//                case "C":
//
//                    break;
//                default:
//                    System.out.println("Invalid option. Please try again.");
//            }
//
//        }

    }

    @Override
    protected Player determineWinner() {
        return null;
    }

    @Override
    protected void endGame(Player winner) {

    }
}

