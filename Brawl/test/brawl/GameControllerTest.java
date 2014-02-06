package brawl;

import brawl.model.Human;
import brawl.model.enums.BrawlCharacter;
import java.awt.event.ActionEvent;
import brawl.model.enums.PlayerID;
import brawl.model.Move;
import brawl.model.enums.GameMode;
import brawl.utils.KeyMapper;
import java.lang.reflect.Field;
import java.awt.Button;
import brawl.ui.SwingUI;
import java.awt.event.KeyEvent;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;


/**
 * class to test methods of GameController
 * @author Zander
 */
public class GameControllerTest extends TestCase {

    public GameControllerTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //Test of pause method, of class GameController.
    public void testPause()
    {
       GameController controller = new GameController();
       SwingUI view = mock(SwingUI.class);
       SettingsModel set = mock(SettingsModel.class);
       Human p1 = mock(Human.class);
       Human p2 = mock(Human.class);
       set.setCharacter(BrawlCharacter.MILTON);
       set.setIsHuman(true);
       set.setPlayer();
       set.setCharacter(BrawlCharacter.WILBUR);
       set.setIsHuman(true);
       set.setPlayer();
       controller.setView(view);
       MatchModel match = mock(MatchModel.class);

       Field mModelf = null;
       Field mModelg = null;

       try
       {
           mModelf = GameController.class.getDeclaredField("matchModel");
           mModelg = SwingUI.class.getDeclaredField("matchModel");
           mModelf.setAccessible(true);
           mModelg.setAccessible(true);
       }   catch (NoSuchFieldException ex) {}
       try
       {
           mModelf.set(controller, match);
           mModelg.set(view, match);
       }   catch (IllegalAccessException ex) {}

       match = set.startGame();
       controller.pause();
       verify(view, times(1)).paused();
       
    }

    //Test of keyReleased method, of class GameController.
    public void testKeyReleased()
    {
        GameController controller = new GameController();
        SwingUI view = mock(SwingUI.class);

        SettingsModel sModel = mock(SettingsModel.class);
        KeyEvent e = null;
        ActionEvent ae = null;
        Move drawMove = mock(Move.class);
        Move playMove = mock(Move.class);
        KeyMapper km = mock(KeyMapper.class);
        MatchModel mModel = mock(MatchModel.class);

        controller.setView(view);
        controller.setModel(sModel);

        e = new KeyEvent(new Button(), 2, 0, 0, KeyEvent.VK_LEFT);
        when(sModel.getMode()).thenReturn(GameMode.TUTORIAL);
        controller.keyReleased(e);
        verify(view, times(1)).setNextTutorialPage(KeyEvent.VK_LEFT);

        e = new KeyEvent(new Button(), 2, 0, 0, KeyEvent.VK_RIGHT);
        when(sModel.getMode()).thenReturn(GameMode.TUTORIAL);
        controller.keyReleased(e);
        verify(view, times(1)).setNextTutorialPage(KeyEvent.VK_RIGHT);

        //else case testing
        Field mModelf = null;
        Field bindKeyf = null;
        Field drawf = null;
        try
        {

            mModelf = GameController.class.getDeclaredField("matchModel");
            bindKeyf = GameController.class.getDeclaredField("bindKey");
            drawf = Move.class.getDeclaredField("draw");
       }    catch (NoSuchFieldException ex) {}
       try
       {
           mModelf.set(controller, mModel);
           bindKeyf.set(controller, false);
           drawf.set(drawMove, true);

       }   catch (IllegalAccessException ex) {}


       //Ready P2, used to set matchModel
       ae = new ActionEvent(new Button(), 12, "Ready P2");
       when(sModel.startGame()).thenReturn(mModel);
       controller.actionPerformed(ae);

       //draw
       when(mModel.getMove(KeyEvent.VK_Z)).thenReturn(drawMove);
       when(drawMove.isDraw()).thenReturn(true);
       when(drawMove.getSource()).thenReturn(PlayerID.PLAYER1);
       
       e = new KeyEvent(new Button(), 2, 0, 0, KeyEvent.VK_Z);
       controller.keyReleased(e);
       verify(mModel, times(1)).getMove(KeyEvent.VK_Z);
       verify(mModel, times(1)).drawCard(PlayerID.PLAYER1);

       //play
       when(mModel.getMove(KeyEvent.VK_S)).thenReturn(playMove);
       when(playMove.isDraw()).thenReturn(false);
       when(playMove.getSource()).thenReturn(PlayerID.PLAYER1);
       when(playMove.getSide()).thenReturn(PlayerID.PLAYER1);
       when(playMove.getColumn()).thenReturn(2);

       e = new KeyEvent(new Button(), 2, 0, 0, KeyEvent.VK_S);
       controller.keyReleased(e);
       verify(mModel, times(1)).getMove(KeyEvent.VK_S);
       verify(mModel, times(1)).playDiscard(
               PlayerID.PLAYER1, PlayerID.PLAYER1, 2);

       e = new KeyEvent(new Button(), 1, 0, 0, KeyEvent.VK_ESCAPE);
       controller.keyReleased(e);
       verify(view, times(1)).paused();

    }

