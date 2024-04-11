package casino.games;

import casino.user.Player;

public class Poker extends Game{
    public Poker(String name, int maxPlayers) {
        super(name, maxPlayers);
    }

    public Poker() {

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
