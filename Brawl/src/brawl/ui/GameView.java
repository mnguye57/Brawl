package brawl.ui;

import brawl.GameController;
import brawl.MatchModel;
import brawl.SettingsModel;
import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.PlayerID;
import java.util.Observable;


/**
 * A GameView interface represents a user interface layout for Brawl.
 * A GameView interface contains two public methods and another method inherited
 * from java.util.Observer.
 * @author FBRD-Ebele Okonkwo
 * @version 1.0
 */
public interface GameView extends java.util.Observer
{
    /**
    * Assigns a controller to each user interface that uses this interface.
    *
    * @param crtl a controller object to interface with
    * @pre crtl must not be null.
    * @post A controller is assigned to a user interface, allowing it to
    * receive events from the UI and perform actions accordingly.
    */
    public void setController(GameController crtl);

    /**
    * Assigns the MatchModel to each user interface that uses this interface.
    *
    * @param model a model object to extract match data from
    * @pre model must not be null.
    * @post model is assigned to a UI class, allowing the UI to
    * receive data from the model.
    */

    public void setMatchModel(MatchModel model);

    /**
    * Assigns the SettingsModel to each user interface that uses this interface.
    *
    * @param model a model object to extract settings data from
    * @pre model must not be null.
    * @post model is assigned to a UI class, allowing the UI to
    * receive data from the model.
    */
    public void setSettingsModel(SettingsModel model);

    /**
    * Updates the data displayed on the user interface whenever the game state
    * changes.
    *
    * @param o the Observable object
    * @param arg0 argument that is passed to the notifyObservers method
    * @pre Data in the game must have changed.
    * @post The data that is presented in the view is the same as that in the
    * game.
    */
    public void update(Observable o, Object arg0);

    /**
     * Displays the Main Menu Screen
     */
    public void displayMainMenuScreen();

    /**
     * Displays the deck selection screen for player 1
     */
    public void displayDeckSelectionP1();

    /**
     * Displays the deck selection screen for player 2
     */
    public void displayDeckSelectionP2();

    /**
     * Displays the options screen
     */
    public void displayOptionsScreen();

    /**
     * Displays the about screen
     */
    public void displayAboutScreen();
    
    /**
     * Displays the countdown screen
     */
    public void displayCountdownScreen();

    /**
     * Displays the quit screen
     */
    public void displayQuitScreen();

    /**
     * Displays the game board
     */
    public void displayPlayScreen();
    /**
     * Displays the "Play Again?" screen
     */
    public void displayPlayAgainScreen();

    /**
     * Initializes all of the SwingUI components
     */
    public void init();

    /**
     * Sets up the user interface for system testing
     */
    public void sysTestSetup();

    /**
     * Pauses the game
     */
    public void paused();

    /**
     * Displays the tutorial screen
     */
    public void displayTutorialScreen();

    /**
     * Updates the deck info panel on the deck selection screens
     * @param player PlayerID to get deck info
     * @param bc Character chosen by current player
     */
    public void updateDeckInfo(PlayerID player, BrawlCharacter bc);

    /**
     * Updates the controls screen when the key bindings change
     */
    public void updateControlsScreen();

     /**
     * Sets the next page in the tutorial screen.
     * @param keyCode determines if the user wants to go forward or backward
     *        a page.
     */
    public void setNextTutorialPage(int keyCode);
    /**
     * Blinks a panel to give feedback that an invalid key was pressed
     */
    public void blinkInvalid();
}