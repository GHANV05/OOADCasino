package casino.games;

import casino.user.Player;

import java.util.Random;
import java.util.Scanner;

//Edits (really easy to win atm)
// --> larger slots matrix,
// --> more symbols (unique combinations of certain symbols lead to different multipliers to earnings)
// --> Delay between each print of symbol to (simulating each slot symbol "falling" into place"

public class SlotMachine extends Game {
    /***********************************************************************************************************************/
    private static final int BET_AMOUNT = 20;
    //Emoji symbols and their Unicode encodings (UTF-8) not working Sadge
    //private static final String[] SYMBOLS = {"\uD83C\uDF52", "\uD83C\uDF4B", "\uD83C\uDF47", "\uD83C\uDF49", "\uD83C\uDF53"};
    private static final String[] SYMBOLS = {" ♠ ", " ♥ ", " ♦ ", " ♣ " , " $ " , " # "};
    private boolean quit = false;
    private final Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    public double playerStartMoney = 0.0;
    /***********************************************************************************************************************/
    public SlotMachine(String name, int maxPlayers, Player mainPlayer) {
        super(name, maxPlayers);
        this.mainPlayer = mainPlayer;
        this.playerStartMoney = mainPlayer.getAccountBalance();
        this.leaderboard = new Leaderboard();
        this.leaderboard.addPlayer(mainPlayer.getUserName());
    }
    @Override
    public void initializeGame() {
        quit = false;
        System.out.println("Welcome to " + getName() + "!");
        System.out.println("Each spin costs $" + BET_AMOUNT + ".");
        System.out.println("You're starting with a balance of $" + mainPlayer.getAccountBalance());
        playerStartMoney = mainPlayer.getAccountBalance();
    }

    @Override
    public boolean isGameOver() {
        return mainPlayer.getAccountBalance() < BET_AMOUNT || quit;
    }


    @Override
    public void playTurn() {
        boolean run = true;
        quit = false;
        while(run) {
            System.out.println("+======SLOTS======+");
            System.out.println("(A) Game Rules...");
            System.out.println("(B) Play a round!");
            System.out.println("(C) View Leaderboard!");
            System.out.println("(Q) Head Back to Game Menu...");
            System.out.println("+===================+");
            System.out.print("Type Your Option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    //Explain Unique symbol combinations...
                    System.out.println("Match three symbols in a row, column, or diagonal to win!");
                    break;
                case "B":
                    if (mainPlayer.getAccountBalance() >= BET_AMOUNT) {
                        mainPlayer.withdraw(BET_AMOUNT);
                        if (playRound()) {
                            int winnings = BET_AMOUNT * 10;
                            mainPlayer.deposit(winnings);
                            System.out.println("Congratulations! You won $" + winnings);
                            leaderboard.recordWin(mainPlayer.getUserName());
                            mainPlayer.recordWin(this.getName());
                        } else {
                            System.out.println("Sorry, you lost this round.");
                            leaderboard.recordLoss(mainPlayer.getUserName());
                            mainPlayer.recordLoss(this.getName());
                        }
                        System.out.println("Current balance: $" + mainPlayer.getAccountBalance());
                    } else {
                        System.out.println("Insufficient balance to play.");
                        run = false;
                    }
                    break;
                case "C":
                    leaderboard.displayLeaderboard();
                    break;
                case "Q":
                    System.out.println("Quitting Slots and returning to the Game Lobby...");
                    quit = true;  // Set the quit flag to true
                    run = false;   // Stop the loop
                    return;        // Exit the method
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private boolean playRound() {
        String[][] grid = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = SYMBOLS[random.nextInt(SYMBOLS.length)];
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(); // Move to next line after each row
        }
        return checkWin(grid);
    }

    private boolean checkWin(String[][] grid) {
        // Check rows, columns and diagonals
        for (int i = 0; i < 3; i++) {
            if (grid[i][0].equals(grid[i][1]) && grid[i][1].equals(grid[i][2])) return true; // Rows
            if (grid[0][i].equals(grid[1][i]) && grid[1][i].equals(grid[2][i])) return true; // Columns
        }
        if (grid[0][0].equals(grid[1][1]) && grid[1][1].equals(grid[2][2])) return true; // Major diagonal
        if (grid[0][2].equals(grid[1][1]) && grid[1][1].equals(grid[2][0])) return true; // Minor diagonal
        return false;
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
            mainPlayer.recordLoss(this.getName());
        }
    }
}
