/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package brawl;

import brawl.model.Move;
import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.CardType;
import brawl.model.enums.GameMode;
import brawl.model.enums.PlayerID;
import brawl.ui.SwingUI;
import brawl.utils.KeyMapper;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.Map;
import javax.swing.JButton;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author Ebele
 */
public class GameControllerIntTest extends TestCase {

    public GameControllerIntTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

    public void testActionPerformed()
    {
        // verify the game mode
        // verify the character chosen

        GameController cont = new GameController();
        SettingsModel set = new SettingsModel();
        SwingUI cui = mock(SwingUI.class);

        cont.setView(cui);
        cont.setModel(set);

        cui.init();
        cont.actionPerformed(new ActionEvent(this, 0, "Training"));
        // GameController needs to set the game mode
        // use an assert to verify this is settingsModel
        assertEquals(GameMode.TRAINING, set.getMode());
        cont.actionPerformed(new ActionEvent(this, 0, "beatrice1"));
        cont.actionPerformed(new ActionEvent(this, 0, "human1"));
        cont.actionPerformed(new ActionEvent(this, 0, "Ready P1"));
        // verify in settingsModel that player 1's char is set to Beatrice and
        // is human
        assertEquals(BrawlCharacter.BEATRICE, set.getCharacter());
        assertTrue(set.isHuman());
        // verify that the current PlayerID is PLAYER2
        assertEquals(PlayerID.PLAYER2, set.getCurrentPlayerId());

        cont.actionPerformed(new ActionEvent(this, 0, "wilbur1"));
        cont.actionPerformed(new ActionEvent(this, 0, "computer easy"));
        // verify in settingsModel that player 2's char is set to Wilbur and is
        // AI
        assertEquals(BrawlCharacter.WILBUR, set.getCharacter());
        assertFalse(set.isHuman());
        // if user goes back to player 1...
        cont.actionPerformed(new ActionEvent(this, 0, "Back to Player 1"));
        cont.actionPerformed(new ActionEvent(this, 0, "milton1"));
        cont.actionPerformed(new ActionEvent(this, 0, "human1"));
        cont.actionPerformed(new ActionEvent(this, 0, "Ready P1"));
        // verify
        assertEquals(BrawlCharacter.MILTON, set.getCharacter());
        assertTrue(set.isHuman());
        // going to player 2 again
        cont.actionPerformed(new ActionEvent(this, 0, "esther1"));
        cont.actionPerformed(new ActionEvent(this, 0, "computer easy"));
        cont.actionPerformed(new ActionEvent(this, 0, "Ready P2"));
        // verify
        assertEquals(BrawlCharacter.ESTHER, set.getCharacter());
        assertFalse(set.isHuman());

        MatchModel match = set.startGame();
        Field mModel;
        try
        {
            mModel = GameController.class.getDeclaredField("matchModel");
            mModel.setAccessible(true);
            mModel.set(cont, match);
        }
        catch (NoSuchFieldException ex) {}
        catch (IllegalAccessException ex) {}
        // make sure match model was built correctly
            // probably a settings model issue if this is wrong...
        assertEquals(BrawlCharacter.MILTON, match.getPlayer(PlayerID.PLAYER1).getDeck().getCharacter());
        assertEquals(BrawlCharacter.ESTHER, match.getPlayer(PlayerID.PLAYER2).getDeck().getCharacter());
        // matchModel should have a method indicating what mode the game is in -
        // use that to verify game mode
    }

    public void testActionPerformed2()
    {
        GameController cont = new GameController();
        SettingsModel set = new SettingsModel();
        SwingUI cui = mock(SwingUI.class);

        cont.setView(cui);
        cui.init();
        cont.setModel(set);

        cont.actionPerformed(new ActionEvent(this, 0, "Tutorial"));
        assertEquals(GameMode.TUTORIAL, set.getMode());
    }
    
