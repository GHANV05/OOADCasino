import casino.fileDBMS.CSVFileManager;
import casino.games.Leaderboard;
import casino.user.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


@Nested
class CSVFileManagerTest {
    private CSVFileManager fileManager;
    private static final String TEST_FILE_PATH = "testPlayerDB.csv"; // Use a specific test file
    private static final Path testFilePath = Paths.get(TEST_FILE_PATH);

    @BeforeEach
    void setUp() throws Exception {
        // Ensure the file is clean before each test
        Files.deleteIfExists(testFilePath);
        Files.copy(Paths.get("originalTestPlayerDB.csv"), testFilePath); // Assume originalTestPlayerDB.csv is your pristine copy
        fileManager = new CSVFileManager(TEST_FILE_PATH);
    }

    @AfterEach
    void tearDown() throws Exception {
        // Clean up by deleting the test file to ensure no side effects
        Files.deleteIfExists(testFilePath);
    }

    @Test
    void testFileInitialization() {
        // Test initialization logic possibly by deleting the file and letting the manager recreate it
        fileManager.initializeFile();
        assertTrue(Files.exists(testFilePath), "File should be re-initialized if missing.");
    }

    @Test
    void testWriteAndReadPlayerData() {
        // Example data
        Player player1 = new Player("Test", "User", "testuser", "1000");
        player1.setTotalWins(5);
        player1.setTotalLosses(2);
        player1.setAccountBalance(500.0);

        fileManager.writePlayerData(List.of(player1));

        List<Player> players = fileManager.readPlayerData();
        assertFalse(players.isEmpty(), "Should retrieve data after writing.");
        assertEquals("testuser", players.get(0).getUserName(), "Username should match.");
    }

    @Test
    void testUpdatePlayerRecord() {
        Player player = new Player("Updater", "User", "updateuser", "1001");
        player.setTotalWins(1);
        player.setTotalLosses(0);
        player.setAccountBalance(300.0);

        fileManager.writePlayerData(List.of(player));

        player.setTotalWins(2); // Update win count
        fileManager.updatePlayerRecord(player);

        List<Player> updatedPlayers = fileManager.readPlayerData();
        assertEquals(2, updatedPlayers.get(0).getTotalWins(), "Player's wins should be updated to 2.");
    }

    @Test void testWriteAndReadLeaderboardData() {
        Leaderboard leaderboard = new Leaderboard();
        Leaderboard.PlayerStats stats1 = new Leaderboard.PlayerStats("johndoe");
        stats1.recordWin();
        stats1.recordLoss();

        Leaderboard.PlayerStats stats2 = new Leaderboard.PlayerStats("janedoe");
        stats2.recordWin();
        stats2.recordWin();

        leaderboard.addPlayerStats(stats1);
        leaderboard.addPlayerStats(stats2);

        fileManager.writeLeaderboardData(leaderboard);

        Leaderboard readLeaderboard = fileManager.readLeaderboardData();
        assertNotNull(readLeaderboard);
        assertEquals(2, readLeaderboard.getLeaderboardList().size());
        assertEquals(50.0, readLeaderboard.getLeaderboardList().get(0).getWinRate());
        assertEquals(100.0, readLeaderboard.getLeaderboardList().get(1).getWinRate());
    }
}


