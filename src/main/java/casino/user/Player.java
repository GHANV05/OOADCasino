package casino.user;

import casino.games.blackjack.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    //Personal information
    private String firstName;
    private String lastName;
    private String wholeName;
    private String username;
    private String phoneNumber;
    private List<Card> hand;

    //Player stats
    private int totalWins;
    private int totalLosses;
    private Map<String, Integer[]> gameStats; // Key: Game name, Value: [Wins, Losses]

    //Financial
    private double accountBalance = 1000.00; //everyone starts with 1000.00

    //Constructors
    public Player(String firstName, String lastName, String username, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wholeName = firstName + " " + lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.gameStats = new HashMap<>();
    }

    //Getters and Setters for Personal Information
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getWholeName() {
        return wholeName;
    }

    public String getUserName() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUsername(String s) {
        this.username = s;
    }

    public void setPhoneNumber(String s) {
        this.phoneNumber = s;
    }

    //Getters and Setters for Player Stats
    public void recordWin(String gameName) {
        gameStats.putIfAbsent(gameName, new Integer[]{0, 0});
        gameStats.get(gameName)[0]++;
        totalWins++;
    }

    public void recordLoss(String gameName) {
        gameStats.putIfAbsent(gameName, new Integer[]{0, 0});
        gameStats.get(gameName)[1]++;
        totalLosses++;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public double getTotalWinRate() {
        return (double) getTotalWins() / (double) getTotalLosses();
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public void setTotalLosses(int totalLosses) {
        this.totalLosses = totalLosses;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }


    // Retrieve specific game stats
    public Integer[] getGameStats(String gameName) {
        return gameStats.getOrDefault(gameName, new Integer[]{0, 0});
    }

    //Account balance management
    public double getAccountBalance() {
        return accountBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
        }
    }

    public void withdraw(double amount){
        if(amount > 0 && accountBalance >= amount){
            accountBalance -= amount;
        }
        else {
            System.out.println("Insufficient Funds!");
            System.out.print("Current Balance: " + accountBalance);
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }
}
