
import casino.fileDBMS.CSVFileManager;
import casino.user.Player;
import casino.user.PlayerDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerDatabaseTest {
    private PlayerDatabase playerDatabase;
    private CSVFileManager fileManager;
    private String testFilePath = "testPlayerDB.csv";  // Test-specific CSV file path

    @BeforeEach
    public void setUp() {
        // Ensure any previous test data is cleared before each test
        Path path = Paths.get(testFilePath);
        try {
            Files.deleteIfExists(path);
        } catch (Exception e) {
            System.err.println("Failed to delete test file: " + e.getMessage());
        }
        fileManager = new CSVFileManager(testFilePath);
        playerDatabase = new PlayerDatabase(fileManager);
    }

    @AfterEach
    public void tearDown() {
        // Clean up after tests
        Path path = Paths.get(testFilePath);
        try {
            Files.deleteIfExists(path);
        } catch (Exception e) {
            System.err.println("Failed to delete test file: " + e.getMessage());
        }
    }
    @Test
    public void testAddPlayer() {
        Player player = new Player("John", "Doe", "johndoe", "1234567890");
        playerDatabase.addPlayer(player);
        assertTrue(playerDatabase.playerExists("johndoe"), "Player should exist after being added.");
    }

    @Test
    public void testAddPlayerTwice() {
        Player player = new Player("John", "Doe", "johndoe", "1234567890");
        playerDatabase.addPlayer(player);
        Player samePlayer = new Player("John", "Doe", "johndoe", "1234567890");
        playerDatabase.addPlayer(samePlayer);  // Attempt to add the same player again
        assertEquals(1, playerDatabase.getAllPlayers().size(), "There should only be one entry for the same username.");
    }

    @Test
    public void testGetPlayer() {
        Player player = new Player("Jane", "Doe", "janedoe", "0987654321");
        playerDatabase.addPlayer(player);
        Player retrievedPlayer = playerDatabase.getPlayer("janedoe");
        assertEquals("Jane", retrievedPlayer.getFirstName(), "Retrieved player should have the first name 'Jane'.");
        assertEquals("janedoe", retrievedPlayer.getUserName(), "Retrieved player should have the username 'janedoe'.");
    }

    @Test
    public void testPlayerExists() {
        Player player = new Player("John", "Doe", "johndoe", "1234567890");
        playerDatabase.addPlayer(player);
        assertTrue(playerDatabase.playerExists("johndoe"), "Player should exist.");
        assertFalse(playerDatabase.playerExists("janedoe"), "Player should not exist.");
    }

    @Test
    public void testLogin() {
        Player player = new Player("John", "Doe", "johndoe", "1234567890");
        playerDatabase.addPlayer(player);
        assertNotNull(playerDatabase.login("johndoe"), "Login should be successful for existing user.");
        assertNull(playerDatabase.login("nonexistent"), "Login should fail for non-existent user.");
    }
}
