package casino.games;

import casino.user.Player;

public class Blackjack extends Game {
    public Blackjack(String name, int maxPlayers) {
        super(name, maxPlayers);
    }

    public Blackjack() {
        super();
    }

    @Override
    protected void initializeGame() {

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
