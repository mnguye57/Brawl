package brawl.ui;

import brawl.GameController;
import brawl.MatchModel;
import brawl.SettingsModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.*;
import brawl.model.*;
import brawl.model.enums.*;

/**
 * The SwingUI class represents a View class for the Swing-based interface.
 *
 * @author FBRD-Paul Doyle
 * @version 1.0
 */
public class SwingUI extends JFrame implements GameView
{

    /**
     * Reference to the controller object that this view communicates with
     */
    private GameController ctrl;

    /**
     * Reference to the MatchModel object that this view extracts data from
     */
    private MatchModel matchModel;

    /**
     * Reference to the SettingsModel object that this view extracts settings
     * data from.
     */
    private SettingsModel settingsModel;

    /**
     * Timer for the countdown screen.
     */
    private Timer timer;

    /**
     * Reference to the different screens.
     */
    private JPanel screens = new JPanel(new CardLayout());
    private IntroScreen introScreen;
    private MainMenuScreen mainMenuScreen;
    private PlayScreen playScreen;
    private JPanel countdownScreen = new JPanel();
    private DeckSetUpScreen deckSelectionScreen;
    private DeckSetUpScreen deckSelectionScreen2;
    private JPanel pauseScreen = new JPanel();
    private QuitScreen quitScreen;
    private TutorialScreen tutorialScreen;
    private AboutScreen aboutScreen;
    private PlayAgainScreen playAgainScreen;
    private ControlScreen controlScreen;
    private final static int FRAME_W = 1280;
    private final static int FRAME_H = 700;

    /**
     * Initialize all the different screens for the Brawl game
     */
    public void init()
    {
        pauseScreen.add(new JLabel("Pause"));

        introSetup();
        mainMenuSetup();
        optionsScreenSetup();
        tutorialScreenSetup();
        quitScreenSetup();
        deckSetUpscreenP1();
        deckSetUpscreenP2();
        aboutSetUp();
        playAgainSetUpScreen();
        boardSetUp();

        /* Add all the different screens to screens */
        screens.add(introScreen, "Intro Screen");
        screens.add(mainMenuScreen, "Main Menu");
        screens.add(deckSelectionScreen, "Deck Selection Player 1");
        screens.add(deckSelectionScreen2, "Deck Selection Player 2");
        screens.add(countdownScreen, "Countdown");
        screens.add(controlScreen, "Options");
        screens.add(playScreen, "Play");
        screens.add(aboutScreen, "About");
        screens.add(pauseScreen, "Pause");
        screens.add(tutorialScreen, "Tutorial");
        screens.add(quitScreen, "Quit");
        screens.add(playAgainScreen, "Play Again");

        this.add(screens);
        this.addWindowListener(new CloseListener());
        this.setSize(FRAME_W, FRAME_H);
        this.setResizable(false);
        setVisible(true);
    }

    /**
     * Set the controller for the UI
     * @param ctrlr the controller for the UI
     */
    public void setController(GameController ctrlr)
    {
        this.ctrl = ctrlr;
    }

    /**
     * Set the MatchModel for the UI
     * @param matchModel the MatchModel for the UI
     */
    public void setMatchModel(MatchModel matchModel)
    {
        this.matchModel = matchModel;
        this.matchModel.addObserver(this);
        playScreen.setMatchModel(this.matchModel, this.matchModel.getGameMode());
    }

    /**
     * Set the SettingsModel for the UI
     * @param settingsModel the SettingsModel for the UI
     */
    public void setSettingsModel(SettingsModel settingsModel)
    {
        this.settingsModel = settingsModel;
    }

    /**
     * Displays the option menu.  The option menu contains player options,
     * such as AI difficulty, animation, and quit game selections.
     *
     * @pre user is viewing either the Main Menu or the gameplay screen
     * @post user is viewing the Options menu
     */
    public void displayOptionsScreen()
    {
        // CALL updateCards with "Options"
        updateCards("Options");
    }

    /**
     * Displays the Intro Screen with a button to proceed to the main menu
     *
     * @pre application is starting
     * @post user will proceed to the main menu
     */
    public void displayIntroScreen()
    {
        // CALL updateCards with "Main Menu"
        updateCards("Intro Screen");
    }

    /**
     * Displays the main menu.  The main menu contains game mode options:
     * Tournament, Turn-Based, Tutorial.
     *
     * @pre user is viewing the Options menu, Deck Viewer, or gameplay screen
     * @post user is returned to the Main Menu
     */
    public void displayMainMenuScreen()
    {
        // CALL updateCards with "Main Menu"
        updateCards("Main Menu");
    }

    /**
     * Displays the tutorial.
     *
     * @pre user is viewing the Main Menu
     * @post user is in Tutorial mode
     */
    public void displayTutorialScreen()
    {
        // CALL updateCards with "Tutorial"
        updateCards("Tutorial");
        tutorialScreen.requestFocus();
    }

