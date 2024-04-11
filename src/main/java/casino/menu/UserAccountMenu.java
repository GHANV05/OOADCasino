package casino.menu;

import casino.user.Player;
import java.util.Scanner;

public class UserAccountMenu {
    private Scanner scanner;
    private Player player;

    public UserAccountMenu(Player player) {
        this.scanner = new Scanner(System.in);
        this.player = player;
    }

    public void displayMenu() {
        boolean run = true;
        while(run){
            System.out.println("+============================+");
            System.out.println("My Account");
            //Add pretty print: player.displayPlayerInformation();
            System.out.println("+============================+");
            System.out.println("Select an option:");
            System.out.println("-------------------------");
            System.out.println("(A) Edit Personal Information");
            System.out.println("(B) Check Account Balance");
            // Add deposit or withdraw here
            System.out.println("(Q) Quit to Casino Lobby...");
            System.out.println("-------------------------");
            System.out.print("Type Your Option: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    // Implement functionality to edit personal information
                    editPersonalInformation();
                    break;
                case "B":
                    // Display the account balance
                    System.out.println("Current Account Balance: $" + player.getAccountBalance());
                    break;
                case "Q":
                    System.out.println("Returning to the Casino Lobby...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void editPersonalInformation() {
        System.out.print("Enter new Phone Number: ");
        String newPhoneNumber = scanner.nextLine();
        player.setPhoneNumber(newPhoneNumber);
        System.out.println("Phone Number updated successfully.");
        // TO-DO: Edit other personal information
    }
}
