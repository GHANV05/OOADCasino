package casino.menu;

import casino.user.Player;
import java.util.Scanner;

//**TO-DO**
// - Implement/Utilize PlayerDB class functionality

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
            System.out.println("(C) Delete Player Account...");
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
                    System.out.println("+======================+");
                    System.out.println("Current Account Balance: $" + player.getAccountBalance());
                    System.out.println("+======================+");
                    break;
                case "C":
                    //**TO-DO** Delete Account
                    System.out.println("+======================+");
                    System.out.println("Are you sure you would like to delete your account...?");
                    System.out.print("Delete Account Yes or No [Y/N]:");
                    System.out.println("+======================+");
                    String choice0 = scanner.nextLine().trim().toUpperCase();
                    if(choice0 == "Y"){
                        //**TO-DO**
                        //Delete player from playerDB (they can stay in Game leaderboard's for history)
                        run = false; //Return player back to startMenu or proceed as a guest account?
                        break;
                    } else if (choice0 == "N") {
                        break;
                    }
                case "Q":
                    System.out.println("Returning to the Casino Lobby...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //
    private void editPersonalInformation() {
        System.out.print("Enter new Phone Number: ");
        String newPhoneNumber = scanner.nextLine();
        player.setPhoneNumber(newPhoneNumber);
        System.out.println("Phone Number updated successfully.");
        // TO-DO: Edit other personal information
    }

    private void displayPersonalInformation() {

    }
}