    public void testKeyReleasedInGame()
    {
        GameController cont = new GameController();
        SettingsModel set = new SettingsModel();
        SwingUI sui = mock(SwingUI.class);

        cont.setView(sui);
        cont.setModel(set);
        sui.init();

        cont.actionPerformed(new ActionEvent(this, 0, "Training"));
        assertEquals(GameMode.TRAINING, set.getMode());
        cont.actionPerformed(new ActionEvent(this, 0, "beatrice1"));
        cont.actionPerformed(new ActionEvent(this, 0, "human1"));
        cont.actionPerformed(new ActionEvent(this, 0, "Ready P1"));
        assertEquals(BrawlCharacter.BEATRICE, set.getCharacter());
        assertTrue(set.isHuman());
        assertEquals(PlayerID.PLAYER2, set.getCurrentPlayerId());
        cont.actionPerformed(new ActionEvent(this, 0, "wilbur1"));
        cont.actionPerformed(new ActionEvent(this, 0, "human1"));
        cont.actionPerformed(new ActionEvent(this, 0, "Ready P2"));
        assertEquals(BrawlCharacter.WILBUR, set.getCharacter());
        assertTrue(set.isHuman());

        Field mModel;
        MatchModel match = set.startGame();
        try
        {
            mModel = GameController.class.getDeclaredField("matchModel");
            mModel.setAccessible(true);
            mModel.set(cont, match);

        }
        catch (NoSuchFieldException ex) {}
        catch (IllegalAccessException ex) {}

        // draw card
        match.getPlayer(PlayerID.PLAYER1).getDeck().generateDeck("RHi RHi");
        cont.keyReleased(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_Z));
        assertEquals(CardType.HIT, match.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType());
        // play card on player 1 frame top right stack
        cont.keyReleased(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_E));
        assertFalse(match.getColumns().get(1).getP2Stack().isEmpty());
    }

    // test Tournament
    public void testKeyReleasedInGame2()
    {
        GameController cont = new GameController();
        SettingsModel set = new SettingsModel();
        SwingUI cui = mock(SwingUI.class);

        cont.setView(cui);
        cont.setModel(set);

        cui.init();
        
        cont.actionPerformed(new ActionEvent(this, 0, "Tournament"));
        cont.actionPerformed(new ActionEvent(this, 0, "beatrice1"));
        cont.actionPerformed(new ActionEvent(this, 0, "human1"));
        cont.actionPerformed(new ActionEvent(this, 0, "Ready P1"));
        assertEquals(BrawlCharacter.BEATRICE, set.getCharacter());
        assertTrue(set.isHuman());
        assertEquals(PlayerID.PLAYER2, set.getCurrentPlayerId());
        cont.actionPerformed(new ActionEvent(this, 0, "wilbur1"));
        cont.actionPerformed(new ActionEvent(this, 0, "computer easy"));
        cont.actionPerformed(new ActionEvent(this, 0, "Ready P2"));
        assertEquals(BrawlCharacter.WILBUR, set.getCharacter());
        assertFalse(set.isHuman());

        Field mModel;
        MatchModel match = set.startGame();
        try
        {
            mModel = GameController.class.getDeclaredField("matchModel");
            mModel.setAccessible(true);
            mModel.set(cont, match);
        }
        catch (NoSuchFieldException ex) {}
        catch (IllegalAccessException ex) {}
        // how to check that match isn't null...

        // draw card
        match.getPlayer(PlayerID.PLAYER1).getDeck().generateDeck("RHi RHi BHi");
        cont.keyReleased(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_Z));

        // play card on top right stack
        cont.keyReleased(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_E));
        assertFalse(match.getColumns().get(1).getP2Stack().isEmpty());
        // draw card
        cont.keyReleased(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_Z));
        // check that a card was drawn?
        // play card on top left stack
        cont.keyReleased(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_Q));
        // test tournament mode behavior - stack should not be empty
        assertFalse(match.getColumns().get(0).getP2Stack().isEmpty());
    }

    // test key bindings
    public void testKeyReleasedInControls()
    {
        GameController cont = new GameController();
        SettingsModel set = new SettingsModel();
        SwingUI cui = mock(SwingUI.class);
        cui.init();

        cont.setView(cui);
        cont.setModel(set);
        cui.init();

        cont.actionPerformed(new ActionEvent(this, 0, "Options"));
        cont.actionPerformed(new ActionEvent(this, 0, "Controls"));

        // change draw key to 'c' key
        Move mDraw = new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, -1);
        cont.actionPerformed(new ActionEvent(this, 0, mDraw.toString()));
        cont.keyPressed(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_C));
        
        Move mTL = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        cont.actionPerformed(new ActionEvent(this, 0, mTL.toString()));
        // change move to 'x' key
        cont.keyReleased(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_C));

        Map<Integer, Move> map = KeyMapper.getKeys(PlayerID.PLAYER1);
        assertTrue(map.containsKey(KeyEvent.VK_C));
        assertEquals(mTL.toString(), map.get(KeyEvent.VK_C).toString());

        Move mBL = new Move(PlayerID.PLAYER2, PlayerID.PLAYER1, 2);
        cont.actionPerformed(new ActionEvent(this, 0, mBL.toString()));
        // change move to '/'
        cont.keyReleased(new KeyEvent(new JButton(), 1, 0, 0, KeyEvent.VK_SLASH));

        map = KeyMapper.getKeys(PlayerID.PLAYER2);
        assertTrue(map.containsKey(KeyEvent.VK_SLASH));
        assertEquals(mBL.toString(), map.get(KeyEvent.VK_SLASH).toString());
    }
}
