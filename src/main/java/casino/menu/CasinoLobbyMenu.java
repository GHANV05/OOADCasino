package casino.menu;

import casino.user.Player;
import java.util.Scanner;



public class CasinoLobbyMenu {
    private Scanner scanner;
    private Player player;
    private GameMenu gameMenu;

    public CasinoLobbyMenu(Player player) {
        this.scanner = new Scanner(System.in);
        this.gameMenu = new GameMenu(player);
    }

    public void addPlayer(Player player){
        this.player = player;
    }

    public void displayMenu() {
        boolean run = true;
        while(run){
            System.out.println("+============================+");
            System.out.println("Welcome to the Casino Lobby, " + player.getUserName() + "!");
            System.out.println("+============================+");
            System.out.println("Select an option:");
            System.out.println("-------------------------");
            System.out.println("(A) Head to the Casino");
            System.out.println("(B) My Account");
            System.out.println("(Q) Quit to StartMenu...");
            System.out.println("-------------------------");
            System.out.print("Type Your Option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    gameMenu.addPlayer(player);
                    gameMenu.displayMenu(); // Direct to GameMenu
                    break;
                case "B":
                    // Placeholder for the User Account Menu
                    UserAccountMenu accountMenu = new UserAccountMenu(player);
                    accountMenu.displayMenu();
                    break;
                case "Q":
                    System.out.println("Returning to the Start Menu...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
