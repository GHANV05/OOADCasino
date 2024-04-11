package casino.menu;

import casino.games.Game;
import casino.games.Blackjack;
import casino.games.Poker;
import casino.games.SlotMachine;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameMenu {
    private Scanner scanner;
    private Map<String, Game> games;

    public GameMenu() {
        this.scanner = new Scanner(System.in);
        this.games = new HashMap<>();
        initializeGames();
    }

    private void initializeGames() {
        // Initialize and add games to the list
        // For each game, you can instantiate it and add it to the games map with a unique key
        games.put("A", new Blackjack());
        games.put("B", new Poker());
        games.put("C", new SlotMachine());
    }

    public void displayMenu() {
        System.out.println("+============================+");
        System.out.println("Welcome to the Game Menu!");
        System.out.println("+============================+");
        System.out.println("Select a game to play:");
        System.out.println("-------------------------");
        // Dynamically list out all games
        games.forEach((key, value) -> System.out.println("(" + key + ") " + value.getName()));
        System.out.println("(Q) Quit...");
        System.out.println("-------------------------");
        System.out.print("Type Your Option: ");

        String choice = scanner.nextLine().trim().toUpperCase();

        if (games.containsKey(choice)) {
            playGame(games.get(choice));
        } else if ("Q".equals(choice)) {
            System.out.println("Returning to the main menu...");
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    private void playGame(Game game) {
        System.out.println("Now playing: " + game.getName());
        game.play();  // Start the game. Game class and/or subclasses need a play() method.
    }
}
