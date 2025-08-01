package casino.user;

import casino.fileDBMS.CSVFileManager;

import java.util.*;

public class PlayerDatabase {
    private Map<String, Player> players;
    private CSVFileManager fileManager;

    public PlayerDatabase(CSVFileManager fileManager) {
        this.fileManager = fileManager;
        this.players = new HashMap<>();
        //loadPlayers();  // Consider loading players directly in the constructor
    }

    public void addPlayer(Player player) {
        if(!players.containsKey(player.getUserName())){
            players.put(player.getUserName(),player);
        }
        else{
            System.out.println("User already exists");
        }
    }

    public Player getPlayer(String username){
        return players.get(username);
    }

    public boolean playerExists(String username){
        return players.containsKey(username);
    }

    public void loadPlayers() {
        List<Player> loadedPlayers = fileManager.readPlayerData();
        for (Player player : loadedPlayers) {
            players.put(player.getUserName(), player);
        }
    }

    public void savePlayers() {
        fileManager.writePlayerData(new ArrayList<>(players.values()));
    }

    public Player login(String username){
        if(playerExists(username)){
            System.out.println("Login successful for " + username);
            return  getPlayer(username);
        }
        else{
            System.out.println("No such user exists");
            return null;
        }
    }

    public Collection<Player> getAllPlayers() {
        return players.values();
    }

    public void updatePlayer(Player updatedPlayer) {
        String username = updatedPlayer.getUserName();
        if (players.containsKey(username)) {
            players.put(username, updatedPlayer);  // Replace the old player data with the updated data
            savePlayers();  // Write changes back to the CSV
            System.out.println("Player data updated successfully.");
        } else {
            System.out.println("Player not found, cannot update.");
        }
    }


    public void deletePlayer(String username) {
        if (players.containsKey(username)) {
            players.remove(username);  // Remove the player from the map
            savePlayers();  // Update the CSV file
            System.out.println("Player removed successfully.");
        } else {
            System.out.println("Player not found, cannot delete.");
        }
    }

}
