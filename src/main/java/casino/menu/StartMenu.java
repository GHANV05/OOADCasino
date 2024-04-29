package casino.menu;

import casino.fileDBMS.CSVFileManager;
import casino.user.Player;
import casino.user.PlayerDatabase;
import casino.user.PlayerFactory;
import java.util.Scanner;

public class StartMenu {
    private Scanner scanner;
    private CasinoLobbyMenu lobbyMenu;
    private PlayerDatabase playerDB;
    private CSVFileManager playerFileManager;

    public StartMenu() {
        this.scanner = new Scanner(System.in);
        String filePath = "playerDB.csv"; // Assuming the file is in the root directory of the project
        this.playerFileManager = new CSVFileManager(filePath);
        this.playerFileManager.initializeFile();
        this.playerDB = new PlayerDatabase(playerFileManager);
        this.playerDB.loadPlayers(); // Load players at the start of the application
    }

    public void displayMenu() {
        boolean run = true;
        while (run) {
            System.out.println("+============================+");
            System.out.println("Welcome to Virtual Vegas!");
            System.out.println("+============================+");
            System.out.println("Select an option:");
            System.out.println("-------------------------");
            System.out.println("(A) Create New User");
            System.out.println("(B) Login as Existing User");
            System.out.println("(C) Continue as Guest");
            System.out.println("(Q) Quit...");
            System.out.println("-------------------------");
            System.out.print("Type Your Option:");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    Player player = createNewUser();
                    if (player != null) {
                        lobbyMenu = new CasinoLobbyMenu(player);
                        lobbyMenu.addPlayer(player);
                        lobbyMenu.displayMenu();
                    }
                    break;
                case "B":
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine().trim();
                    Player existingPlayer = playerDB.login(username);
                    if (existingPlayer != null) {
                        lobbyMenu = new CasinoLobbyMenu(existingPlayer);
                        lobbyMenu.addPlayer(existingPlayer);
                        lobbyMenu.displayMenu();
                    }
                    break;
                case "C":
                    Player guest = continueAsGuest();
                    lobbyMenu = new CasinoLobbyMenu(guest);
                    lobbyMenu.addPlayer(guest);
                    lobbyMenu.displayMenu();
                    break;
                case "Q":
                    playerDB.savePlayers(); // Save players before exiting
                    System.out.println("Goodbye!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private Player createNewUser() {
        System.out.println("\n--- Create New User ---");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Choose a Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        if (!playerDB.playerExists(username)) {
            Player newUser = PlayerFactory.createPlayer(firstName, lastName, username, phoneNumber);
            playerDB.addPlayer(newUser);
            System.out.println("User created successfully! Welcome, " + newUser.getWholeName() + "!");
            return newUser;
        } else {
            System.out.println("Username already taken. Please try a different username.");
            return null;
        }
    }

    private Player continueAsGuest() {
        Player guestUser = PlayerFactory.createPlayer("Guest", "User", "guestuser", "000-000-0000");
        System.out.println("Continuing as Guest. Welcome, " + guestUser.getWholeName() + "!");
        return guestUser;
    }

    public static void main(String[] args) {
        StartMenu menu = new StartMenu();
        menu.displayMenu();
    }
}
