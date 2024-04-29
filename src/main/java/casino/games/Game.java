package casino.games;

import casino.fileDBMS.CSVFileManager;
import casino.user.Player;

import java.util.ArrayList;
import java.util.List;

/*
* --Game--
* Superclass using a Template Pattern to utilize
* repeated functionalities and / or add additional features
* */

public abstract class Game {

    public Player mainPlayer;
    protected String name;
    protected List<Player> players;
    protected int maxPlayers;

    public Leaderboard leaderboard;
//    CSVFileManager slotsLeaderboardFM = new CSVFileManager("/SlotsLeaderboard.csv");
//    CSVFileManager blackjackLeaderboardFM = new CSVFileManager("/BlackJackLeaderboard.csv");
//    CSVFileManager pokerLeaderboardFM = new CSVFileManager("/PokerLeaderboard.csv");

    //Include a leader specific to each Game subclass

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
    }

    public Game() {

    }

    // Template method defining the game flow
    public final void play() {
        initializeGame();
        while (!isGameOver()) {
            playTurn();
        }
        Player winner = determineWinner();
        endGame(winner);
    }

    protected abstract void initializeGame();
    protected abstract boolean isGameOver();
    protected abstract void playTurn();
    protected abstract Player determineWinner();
    protected abstract void endGame(Player winner);
    public String getName() {return name; }

    // Other common methods like addPlayer, removePlayer, displayLeaderboard, etc.
    public void displayLeaderboard () {
        this.leaderboard.displayLeaderboard();
    }
}

