package brawl;

import brawl.model.Player;
import brawl.model.enums.PlayerID;
import junit.framework.TestCase;
import java.lang.reflect.Field;

/**
 *
 * @author Paul
 */
public class SettingsModelTest extends TestCase {
    
    public SettingsModelTest(String testName) {
        super(testName);
    }

    /**
     * Test of setPlayer method, of class SettingsModel.
     */
    public void testSetPlayer()
    {
        System.out.println("setPlayer");
        SettingsModel settings = new SettingsModel();

        // Use java.lang.reflect to make private fields accessible
        Field p1f = null, p2f = null;
        try
        {
            p1f = SettingsModel.class.getDeclaredField("p1");
            p1f.setAccessible(true);
            p2f = SettingsModel.class.getDeclaredField("p2");
            p2f.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        settings.setPlayer();
        settings.changeCurrentPlayerId();
        settings.setPlayer();
        
        Player p1 = null, p2 = null;
        try
        {
            p1 = (Player)p1f.get(settings);
            p2 = (Player)p2f.get(settings);
        }
        catch (IllegalAccessException ex) {}
        assertNotNull(p1);
        assertNotNull(p2);
    }

    /**
     * Test of startGame method, of class SettingsModel.
     */
    public void testStartGame()
    {
        System.out.println("startGame");
        SettingsModel settings = new SettingsModel();
        settings.setPlayer();
        settings.changeCurrentPlayerId();
        settings.setPlayer();
        assertNotNull(settings.startGame());
    }

    /**
     * Test of changeCurrentPlayerId method, of class SettingsModel.
     */
    public void testChangeCurrentPlayerId()
    {
        System.out.println("changeCurrentPlayerId");
        SettingsModel settings = new SettingsModel();
        assertEquals(PlayerID.PLAYER1, settings.getCurrentPlayerId());
        settings.changeCurrentPlayerId();
        assertEquals(PlayerID.PLAYER2, settings.getCurrentPlayerId());
    }

    public void testGetDifficulty()
    {
        System.out.println("getDifficulty");
        SettingsModel settings = new SettingsModel();
        settings.setIsHuman(true);
        assertEquals(-1, settings.getDifficulty());
        settings.setIsHuman(false);
        settings.setDifficulty(2);
        assertEquals(2, settings.getDifficulty());
    }
}