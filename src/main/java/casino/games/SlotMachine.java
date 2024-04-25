package casino.games;

import casino.user.Player;

public class SlotMachine extends Game {
    private static final int BET_AMOUNT = 10;
    public double playerStartMoney = 0.0;
    public SlotMachine(String name, int maxPlayers, Player mainPlayer) {
        super(name, maxPlayers);
        this.mainPlayer = mainPlayer;
        this.playerStartMoney = mainPlayer.getAccountBalance();
    }
    @Override
    public void initializeGame() {
        System.out.println("Welcome to " + getName() + "!");
        System.out.println("Each spin costs $" + BET_AMOUNT + ".");
        System.out.println("You're starting with a balance of $" + mainPlayer.getAccountBalance());
        playerStartMoney = mainPlayer.getAccountBalance();
    }

    @Override
    public boolean isGameOver() {
        return mainPlayer.getAccountBalance() < BET_AMOUNT;
    }

    @Override
    public void playTurn() {
        mainPlayer.withdraw(BET_AMOUNT);
        System.out.println("Spinning the slot machine...");

        int result = (int) (Math.random() * 100); //Randomize Outcome of the spin
        if (result < 50) {
            System.out.println("Sorry, you lost this round.");
        }
        else{
            int winings = BET_AMOUNT * 2;
            mainPlayer.deposit(winings);
            System.out.println("Congratulations! You won $" + winings);
        }

        System.out.println("Current balance$" + mainPlayer.getAccountBalance());
    }

    //Will determine winner (financial earnings/losses amount)
    @Override
    public Player determineWinner() {
        double earnings = mainPlayer.getAccountBalance() - playerStartMoney;
        if (mainPlayer.getAccountBalance() > playerStartMoney) {  // Assuming they started with $1000
            System.out.println("Player Won: $" + earnings);
            return mainPlayer;
        }
        System.out.println("Player Lost: $" + earnings);
        return mainPlayer;
    }

    //Updates player game record at the end of the simulation
    @Override
    public void endGame(Player winner) {
        if (winner != null) {
            System.out.println("Game Over! You leave with $" + winner.getAccountBalance() + ", nice playing!");
            winner.recordWin(getName());
        } else {
            System.out.println("Game Over! You leave with $" + mainPlayer.getAccountBalance() + ", better luck next time!");
            mainPlayer.recordLoss(getName());
        }
    }
}
