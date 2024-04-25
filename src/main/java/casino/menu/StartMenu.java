package casino.menu;

import casino.user.Player;
import casino.menu.GameMenu;
import casino.user.PlayerFactory;

import java.util.Scanner;

public class StartMenu {
    private Scanner scanner;
    private CasinoLobbyMenu lobbyMenu;

    public StartMenu(){
        this.scanner = new Scanner(System.in);
        this.lobbyMenu = new CasinoLobbyMenu();
    }

    public void displayMenu(){
        boolean run = true;
        while(run){
            System.out.println("+============================+");
            System.out.println("Welcome to Virtual Vegas!");
            System.out.println("+============================+");
            System.out.println("Select an option:");
            System.out.println("-------------------------");
            System.out.println("(A) Create New User");
            System.out.println("(B) Login as Existing User (Not implemented yet)");
            System.out.println("(C) Continue as Guest");
            System.out.println("(Q) Quit...");
            System.out.println("-------------------------");
            System.out.print("Type Your Option:");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice){
                case "A":
                    //
                    Player player = createNewUser();
                    lobbyMenu.addPlayer(player);
                    lobbyMenu.displayMenu();
                    break;
                case "B":
                    //Login logic will go here
                    System.out.println("Login feature is not yet implemented");
                    //Call Game Menu
                    break;
                case "C":
                    Player guest = continueAsGuest();
                    lobbyMenu.addPlayer(guest);
                    lobbyMenu.displayMenu();
                    break;
                case "Q":
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

        // Use the PlayerFactory to create a new Player object
        Player newUser = PlayerFactory.createPlayer(firstName, lastName, username, phoneNumber);
        System.out.println("User created successfully! Welcome, " + newUser.getWholeName() + "!");
        // ***TO-DO*** Save the newUser object to database or user management system
        return newUser;
    }

    private Player continueAsGuest() {
        Player guestUser = PlayerFactory.createPlayer("Guest", "User", "guestuser", "000-000-0000");
        System.out.println("Continuing as Guest. Welcome, " + guestUser.getWholeName() + "!");
        return guestUser;
        // This Guest User can be used for ease of testing
    }


    public static void main(String[] args) {
        StartMenu menu = new StartMenu();
        menu.displayMenu();
    }
}
