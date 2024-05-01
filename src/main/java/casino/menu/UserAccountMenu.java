package casino.menu;

import casino.user.Player;
import casino.user.PlayerDatabase;

import java.util.Scanner;

//**TO-DO**
// - Implement/Utilize PlayerDB class functionality

public class UserAccountMenu {
    private Scanner scanner;
    private Player player;
    PlayerDatabase playerDB;

    public UserAccountMenu(Player player) {
        this.scanner = new Scanner(System.in);
        this.player = player;
    }

    public void displayMenu(PlayerDatabase pdb) {
        playerDB = pdb;
        boolean run = true;
        while(run){
            displayUser();
            System.out.println("+============================+");
            System.out.println("My Account - Current Balance: $" + player.getAccountBalance());
            System.out.println("+============================+");
            System.out.println("Select an option:");
            System.out.println("-------------------------");
            System.out.println("(A) Edit Personal Information");
            System.out.println("(B) Add or Withdraw Funds");
            System.out.println("(C) Delete Player Account...");
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
                    manageFunds();
                    break;
                case "C":
                    //**TO-DO** Delete Account
                    deleteUserAccount();
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

    private void displayUser() {
        System.out.println("+=================================+");
        System.out.println("|       User Account Details      |");
        System.out.println("+=================================+");
        System.out.println("| Username: " + player.getUserName());
        System.out.println("| Full Name: " + player.getWholeName());
        System.out.println("| Phone Number: " + player.getPhoneNumber());
        System.out.println("| Total Wins: " + player.getTotalWins());
        System.out.println("| Total Losses: " + player.getTotalLosses());
        System.out.println("| Win Rate: " + (player.getTotalWins() > 0 ? String.format("%.2f%%", 100.0 * player.getTotalWins() / (player.getTotalWins() + player.getTotalLosses())) : "N/A"));
        System.out.println("| Current Balance: $" + player.getAccountBalance());
        System.out.println("+=================================+");
    }


    private void manageFunds() {
        System.out.println("Current Balance: $" + player.getAccountBalance());
        System.out.println("(1) Add Funds");
        System.out.println("(2) Withdraw Funds");
        System.out.print("Select an Option: ");
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                System.out.print("Enter Amount to Add: ");
                double addAmount = Double.parseDouble(scanner.nextLine());
                player.deposit(addAmount);
                System.out.println("$" + addAmount + " added. New Balance: $" + player.getAccountBalance());
                break;
            case "2":
                System.out.print("Enter Amount to Withdraw: ");
                double withdrawAmount = Double.parseDouble(scanner.nextLine());
                if (player.getAccountBalance() >= withdrawAmount) {
                    player.withdraw(withdrawAmount);
                    System.out.println("$" + withdrawAmount + " withdrawn. New Balance: $" + player.getAccountBalance());
                } else {
                    System.out.println("Insufficient funds.");
                }
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void editPersonalInformation() {
        System.out.println("Select information to edit:");
        System.out.println("(1) First Name");
        System.out.println("(2) Last Name");
        System.out.println("(3) Username");
        System.out.println("(4) Phone Number");
        System.out.print("Your choice: ");
        String choice = scanner.nextLine().trim();
        String newValue;
        switch (choice) {
            case "1":
                System.out.print("Enter new First Name: ");
                newValue = scanner.nextLine();
                player.setFirstName(newValue);
                break;
            case "2":
                System.out.print("Enter new Last Name: ");
                newValue = scanner.nextLine();
                player.setLastName(newValue);
                break;
            case "3":
                System.out.print("Enter new Username: ");
                newValue = scanner.nextLine();
                if (!playerDB.playerExists(newValue)) {
                    player.setUsername(newValue);
                } else {
                    System.out.println("Username already taken. Please choose another.");
                }
                break;
            case "4":
                System.out.print("Enter new Phone Number: ");
                newValue = scanner.nextLine();
                player.setPhoneNumber(newValue);
                break;
            default:
                System.out.println("Invalid choice.");
        }
        playerDB.updatePlayer(player); // Save changes to the database
    }

    //Currently does not return to StartMenu to create a new player...
    private void deleteUserAccount() {
        System.out.println("Are you sure you want to delete your account? (Y/N)");
        String response = scanner.nextLine().trim().toUpperCase();
        if ("Y".equals(response)) {
            playerDB.deletePlayer(player.getUserName());
            System.out.println("Account deleted successfully.");
            //run = false;
        }
    }
}