    /**
     * Sets the next page in the tutorial screen.
     * @param keyCode determines if the user wants to go forward or backward
     *        a page.
     */
    public void setNextTutorialPage(int keyCode)
    {
        tutorialScreen.setNextPage(keyCode);
    }

    /**
     * Displays the training screen.
     *
     * @pre user is viewing the Main Menu
     * @post user is in Training mode
     */
    public void displayTrainingScreen()
    {
        // CALL displayDeckSelectionP1()
        displayDeckSelectionP1();
    }

    /**
     * Displays the deck selection screen for player one.
     *
     * @pre user is viewing the Main Menu
     * @post user is viewing the Deck Selection screen for player one.
     */
    public void displayDeckSelectionP1()
    {
        // CALL updateCards with "Deck Selection"
        updateCards("Deck Selection Player 1");
        deckSelectionScreen.revalidate();
        deckSelectionScreen.repaint();
    }

    /**
     * Displays the deck selection screen for player two.
     *
     * @pre user is viewing the Main Menu
     * @post user is viewing the Deck Selection screen for player two
     */
    public void displayDeckSelectionP2()
    {
        // CALL updateCards with "Deck Selection"
        
        updateCards("Deck Selection Player 2");
        deckSelectionScreen2.revalidate();
        deckSelectionScreen2.repaint();
    }

    /**
     * Displays the countdown to start the game play.
     *
     * @pre the user is viewing the Deck Selection screen for player two
     * @post user is viewing the countdown screen.
     */
    public void displayCountdownScreen()
    {
        countdown321();
        updateCards("Countdown");
    }

    /**
     * Displays the play screen.
     *
     * @pre user is viewing the countdown screen
     * @post user is viewing the play screen
     */
    public void displayPlayScreen()
    {
        // CALL updateCards with "Play"
        playScreen.resetBoard();
        playScreen.setMatchModel(this.matchModel, this.matchModel.getGameMode());
        updateCards("Play");
        playScreen.requestFocus();
    }

    /**
     * Displays the quit screen.
     *
     * @pre user is viewing the main menu or the play screen
     * @post user is viewing the quit screen
     */
    public void displayQuitScreen()
    {
        // CALL updateCards with "Quit"
        updateCards("Quit");
    }

    /**
     * Displays the play again screen.
     *
     * @pre user is viewing the play screen
     * @post user is viewing the play again screen
     */
    public void displayPlayAgainScreen()
    {
        playAgainScreen.setMatchModel(this.matchModel);
        playAgainScreen.setWinner();
        // CALL updateCards with "Play Again"
        updateCards("Play Again");
    }

    /**
     * Displays the about screen.
     *
     * @pre user is viewing the main menu screen
     * @post user is viewing the about screen
     */
    public void displayAboutScreen()
    {
        updateCards("About");
    }

    /**
     * Updates the deck information panel on the deck selection screen.
     * @param player determines which selection screen to update
     * @param bc determines which deck's information to use
     */
    public void updateDeckInfo(PlayerID player, BrawlCharacter bc)
    {
        if (player == PlayerID.PLAYER1)
        {
            deckSelectionScreen.updateInfo(bc);
        }
        else if (player == PlayerID.PLAYER2)
        {
            deckSelectionScreen2.updateInfo(bc);
        }
    }
    
    /**
     * Switches to another screen
     *
     * @param card the string representation of the screen to switch to
     */
    private void updateCards(String card)
    {
        CardLayout c1 = (CardLayout) (screens.getLayout());
        c1.show(screens, card);

        screens.updateUI();
        repaint();
    }

    /**
     * Initializes the countdown screen.
     */
    public void countdown321()
    {
        final int nSeconds = 3, interval = 1000;
        final JLabel timeLabel = new JLabel(" ", JLabel.CENTER);
        countdownScreen.add(timeLabel);
        countdownScreen.setLayout(new BoxLayout(countdownScreen,
                BoxLayout.PAGE_AXIS));
        timer = new Timer(interval, new CountdownListener(timeLabel, nSeconds));
        timer.start();
    }

    /**
     * Initializes the introduction screen.
     */
    private void introSetup()
    {
        introScreen = new IntroScreen(ctrl);
    }

    /**
     * Initializes the main menu.
     */
    private void mainMenuSetup()
    {
        mainMenuScreen = new MainMenuScreen(ctrl);
        mainMenuScreen.setLayout(new BoxLayout(mainMenuScreen,
                BoxLayout.PAGE_AXIS));
    }

    /**
     * Initializes the quit screen.
     */
    private void quitScreenSetup()
    {
        quitScreen = new QuitScreen(ctrl);
        quitScreen.setLayout(new BoxLayout(quitScreen, BoxLayout.Y_AXIS));
    }

