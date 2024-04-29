import casino.games.Leaderboard;
import casino.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LeaderboardTest {
    private Leaderboard leaderboard;
    private Player mockPlayer;

    @BeforeEach
    public void setUp() {
        leaderboard = new Leaderboard();
        mockPlayer = new Player("test", "test", "test1" , "1234");
        leaderboard.addPlayer(mockPlayer.getUserName());
    }

    @Test
    public void testAddPlayer() {
        assertNotNull(leaderboard.findPlayer(mockPlayer.getUserName()));
    }

    @Test public void testRecordWin(){
        leaderboard.recordWin(mockPlayer.getUserName());
        assertEquals( 1, leaderboard.findPlayer(mockPlayer.getUserName()).getTotalWins());
    }

    @Test public void testRecordLoss(){
        leaderboard.recordLoss(mockPlayer.getUserName());
        assertEquals( 1, leaderboard.findPlayer(mockPlayer.getUserName()).getTotalLosses());
    }

    @Test public void testWinRateCalculation() {
        leaderboard.recordWin(mockPlayer.getUserName());
        leaderboard.recordLoss(mockPlayer.getUserName());
        double expectedWinRate = 50.0;
        assertEquals(expectedWinRate, leaderboard.findPlayer(mockPlayer.getUserName()).getWinRate(), 0.001);
    }

    @Test
    public void testLeaderboardSorting() {
        leaderboard.addPlayer("test1");
        leaderboard.addPlayer("test2");

        leaderboard.recordWin("test1");
        leaderboard.recordLoss("test1"); // 50% win rate

        leaderboard.recordWin("test2"); // 100% win rate, no losses

        leaderboard.displayLeaderboard();

        // Assert that "test2", with a higher win rate, comes first
        assertEquals("test2", leaderboard.getPlayerAtIndex(0).username);
        assertEquals("test1", leaderboard.getPlayerAtIndex(1).username);
    }
}
