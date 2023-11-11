package eetac.upc.dsa;

import org.junit.Test;
import eetac.upc.dsa.models.Game;
import eetac.upc.dsa.models.User;
import eetac.upc.dsa.GameManager;
import eetac.upc.dsa.GameManagerImpl;
import static org.junit.Assert.*;
public class JSONServiceTest {

    // Game
    @Test
    public void getGame() {
        Game g = new Game("Aladdin",7);
        GameManager gm = GameManagerImpl.getInstance();;
        assertEquals(gm.getGame(g.GetGameId()),g);
    }

    @Test
    public void getGameInJSON() throws Exception {

    }

    @Test
    public void createGameInJSON() throws Exception {

    }


    // User

    @Test
    public void getUser()  {
        User u = new User("Manolo");
        GameManager gm = GameManagerImpl.getInstance();;
        assertEquals(gm.getGame(u.GetGameId()),u);
    }

    @Test
    public void getUserInJSON() throws Exception {

    }

    @Test
    public void createUserInJSON() throws Exception {

    }
}
