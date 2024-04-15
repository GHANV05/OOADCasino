import casino.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Gavin" , "Hanville" ,
                "gaha4495" , "1-800-988-2305");
    }
    @Test
    void testSetUsername() {
        String newUsername = "newUsername";
        player.setUsername(newUsername);
        assertEquals(newUsername, player.getUserName(), "The username should be updated.");
    }

    @Test
    void testSetPhoneNumber() {
        String newPhoneNumber = "999-999-9999";
        player.setPhoneNumber(newPhoneNumber);
        assertEquals(newPhoneNumber, player.getPhoneNumber(), "The phone number should be updated.");
    }
    @Test
    void testRecordWinAndTotalWins() {
        player.recordWin("Blackjack");
        assertEquals(1, player.getTotalWins(), "The total wins should be 1 after recording a win.");
    }
    @Test
    void testRecordLossAndTotalLosses() {
        player.recordLoss("Poker");
        assertEquals(1, player.getTotalLosses(), "The total losses should be 1 after recording a loss.");
    }
    @Test
    void testWinRateCalculation() {
        player.recordWin("Blackjack");
        player.recordLoss("Poker");
        assertEquals(1.0, player.getTotalWinRate(), "The win rate should be 1.0 with equal wins and losses.");
    }
    @Test
    void testGetGameStats() {
        player.recordWin("Roulette");
        Integer[] stats = player.getGameStats("Roulette");
        assertArrayEquals(new Integer[]{1, 0}, stats, "The stats for Roulette should reflect the recorded win.");
    }

    @Test
    void testAccountBalanceOperations() {
        player.deposit(100);
        assertEquals(1100, player.getAccountBalance(), "The account balance should be 1100 after depositing.");

        player.withdraw(50);
        assertEquals(1050, player.getAccountBalance(), "The account balance should be 1050 after withdrawing.");

        player.withdraw(1100); // Attempting to overdraw
        assertEquals(1050, player.getAccountBalance(), "The account balance should remain 1050 after failed withdrawal.");
    }
}
