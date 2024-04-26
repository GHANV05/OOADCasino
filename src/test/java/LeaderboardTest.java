import casino.fileDBMS.Leaderboard;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {
    private static final Path TEST_CSV_PATH = Paths.get("test_leaderboard.csv");
    private Leaderboard leaderboard;

    @BeforeEach
    void setUp() throws IOException {
        // Set up a fresh state before each test
        Files.deleteIfExists(TEST_CSV_PATH); // Ensure any previous test file is deleted
        leaderboard = new Leaderboard(TEST_CSV_PATH.toString());
        leaderboard.initializeLeaderboard(); // Create a new file for the leaderboard
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(TEST_CSV_PATH);
    }

    @Test
    void testInitializeLeaderboard() {
        assertTrue(Files.exists(TEST_CSV_PATH));
    }

    @Test
    void testLoadLeaderboard() {
        List<CSVRecord> records = leaderboard.loadLeaderboard();
        assertNotNull(records);
    }

    @Test
    void testAddOrUpdatePlayer() {
        String username = "testuser";
        leaderboard.addOrUpdatePlayer(username, 1, 0);
        List<CSVRecord> records = leaderboard.loadLeaderboard();

        boolean found = false;
        for (CSVRecord record : records) {
            if (record.get("Username").equals(username)) {
                found = true;
                assertEquals("1", record.get("TotalWins"));
                assertEquals("0", record.get("TotalLosses"));
                assertEquals("1.0", record.get("Winrate"));  // Depending on how winrate is formatted
                break;
            }
        }
        assertTrue(found, "New player should have been added to the leaderboard");
    }

    @Test
    void testSortLeaderboardByWinrate() {
        // Add multiple players with different winrates
        leaderboard.addOrUpdatePlayer("user1", 2, 1); // 66.7%
        leaderboard.addOrUpdatePlayer("user2", 1, 0); // 100%
        leaderboard.addOrUpdatePlayer("user3", 0, 1); // 0%

        // Sort the leaderboard
        leaderboard.sortLeaderboardByWinrate();
        List<CSVRecord> records = leaderboard.loadLeaderboard();

        assertEquals("user2", records.get(0).get("Username"));
        assertEquals("user1", records.get(1).get("Username"));
        assertEquals("user3", records.get(2).get("Username"));
    }

    // ... Other tests for update and remove functionalities
}
