package casino.user;

public class PlayerFactory {
    public static Player createPlayer(String firstName, String lastName, String username, String phoneNumber) {
        return new Player(firstName, lastName, username, phoneNumber);
    }
}
