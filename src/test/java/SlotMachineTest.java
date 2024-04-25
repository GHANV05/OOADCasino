import casino.games.SlotMachine;
import casino.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SlotMachineTest {
    private SlotMachine slotMachine;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("John","Doe", "johndoe123", "12345");
        slotMachine = new SlotMachine("Lucky Spin", 1, player);  // Use TestableSlotMachine here
    }

    @Test
    void testInitializeGame() {
        slotMachine.initializeGame();
        assertEquals(slotMachine.playerStartMoney, player.getAccountBalance());
    }

    @Test
    void testIsGameOver() {
        // Set balance to a value less than BET_AMOUNT
        player.withdraw(995);
        assertTrue(slotMachine.isGameOver(), "Game should be over if balance is less than the bet amount");
    }

    @Test
    void testDetermineWinner() {
        // Force the player to win
        player.deposit(100);  // Increase balance to ensure a win
        Player winner = slotMachine.determineWinner();
        assertNotNull(winner, "Winner should not be null if player has more than starting balance");
        assertEquals(player, winner, "Winner should be the main player if they have gained money");
    }
    @Test
    void testEndGame() {
        // Assuming the game records stats
        player.deposit(100);  // Player ends with a win
        slotMachine.endGame(player);
        assertEquals(1, player.getTotalWins(), "Total wins should be updated after game ends");
        assertEquals(0, player.getTotalLosses(), "Total losses should remain the same if player wins");
    }
}