    /**
     * Initializes the options screen.
     */
    private void optionsScreenSetup()
    {
        controlScreen = new ControlScreen(ctrl);
        controlScreen.setLayout(new BoxLayout(controlScreen, BoxLayout.Y_AXIS));
    }

    /**
     * Initializes the tutorial screen.
     */
    private void tutorialScreenSetup()
    {
        tutorialScreen = new TutorialScreen(ctrl);
    }

    /**
     * Initializes the deck selection screen for player one.
     */
    private void deckSetUpscreenP1()
    {
        deckSelectionScreen = new DeckSetUpScreen(ctrl, PlayerID.PLAYER1);
        deckSelectionScreen.setLayout(new BoxLayout(deckSelectionScreen,
                BoxLayout.PAGE_AXIS));
    }

    /**
     * Initializes the deck selection screen for player two.
     */
    private void deckSetUpscreenP2()
    {
        deckSelectionScreen2 = new DeckSetUpScreen(ctrl, PlayerID.PLAYER2);
        deckSelectionScreen2.setLayout(new BoxLayout(deckSelectionScreen2,
                BoxLayout.PAGE_AXIS));
    }

    /**
     * Initializes the deck selection screen.
     */
    private void playAgainSetUpScreen()
    {
        playAgainScreen = new PlayAgainScreen(ctrl);
    }

    /**
     * Initializes the play screen.
     */
    private void boardSetUp()
    {
        playScreen = new PlayScreen(this.ctrl);
    }

    private void aboutSetUp()
    {
        aboutScreen = new AboutScreen(this.ctrl);
    }

    /**
     * Pauses the game by creating a pop-up.
     */
    public void paused()
    {
        String[] choices =
        {
            "Resume", "Quit"
        };
        int result;

        result = JOptionPane.showOptionDialog(this, "The game is now paused. " +
                "You can resume play or quit this game.", "Paused",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                choices, this);

        if (result == 1)
        {
            ctrl.actionPerformed(new ActionEvent(this, 0, "Main Menu"));
        }/*user clicks resume or exits the pop up window*/
        else if ( (result == -1) || (result == 0) )
        {
            ctrl.actionPerformed(new ActionEvent(this, 0, "Unpause"));
        }
    }

    /**
     * Updates the controls screen when the key bindings change
     */
    public void updateControlsScreen()
    {
        controlScreen.updateControlsScreen();
    }
    /**
     * Sets up the system for testing.
     */
    public void sysTestSetup()
    {
    }

    /**
     * Updates the UI when a change is detected in the observable object
     * @param o the observed object
     * @param arg the change made
     */
    public void update(Observable o, Object arg)
    {
        // CALL one of the above functions depending on state of model
        if (matchModel.isGameOver())
        {
            displayPlayAgainScreen();
        }
        else if (arg instanceof Move)
        {
            Move newMove = (Move) arg;
            playScreen.makeMove(newMove);
        }
        else if (arg instanceof PlayerID)
        {
            playScreen.setTurn((PlayerID)arg);
        }
    }

    /**
     * Blinks a panel to give feedback that an invalid key was pressed
     */
    public void blinkInvalid()
    {
        playScreen.blinkInvalid(matchModel.getTurn());
    }

    /**
     * A CountdownListener represents a listener for the countdown screen
     * displayed before a game is started.
     */
    private class CountdownListener implements ActionListener
    {
        private JLabel cl;
        private int nSeconds;
        private final static int C1FONT1 = 500, C2FONT2 = 275;

        public CountdownListener(JLabel timeLabel, int nSec)
        {
            cl = timeLabel;
            cl.setFont(new Font("Helvetica", Font.BOLD, C1FONT1));
            cl.setAlignmentX(CENTER_ALIGNMENT);
            nSeconds = nSec;
        }

        @Override
        public void actionPerformed(ActionEvent event)
        {
            //int nSeconds = 3;
            if (nSeconds > 0)
            {
                cl.setText(String.valueOf(nSeconds--));
                cl.setFont(new Font("Helvetica", Font.BOLD, C1FONT1));
            }
            else if (nSeconds == 0)
            {
                cl.setText("Brawl!");
                cl.setFont(new Font("Helvectica", Font.BOLD, C2FONT2));
                nSeconds--;
            }
            else if (nSeconds == -1)
            {
                timer.stop();
                countdownScreen.removeAll();
                displayPlayScreen();
            }
        }
    }

    /**
     * A CloseListener represents a listener for an event to
     * close the window.
     */
    private static class CloseListener extends WindowAdapter
    {
        /**
         * Closes the window and exists the system
         * @param e the event triggering the closing of the window
         */
        @Override
        public void windowClosing(WindowEvent e)
        {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }
}
