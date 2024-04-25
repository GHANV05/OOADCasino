package casino.menu;

import casino.user.Player;
import casino.games.Game;
import casino.games.blackjack.Blackjack;
import casino.games.Poker;
import casino.games.SlotMachine;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameMenu {
    private Scanner scanner;
    private Map<String, Game> games;
    private Player player;

    public GameMenu(Player player) {
        this.scanner = new Scanner(System.in);
        this.games = new HashMap<>();
        initializeGames(player);
    }

    public void addPlayer(Player player){
        this.player = player;
    }

    private void initializeGames(Player player) {
        // Initialize and add games to the list
        // For each game, you can instantiate it and add it to the games map with a unique key
        games.put("A", new Blackjack("Blackjack", 4));
        games.put("B", new Poker("Poker" , 4));
        games.put("C", new SlotMachine("Slots" , 1, player));
    }

    public void displayMenu() {
        System.out.println("+============================+");
        System.out.println("Welcome \"" + this.player.getUserName() + "\" to the Game Menu!");
        System.out.println("+============================+");
        System.out.println("Select a game to play:");
        System.out.println("-------------------------");
        // Dynamically list out all games
        games.forEach((key, value) -> System.out.println("(" + key + ") " + value.getName()));
        System.out.println("(Q) Quit to Casino Lobby...");
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
