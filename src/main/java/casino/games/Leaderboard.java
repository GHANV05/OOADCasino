package casino.games;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private List<PlayerStats> leaderboard;

    public Leaderboard() {
        this.leaderboard = new ArrayList<>();
    }

    // Adds statistics directly to the leaderboard
    public void addPlayerStats(PlayerStats stats) {
        // Check if the player already exists to prevent duplicates
        PlayerStats existingStats = findPlayer(stats.username);
        if (existingStats == null) {
            leaderboard.add(stats);
        } else {
            existingStats.setWins(stats.getTotalWins());
            existingStats.setLosses(stats.getTotalLosses());
        }
    }

    public List<PlayerStats> getLeaderboardList() {
        return leaderboard;
    }

    public void addPlayer(String username) {
        if(leaderboard.stream().noneMatch(p -> p.username.equals(username))) {
            leaderboard.add(new PlayerStats(username));
        }
    }

    public void recordWin(String username) {
        leaderboard.stream()
                .filter(p -> p.username.equals(username))
                .findFirst()
                .ifPresent(PlayerStats::recordWin);
    }

    public void recordLoss(String username) {
        leaderboard.stream()
                .filter(p -> p.username.equals(username))
                .findFirst()
                .ifPresent(PlayerStats::recordLoss);
    }

    public void displayLeaderboard() {
        // Sort by win rate descending; in case of a tie, sort by total wins descending, then by username
        leaderboard.sort(Comparator.comparingDouble(PlayerStats::getWinRate).reversed());
        leaderboard.forEach(System.out::println);
    }

    public PlayerStats findPlayer(String username) {
        return leaderboard.stream()
                .filter(p -> p.username.equals(username))
                .findFirst()
                .orElse(null);
    }

    public PlayerStats getPlayerAtIndex(int index) {
        if (index >= 0 && index < leaderboard.size()) {
            return leaderboard.get(index);
        }
        return null;
    }

    public static class PlayerStats {
        public String username;
        private int totalWins;
        private int totalLosses;

        public PlayerStats(String username){
            this.username = username;
            this.totalWins = 0;
            this.totalLosses = 0;
        }

        public void recordWin() {
            totalWins++;
        }

        public void recordLoss() {
            totalLosses++;
        }

        public int getTotalWins() {
            return totalWins;
        }

        public int getTotalLosses() {
            return totalLosses;
        }

        public double getWinRate() {
            if(totalWins + totalLosses == 0){
                return 0.0;
            }
            return (double) totalWins / (totalWins + totalLosses) * 100;
        }

        @Override
        public String toString() {
            return String.format("%s: Wins: %d, Losses: %d, Winrate: %.2f%%", username, totalWins, totalLosses, getWinRate());
        }

        public void setWins(int totalWins) {
            this.totalWins = totalWins;
        }

        public void setLosses(int totalLosses) {
            this.totalLosses = totalLosses;
        }
    }
}
