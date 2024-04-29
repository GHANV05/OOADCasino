package casino.fileDBMS;

import casino.games.Leaderboard;
import casino.user.Player;
import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVFileManager {
    private final String csvFilePath;

    public CSVFileManager(String filePath) {
        this.csvFilePath = filePath;
    }

    public void initializeFile() {
        try {
            Path path = Paths.get(csvFilePath);
            if (Files.notExists(path)) {
                try (BufferedWriter writer = Files.newBufferedWriter(path);
                     CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Username", "TotalWins", "TotalLosses", "AccountBalance"))) {
                    csvPrinter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePlayerData(List<Player> players) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Username", "TotalWins", "TotalLosses", "AccountBalance"))) {
            for (Player player : players) {
                csvPrinter.printRecord(player.getUserName(), player.getTotalWins(), player.getTotalLosses(), player.getAccountBalance());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Player> readPlayerData() {
        List<Player> players = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("Username", "TotalWins", "TotalLosses", "AccountBalance")
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .parse(reader);

            for (CSVRecord record : records) {
                try {
                    String username = record.get("Username").trim();
                    int totalWins = Integer.parseInt(record.get("TotalWins").trim());
                    int totalLosses = Integer.parseInt(record.get("TotalLosses").trim());
                    double accountBalance = Double.parseDouble(record.get("AccountBalance").trim());

                    Player player = new Player("N/A", "N/A", username, "000-000-0000");
                    player.setTotalWins(totalWins);
                    player.setTotalLosses(totalLosses);
                    player.setAccountBalance(accountBalance);
                    players.add(player);
                } catch (Exception e) {
                    System.err.println("Error processing record: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read player data: " + e.getMessage());
            e.printStackTrace();
        }
        return players;
    }



    public void updatePlayerRecord(Player player) {
        // Read all data, update the specific player's data, write all data back
        List<Player> players = readPlayerData();
        players.removeIf(p -> p.getUserName().equals(player.getUserName())); // Remove old entry
        players.add(player); // Add updated entry
        writePlayerData(players);
    }

    public void writeLeaderboardData(Leaderboard leaderboard) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Username", "TotalWins", "TotalLosses", "Winrate"))) {
            for (Leaderboard.PlayerStats stats : leaderboard.getLeaderboardList()) {
                csvPrinter.printRecord(stats.username, stats.getTotalWins(), stats.getTotalLosses(), stats.getWinRate());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Leaderboard readLeaderboardData() {
        Leaderboard leaderboard = new Leaderboard();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord record : csvParser) {
                String username = record.get("Username");
                int totalWins = Integer.parseInt(record.get("TotalWins"));
                int totalLosses = Integer.parseInt(record.get("TotalLosses"));
                // Create PlayerStats and add to Leaderboard
                Leaderboard.PlayerStats stats = new Leaderboard.PlayerStats(username);
                stats.setWins(totalWins);
                stats.setLosses(totalLosses);
                leaderboard.addPlayerStats(stats);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }
}