    //Test of actionPerformed method, of class GameController.
    public void testActionPerformed()
    {
        GameController controller = new GameController();
        ActionEvent e;
        SwingUI view = mock(SwingUI.class);
        SettingsModel sModel = mock(SettingsModel.class);
        MatchModel mModel = mock(MatchModel.class);

        controller.setView(view);
        controller.setModel(sModel);

        //Proceed
        e = new ActionEvent(new Button(), 1, "Proceed");
        controller.actionPerformed(e);
        verify(view, times(1)).displayMainMenuScreen();

        //Training
        e = new ActionEvent(new Button(), 2, "Training");
        controller.actionPerformed(e);
        verify(view, times(1)).displayDeckSelectionP1();
        verify(sModel, times(1)).setMode(GameMode.TRAINING);

        //Tournament
        e = new ActionEvent(new Button(), 3, "Tournament");
        controller.actionPerformed(e);
        verify(view, times(2)).displayDeckSelectionP1();
        verify(sModel, times(1)).setMode(GameMode.TOURNAMENT);

        //Tutorial
        e = new ActionEvent(new Button(), 4, "Tutorial");
        controller.actionPerformed(e);
        verify(view, times(1)).displayTutorialScreen();
        verify(sModel, times(1)).setMode(GameMode.TUTORIAL);

        //About
        e = new ActionEvent(new Button(), 31, "About");
        controller.actionPerformed(e);
        verify(view, times(1)).displayAboutScreen();

        //Easy
        e = new ActionEvent(new Button(), 8, "computer easy");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setDifficulty(2000);

        //Medium
        e = new ActionEvent(new Button(), 9, "computer medium");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setDifficulty(1000);

        //Hard
        e = new ActionEvent(new Button(), 10, "computer hard");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setDifficulty(500);

        //Back to Player 1
        e = new ActionEvent(new Button(), 11, "Back to Player 1");
        controller.actionPerformed(e);
        verify(sModel, times(1)).changeCurrentPlayerId();
        verify(view, times(3)).displayDeckSelectionP1();

        //Ready P2
        e = new ActionEvent(new Button(), 12, "Ready P2");
        when(sModel.startGame()).thenReturn(mModel);
        controller.actionPerformed(e);
        verify(sModel, times(1)).setPlayer();
        verify(sModel, times(1)).startGame();
        verify(view, times(1)).setMatchModel(mModel);
        verify(mModel, times(1)).addObserver(view);
        verify(view, times(1)).displayCountdownScreen();
        

        //Quit
        e = new ActionEvent(new Button(), 13, "Quit");
        controller.actionPerformed(e);
        verify(view, times(1)).displayQuitScreen();
        //quitPAS
        e = new ActionEvent(new Button(), 14, "quitPAS");
        controller.actionPerformed(e);
        verify(view, times(2)).displayQuitScreen();

        //No
        e = new ActionEvent(new Button(), 15, "No");
        controller.actionPerformed(e);
        verify(view, times(2)).displayMainMenuScreen();

        //Main Menu
        e = new ActionEvent(new Button(), 16, "Main Menu");
        controller.actionPerformed(e);
        verify(view, times(3)).displayMainMenuScreen();

        //mainMenuPAS
        e = new ActionEvent(new Button(), 17, "mainMenuPAS");
        controller.actionPerformed(e);
        verify(view, times(4)).displayMainMenuScreen();

        //Ready P1
        e = new ActionEvent(new Button(), 19, "Ready P1");
        controller.actionPerformed(e);
        verify(sModel, times(2)).setPlayer();
        verify(view, times(1)).displayDeckSelectionP2();
        verify(sModel, times(3)).changeCurrentPlayerId();

        //humphrey1
        e = new ActionEvent(new Button(), 20, "humphrey1");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setCharacter(BrawlCharacter.HUMPHREY);

        //beatrice1
        e = new ActionEvent(new Button(), 21, "beatrice1");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setCharacter(BrawlCharacter.BEATRICE);
        //milton1
        e = new ActionEvent(new Button(), 22, "milton1");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setCharacter(BrawlCharacter.MILTON);
        //wilbur1
        e = new ActionEvent(new Button(), 23, "wilbur1");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setCharacter(BrawlCharacter.WILBUR);
        //gertrude1
        e = new ActionEvent(new Button(), 24, "gertrude1");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setCharacter(BrawlCharacter.GERTRUDE);
        //esther1
        e = new ActionEvent(new Button(), 25, "esther1");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setCharacter(BrawlCharacter.ESTHER);
        //ai1
        e = new ActionEvent(new Button(), 26, "computer easy");
        controller.actionPerformed(e);
        verify(sModel, times(4)).setIsHuman(false);
        //human1
        e = new ActionEvent(new Button(), 27, "human1");
        controller.actionPerformed(e);
        verify(sModel, times(1)).setIsHuman(true);
        //playAgainPAS
        e = new ActionEvent(new Button(), 28, "playAgainPAS");
        controller.actionPerformed(e);
        verify(view, times(4)).displayDeckSelectionP1();

        //sysTestModeSetup
        e = new ActionEvent(new Button(), 29, "sysTestMode");
        when(sModel.startGame()).thenReturn(mModel);


        controller.actionPerformed(e);
        
        verify(sModel, times(2)).setCharacter(BrawlCharacter.HUMPHREY);
        verify(sModel, times(3)).setIsHuman(true);
        verify(sModel, times(2)).setCharacter(BrawlCharacter.MILTON);
        verify(sModel, times(4)).setPlayer();
        verify(sModel, times(4)).changeCurrentPlayerId();

        verify(sModel, times(2)).setMode(GameMode.TOURNAMENT);



        verify(sModel, times(2)).startGame();
        verify(view, times(2)).setMatchModel(mModel);
        verify(mModel, times(2)).addObserver(view);
        verify(view, times(1)).sysTestSetup();

        //Save Bindings
        e = new ActionEvent(new Button(), 32, "Save Bindings");
        controller.actionPerformed(e);
        verify(view, times(5)).displayMainMenuScreen();
        //Reset Bindings
        e = new ActionEvent(new Button(), 33, "Reset Bindings");
        controller.actionPerformed(e);
        verify(view, times(1)).updateControlsScreen();
        //Default
        e = new ActionEvent(new Button(), 34, "Default");
        controller.actionPerformed(e);
        verify(view, times(2)).updateControlsScreen();

        //key bind move case
        Move move = Move.parseMove("p1p1c2");
        e = new ActionEvent( new Button(), 30, "p1p1c2");
        Field bindKeyf = null;
        Field movef = null;
        Move resultMove = null;
        boolean result = false;
        try
        {
            bindKeyf = GameController.class.getDeclaredField("bindKey");
            movef = GameController.class.getDeclaredField("move");
        } catch (NoSuchFieldException ex) {}

        controller.actionPerformed(e);

        try
        {
            bindKeyf.get(result);
            assertTrue(result);
            movef.get(resultMove);
            assertEquals(resultMove, move);
        } catch (IllegalAccessException ex) {}
     
    }

}
