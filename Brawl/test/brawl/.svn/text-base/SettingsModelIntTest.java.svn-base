package brawl;

import brawl.model.AI;
import brawl.model.Human;
import brawl.model.Player;
import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.GameMode;
import brawl.model.enums.PlayerID;
import junit.framework.TestCase;
import java.lang.reflect.Field;

/**
 *
 * @author Paul
 */
public class SettingsModelIntTest extends TestCase {
    
    public SettingsModelIntTest(String testName) {
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

        // Set a human and an ai, each with a different character
        settings.setCharacter(BrawlCharacter.MILTON);
        settings.setIsHuman(true);
        settings.setPlayer();
        settings.changeCurrentPlayerId();
        settings.setCharacter(BrawlCharacter.BEATRICE);
        settings.setIsHuman(false);
        settings.setDifficulty(2);
        settings.setPlayer();
        
        Player p1 = null, p2 = null;
        try
        {
            p1 = (Player)p1f.get(settings);
            p2 = (Player)p2f.get(settings);
        }
        catch (IllegalAccessException ex) {}
        assertEquals(BrawlCharacter.MILTON, p1.getDeck().getCharacter());
        assertEquals(Human.class, p1.getClass());
        assertEquals(BrawlCharacter.BEATRICE, p2.getDeck().getCharacter());
        assertEquals(AI.class, p2.getClass());
    }

    /**
     * Test of startGame method, of class SettingsModel.
     */
    public void testStartGame()
    {
        System.out.println("startGame");
        SettingsModel settings = new SettingsModel();
        settings.setMode(GameMode.TUTORIAL);
        settings.setPlayer();
        settings.setIsHuman(true);
        settings.changeCurrentPlayerId();
        settings.setDifficulty(2000);
        settings.setIsHuman(false);
        settings.setPlayer();
        MatchModel result = settings.startGame();
        Field gmf = null;
        try
        {
            gmf = MatchModel.class.getDeclaredField("mode");
            gmf.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}
        GameMode gm = null;
        try
        {
            gm = (GameMode)gmf.get(result);
        }
        catch (IllegalAccessException ex) {}
        assertEquals(gm, settings.getMode());
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
}