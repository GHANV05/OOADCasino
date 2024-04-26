package casino.fileDBMS;

import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

//**TO-DO**
// - Adjust class implementation to pass provided tests in LeaderboardTest
// - Refactor and implement in order to create a leaderboard for each Game Class (Slots Leaderboard, Blackjack leaderboard, etc.)
// - Outstanding method implementations


//Object to track and update player statistics specific to certain Games (subclasses of Game)
public class Leaderboard {
    private final String csvFilePath; // Variable to hold the path to the CSV file

    public Leaderboard(String filePath) {
        this.csvFilePath = filePath; // Assign the passed file path to the variable
    }

    public void initializeLeaderboard() {
        try {
            Path path = Paths.get(csvFilePath);
            if (Files.notExists(path)) {
                BufferedWriter writer = Files.newBufferedWriter(path);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Username", "TotalWins", "TotalLosses", "Winrate"));
                csvPrinter.flush();
                csvPrinter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CSVRecord> loadLeaderboard() {
        try {
            Reader in = new FileReader(csvFilePath);
            CSVParser parser = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            return parser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void addOrUpdatePlayer(String username, int wins, int losses) {
        List<CSVRecord> records = loadLeaderboard();
        boolean exists = false;
        List<String[]> updatedRecords = new ArrayList<>();

        for (CSVRecord record : records) {
            if (record.get("Username").equals(username)) {
                exists = true;
                int totalWins = Integer.parseInt(record.get("TotalWins")) + wins;
                int totalLosses = Integer.parseInt(record.get("TotalLosses")) + losses;
                double winrate = totalWins / (double) (totalWins + totalLosses);
                updatedRecords.add(new String[]{username, String.valueOf(totalWins), String.valueOf(totalLosses), String.valueOf(winrate)});
            } else {
                updatedRecords.add(new String[]{record.get("Username"), record.get("TotalWins"), record.get("TotalLosses"), record.get("Winrate")});
            }
        }

        if (!exists) {
            double winrate = wins / (double) (wins + losses);
            updatedRecords.add(new String[]{username, String.valueOf(wins), String.valueOf(losses), String.valueOf(winrate)});
        }

        writeLeaderboard(updatedRecords);
    }

    public void writeLeaderboard(List<String[]> updatedRecords) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilePath));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

            for (String[] record : updatedRecords) {
                csvPrinter.printRecord((Object[]) record);
            }

            csvPrinter.flush();
            csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortLeaderboardByWinrate() {
        List<CSVRecord> records = loadLeaderboard();
        records.sort((record1, record2) -> {
            double winrate1 = Double.parseDouble(record1.get("Winrate"));
            double winrate2 = Double.parseDouble(record2.get("Winrate"));
            return Double.compare(winrate2, winrate1);
        });

        List<String[]> sortedRecords = new ArrayList<>();
        for (CSVRecord record : records) {
            sortedRecords.add(new String[]{record.get("Username"), record.get("TotalWins"), record.get("TotalLosses"), record.get("Winrate")});
        }

        writeLeaderboard(sortedRecords);
    }

    public void displayLeaderboard(){
        //**TO-DO**
        // Displays the contents of the leaderboard to the terminal
    }
}
