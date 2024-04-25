import casino.menu.StartMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class StartMenuTest {
    @Test
    public void startMenuTest(){
        StartMenu menu = new StartMenu();
        menu.displayMenu();
    }
}
