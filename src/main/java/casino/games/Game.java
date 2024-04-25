package casino.games;

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
}

