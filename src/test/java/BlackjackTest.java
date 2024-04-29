import casino.games.SlotMachine;
import casino.games.blackjack.Blackjack;
import casino.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlackjackTest {
    private Blackjack blackjack;
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("John","Doe", "johndoe123", "12345");
        blackjack = new Blackjack("Blackjack", 1, player);
    }

    @Test
    void testGameConstructor(){
        assertNotNull(blackjack);
    }

    @Test
    void testInitializeGame() {

    }

}
