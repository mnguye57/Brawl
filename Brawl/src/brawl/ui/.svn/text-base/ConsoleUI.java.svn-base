package brawl.ui;

import brawl.GameController;
import brawl.MatchModel;
import brawl.SettingsModel;
import brawl.model.*;
import brawl.model.enums.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * A ConsoleUI class represents the Console user interface that implements the
 * GameView interface for testing purposes.
 * A ConsoleUI class has three public methods inherited from the
 * GameView interface.
 * @author FBRD-Ebele Okonkwo, implementor-Megan Arnez
 * @version 1.0
 */
public class ConsoleUI implements GameView
{
    /**
    * Reference to the GameController object that a GameView communicates with
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

    /*
     * Reference to a Scanner object that will be used to read in commands typed
     * in by the user.
     */
    private Scanner scanner;

    /**
     * Flag for systemTest mode.
     */
    private boolean sysTest;

    /**
     * Flag for keyBinding mode.
     */
    private boolean keyBinding;

    /**
    * Keyboard button mappings for Player 1
    */
    private HashMap<Integer, Move> p1KeyMap;

    /**
    * Keyboard button mappings for Player 2
    */
    private HashMap<Integer, Move> p2KeyMap;

    private static final int THREE_COLUMNS = 3;
    private static final int BEATRICE_DECK = 3;
    private static final int WILBUR_DECK = 4;
    private static final int GERTRUDE_DECK = 5;
    private static final int ESTHER_DECK = 6;

    /**
    * Constructs a new ConsoleUI.
    */
    public ConsoleUI()
    {
        sysTest = false;
        keyBinding = false;
    }

    /**
     *  Initiates Console UI set up.
     */
    public void init()
    {
        displayMainMenuScreen();
    }

    /**
     * Displays the main menu screen.
     */
    public void displayMainMenuScreen()
    {
        String command = "";
        System.out.println("BRAWL: It's Garden Time!");
        System.out.println("Please enter 'key' or 'sys'.");
        scanner = new Scanner(System.in);

        command = scanner.next();
        if (command.equals("key"))
        {
            System.out.println("You will be playing Training Mode");
            keyBinding = true;
            getModeAndPlayerTypes();
        }
        else if (command.equals("sys"))
        {
            sysTest = true;
            System.out.println("You will be playing Tournament Mode");
            ActionEvent action =
                new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "sysTestMode");
            ctrl.actionPerformed(action);
        }
    }

    /**
     * Calls the method that will retrieve the player's settings (human or ai)
     * and will let the controller know that the game will be played
     * in training mode.
     */
    public void getModeAndPlayerTypes()
    {
        choosePlayerType(1);
        choosePlayerType(2);
        ActionEvent action =
            new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Training");
        ctrl.actionPerformed(action);
    }

     /**
     * Retrieves the settings that the user specifies for choosing
     * what type of player they would like to be for Player 1 and passes that
     * information to the controller.
     *
     * @param player Which player is choosing their type.
     */
    public void choosePlayerType(int player)
    {
        String pType = "";
        int pchose = 0;
        System.out.println("Player " + player + ": human or ai?");
        System.out.print("Please enter 'human' or 'ai': ");
        while(pchose == 0)
        {
            pType = scanner.next();
            if (pType.equals("human"))
            {
                ActionEvent action =
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "human1");
                ctrl.actionPerformed(action);
                pchose = 1;
                System.out.println("Settings model isHuman(T/F): "
                    + settingsModel.isHuman());
            }
            else if (pType.equals("ai"))
            {
                ActionEvent action =
                    new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                        "computer easy");
                ctrl.actionPerformed(action);
                pchose = 1;
                System.out.println("Settings model isHuman(T/F): "
                    + settingsModel.isHuman());
            }
            else
            {
                System.out.println("Invalid command. Please enter either 'human'"
                    + " or 'ai' for Player " + player);
            }
        }
        System.out.println("Player "+ player + " chose: " + pType);
        System.out.println();
    }

    /**
     * Displays the deck selection options for player 1
     * and allows the user to choose their player and
     * passes that information to the controller.
     */
    public void displayDeckSelectionP1()
    {
        int deckNumber = 0;
        System.out.println("Player 1: Please choose a deck.");
        System.out.println("Please choose the character's number.");
        System.out.println("1: Humphrey");
        System.out.println("2: Milton");
        System.out.println("3: Beatrice");
        System.out.println("4: Wilbur");
        System.out.println("5: Gertrude");
        System.out.println("6: Esther\n");

        while (deckNumber < 1 || deckNumber > ESTHER_DECK)
        {
            deckNumber = scanner.nextInt();
            if (deckNumber < 1 || deckNumber > ESTHER_DECK)
            {
                System.out.println("Invalid command. Please choose a deck"
                        + " number between 1 and 6.");
            }
            else
            {
                getCharacter(deckNumber, 1);
            }
        }
        ActionEvent action =
            new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Ready P1");
        ctrl.actionPerformed(action);
    }

    /**
     * Based off of what the user enters the character
     * will be set via the correct action performed call.
     * @param deckNumber the number chosen by the user
     * @param pNum player 1 or player 2
     */
    public void getCharacter(int deckNumber, int pNum)
    {
        ActionEvent action;
        switch (deckNumber)
        {
            case 1:
                action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "humphrey1");
                ctrl.actionPerformed(action);
                break;
            case 2:
                action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "milton1");
                ctrl.actionPerformed(action);
                break;
            case BEATRICE_DECK:
                action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "beatrice1");
                ctrl.actionPerformed(action);
                break;
            case WILBUR_DECK:
                action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "wilbur1");
                ctrl.actionPerformed(action);
                break;
            case GERTRUDE_DECK:
                action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "gertrude1");
                ctrl.actionPerformed(action);
                break;
            case ESTHER_DECK:
                action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "esther1");
                ctrl.actionPerformed(action);
                break;
            default:
                action = new ActionEvent(this,
                     ActionEvent.ACTION_PERFORMED, "humphrey1");
                ctrl.actionPerformed(action);
                break;
        }
    }

    /**
     * Displays the deck selection options for player 1
     * and allows the user to choose their player and
     * passes that information to the controller.
     */
    public void displayDeckSelectionP2()
    {
        int deckNumber = -1;
        int done = 0;
        System.out.println("player 1 chose deck: " +
            settingsModel.getCharacter());
        System.out.println();
        System.out.println("Player 2: Please choose a deck.");
        System.out.println("Please choose the character's number.");
        System.out.println("1: Humphrey");
        System.out.println("2: Milton");
        System.out.println("3: Beatrice");
        System.out.println("4: Wilbur");
        System.out.println("5: Gertrude");
        System.out.println("6: Esther\n");
        System.out.println("Or you can go back to " + "Player 1's Deck Selection Screen"
            + " by pressing '0'");
        deckNumber = scanner.nextInt();
        if (deckNumber == 0)
        {
            ActionEvent action = new ActionEvent(this,
                 ActionEvent.ACTION_PERFORMED, "Back to Player 1");
            ctrl.actionPerformed(action);
        }
        else
        {
            while (done != 1)
            {
                if (deckNumber < 1 || deckNumber > ESTHER_DECK)
                {
                    System.out.println("Invalid command. Please choose a deck"
                            + " number between 1 and 6.");
                    deckNumber = scanner.nextInt();
                }
                else
                {
                    getCharacter(deckNumber, 2);
                    System.out.println("player 2 chose: " +
                        settingsModel.getCharacter());
                    done = 1;
                }
            }
        }
        ActionEvent action =
            new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Ready P2");
        ctrl.actionPerformed(action);
    }

    /**
     * Displays the countdown screen.
     */
    public void displayCountdownScreen()
    {
        System.out.println("\n3-2-1 BRAWL!\n");
        displayPlayScreen();
    }

    /**
     * Displays the game board in its initial state.
     */
    public void displayPlayScreen()
    {
        if (keyBinding)
        {
            printKeyCommands();
        }
        if (sysTest)
        {
            printSysCommands();
        }
        //print whose turn it is
        System.out.println("" + matchModel.getTurn() + "'s turn");
        //print the top cards of the bases
        System.out.println("[--]" + "       " + "[--]" + "       " + "[--]");

        //print the bases on the board
        System.out.println("[" +
            matchModel.getColumns().get(0).getBase().getOwner()
             + "]" + "       " + "[--]" + "       " + "[" +
            matchModel.getColumns().get(1).getBase().getOwner() +
            "]");

        //print the bottom cards of the bases
        System.out.println("[--]" + "       " + "[--]" + "       " + "[--]");

        //print out who is winning each base
        System.out.println("Winning B1: " +
            matchModel.getColumns().get(0).getCurrentWinner() +
            "     " + "Winning B2: " +
            matchModel.getColumns().get(1).getCurrentWinner());
        //print out the discard piles for each player
        System.out.print("[DECK]     " + "[--]" + "     [DECK]     " + "[--]\n\n");
        enterCommands();
    }

    /**
     * Prints all of the valid commands for Player 1 and Player 2
     * in system test mode.
     */
    public void printSysCommands()
    {
        System.out.println("Key Binding Commands:");
        System.out.println("Player1 Keys, Player 2 Keys");
        System.out.println("q, i: Play opponent's side of left Base");
        System.out.println("e, p: Play opponent's side of right base");
        System.out.println("a, k: Play own side of left base");
        System.out.println("d, ;: Play own side of right base");
        System.out.println("z, ,: Draw card\n");
    }

    /**
     * Prints all of the valid commands for Player 1 and Player 2
     * in keyBinding test mode.
     */
    public void printKeyCommands()
    {
        System.out.println("Key Binding Commands:");
        System.out.println("Player1 Keys, Player 2 Keys");
        System.out.println("q, i: Play opponent's side of left Base");
        System.out.println("e, p: Play opponent's side of right base");
        System.out.println("a, k: Play own side of left base");
        System.out.println("d, ;: Play own side of right base");
        System.out.println("z, ,: Draw card");
        System.out.println("x, .: Pass your turn");
    }

    /**
     * Parses commands based on what the user enters.
     */
    public void enterCommands()
    {
        String command = "";
        while(!(matchModel.isGameOver()))
        {
            command = scanner.next();
            if (command.length() == 1)
            {
                parseKeyCommands(command);
            }
            else
            {
                System.out.println("Invalid key binding command.");
            }
        }
        displayPlayAgainScreen();
    }

    /**
     * Prompts the user to enter in the deck that they want created
     * for a specific system test.
     */
    public void sysTestSetup()
    {
        //fix to read in whole string and create proper deck based on input.
        System.out.println("Creating a deck Command");
        System.out.println("Please enter the deck you'd like to create");
        System.out.println(" OR ");
        System.out.println("Creating a random deck:");
        System.out.println("Please enter 'random'");

        sysTestSetupPlayer(PlayerID.PLAYER1);
        sysTestSetupPlayer(PlayerID.PLAYER2);

        displayCountdownScreen();
    }

     /**
     * Generates a new deck and overrides Player's
     * existing deck with the new deck.
     * @param player PlayerID of the player who is choosing their deck type
     */
    public void sysTestSetupPlayer(PlayerID player)
    {
        String deck, newDeck;

        System.out.println(player.toString() + " please choose your deck type.");
        deck = scanner.next();
        newDeck = scanner.nextLine();
        if (newDeck == null)
        {
            if (deck.charAt(0) != 'r')
            {
                //pass string to deck to create the new deck for player 1
                matchModel.getPlayer(player).getDeck().generateDeck(deck);
            }
        }
        else
        {
            deck = deck.concat(newDeck);
            if (deck.charAt(0) != 'r')
            {
                //pass string to deck to create the new deck for player 1
                matchModel.getPlayer(player).getDeck().generateDeck(deck);
            }
        }
    }
    
    /**
     * Parses commands entered in by the user
     * and will continue to do so until the game is over.
     * @param command key pressed by the user
     */
    public void parseKeyCommands(String command)
    {
        JPanel empty = new JPanel();
        if (command.length() == 1)
        {
            char keyChar = command.charAt(0);
            KeyEvent ke = new KeyEvent(empty, 0, 0, 0, getCode(keyChar), keyChar, 0);
            ctrl.keyReleased(ke);
        }
    }

    private int getCode(char keyChar)
    {
        switch(keyChar)
        {
            case 'x':
                System.out.println("Now it's Player 2's turn.");
                return KeyEvent.VK_X;
            case 'z':
                return KeyEvent.VK_Z;
            case 'a':
                return KeyEvent.VK_A;
            case 's':
                return KeyEvent.VK_S;
            case 'd':
                return KeyEvent.VK_D;
            case 'q':
                return KeyEvent.VK_Q;
            case 'w':
                return KeyEvent.VK_W;
            case 'e':
                return KeyEvent.VK_E;
            case ',':
                return KeyEvent.VK_COMMA;
            case '.':
                System.out.println("Now it's Player 1's turn.");
                return KeyEvent.VK_PERIOD;
            case 'k':
                return KeyEvent.VK_K;
            case 'l':
                return KeyEvent.VK_L;
            case ';':
                return KeyEvent.VK_SEMICOLON;
            case 'i':
                return KeyEvent.VK_I;
            case 'o':
                return KeyEvent.VK_O;
            case 'p':
                return KeyEvent.VK_P;
            default:
                return KeyEvent.CHAR_UNDEFINED;
        }
    }

    /**
     * Displays the option screen.
     */
    public void displayOptionsScreen()
    {
        //TO DO figure out key binding stuff...
    }

    /**
     * Displays the quit screen.
     */
    public void displayQuitScreen()
    {
        String choice = "";
        System.out.println("Are you sure you want to quit?");
        System.out.println("Please enter 'Yes' or 'No'.");
        while (!(choice.equals("Yes")) || !(choice.equals("No")))
        {
            choice = scanner.next();
            if (choice.equals("Yes"))
            {
                ActionEvent action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "Yes");
                ctrl.actionPerformed(action);
            }
            else if (choice.equals("No"))
            {
                ActionEvent action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "No");
                ctrl.actionPerformed(action);
            }
            else
            {
                System.out.println("Invalid command. Please enter 'Yes' or 'No'.");
            }
        }
    }

    /**
     * Displays the play again screen.
     */
    public void displayPlayAgainScreen()
    {
        String choice = "";
        if (matchModel.getWinner() == null)
        {
            System.out.println("Draw!");
        }
        else if(matchModel.getWinner().toString().equals("p1"))
        {
            System.out.println("Player 1 wins!");
        }
        else if (matchModel.getWinner().toString().equals("p2"))
        {
            System.out.println("Player 2 wins!");
        }
        System.out.println("Would you like to play again?");
        System.out.println("Please enter 'Yes' or 'No'.");
        while (!(choice.equals("Yes")) || !(choice.equals("No")))
        {
            choice = scanner.next();
            if (choice.equals("Yes"))
            {
                ActionEvent action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "mainMenuPAS");
                ctrl.actionPerformed(action);
            }
            else if (choice.equals("No"))
            {
                ActionEvent action = new ActionEvent(this,
                    ActionEvent.ACTION_PERFORMED, "quitPAS");
                ctrl.actionPerformed(action);
            }
            else
            {
                System.out.println("Invalid command. "
                    + "Please enter 'Yes' or 'No'.");
            }
        }
    }

    /**
     * Sets the controller for the ConsoleUI.
     * @param controller controller passed in from main
     */
    public void setController(GameController controller)
    {
        this.ctrl = controller;
    }

    /**
     * Sets the matchModel for the ConsoleUI.
     * @param matchModel model with all of the player's settings and
     * game play logic
     */
    public void setMatchModel(MatchModel matchModel)
    {
        this.matchModel = matchModel;
    }

    /**
     * Sets the settingsModel for the ConsoleUI.
     * @param settingsModel model passed in from main
     */
    public void setSettingsModel(SettingsModel settingsModel)
    {
        this.settingsModel = settingsModel;
    }

    /** Update the UI when notified by the game.
     *  @param o which observable notified us
     *  @param arg0 an object passed from the observable
     */
    public void update(Observable o, Object arg0)
    {
        printGameState();
    }

    /**
    * Outputs formatted data from the GameModel to the console.
    *
    * @pre An user has entered a command and caused a
    * change in the game state
    * @post The new game state is represented with a new printout of the data
    */
    public void printGameState()
    {
        if (matchModel.getColumns().size() == 1)
        {
            printGameState1Col();
        }
        else if(matchModel.getColumns().size() == 2)
        {
            printGameState2Cols();
        }
        else if (matchModel.getColumns().size() == THREE_COLUMNS)
        {
            printGameState3Cols();
        }
    }

    /**
     * Prints the game state when there is only 1 base on the board.
     */
    public void printGameState1Col()
    {
        //print whose turn it is
        System.out.println(matchModel.getTurn() + "'s turn");
        //print the top cards of the bases
        System.out.println("[--]" + "       " + checkIfSideNull(
            matchModel.getColumns().get(0).peekTopCard(PlayerID.PLAYER2)) +
            "       " + "[--]");

        //print out the base on the board
        System.out.println("[--]" + "       " + "[" +
            matchModel.getColumns().get(0).getBase().getOwner().toString() + "]"
            + "       " + "[--]");
        //print the bottom cards of the bases
        System.out.println("[--]" + "       " + checkIfSideNull(
            matchModel.getColumns().get(0).peekTopCard(PlayerID.PLAYER1)) +
            "       " + "[--]");

        //print out who is winning each base
        System.out.println("Winning B1: " +
            matchModel.getColumns().get(0).getCurrentWinner());
        //print out the discard piles for each player
        System.out.println("[DECK]     " + checkIfDiscardNull(
            matchModel.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard())
            + "     [DECK]     " + checkIfDiscardNull(
                matchModel.getPlayer(PlayerID.PLAYER2).getDeck().peekDiscard()));
    }

    /**
     * Prints the game state when there is only 2 bases are on the board.
     */
    public void printGameState2Cols()
    {
        //print whose turn it is
        System.out.println("" + matchModel.getTurn() + "'s turn");
        //print the top cards of the bases
        System.out.println(checkIfSideNull(
            matchModel.getColumns().get(0).peekTopCard(PlayerID.PLAYER2)) +
            "       " + "[--]" + "       " + checkIfSideNull(
                matchModel.getColumns().get(1).peekTopCard(PlayerID.PLAYER2)));

        //print the bases on the board
        System.out.println("[" +
            matchModel.getColumns().get(0).getBase().getOwner().toString()
            + "]" + "        " + "[--]" + "        " + "[" +
            matchModel.getColumns().get(1).getBase().getOwner().toString() +"]");

        //print the bottom cards of the bases
        System.out.println(checkIfSideNull(
            matchModel.getColumns().get(0).peekTopCard(PlayerID.PLAYER1)) +
            "       " + "[--]" + "       " + checkIfSideNull(
                matchModel.getColumns().get(1).peekTopCard(PlayerID.PLAYER1)));

        //print out who is winning each base
        System.out.println("Winning B1: " +
            matchModel.getColumns().get(0).getCurrentWinner() +
            "     " + "Winning B2: " +
            matchModel.getColumns().get(1).getCurrentWinner());
        //print out the discard piles for each player
        System.out.println("[DECK]     " + checkIfDiscardNull(
            matchModel.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard()) +
            "     [DECK]     " + checkIfDiscardNull(
                matchModel.getPlayer(PlayerID.PLAYER2).getDeck().peekDiscard()));
    }

    /**
     * Prints the game state when there is only 3 bases are on the board.
     */
    public void printGameState3Cols()
    {
        //print whose turn it is
        System.out.println("" + matchModel.getTurn() + "'s turn");
        //print the top cards of the bases
        System.out.println(checkIfSideNull(
            matchModel.getColumns().get(0).peekTopCard(PlayerID.PLAYER2)) +
            "       " + checkIfSideNull(
                matchModel.getColumns().get(1).peekTopCard(PlayerID.PLAYER2))
            + "       " + checkIfSideNull(
                matchModel.getColumns().get(2).peekTopCard(PlayerID.PLAYER2)));

        //print the bases on the board
        System.out.println("[" +
            matchModel.getColumns().get(0).getBase().getOwner().toString()
            + "]" + "        " + "[" +
            matchModel.getColumns().get(1).getBase().getOwner().toString()
            + "]" + "        " + "[" +
            matchModel.getColumns().get(2).getBase().getOwner().toString() + "]");

        //print the bottom cards of the bases
        System.out.println(checkIfSideNull(
            matchModel.getColumns().get(0).peekTopCard(PlayerID.PLAYER1)) +
            "       " + checkIfSideNull(
                matchModel.getColumns().get(1).peekTopCard(PlayerID.PLAYER1)) +
            "       " + checkIfSideNull(
                matchModel.getColumns().get(2).peekTopCard(PlayerID.PLAYER1)));

        //print out who is winning each base
        System.out.println("Winning B1: " +
            matchModel.getColumns().get(0).getCurrentWinner() +
            "     " + "Winning B2: " +
            matchModel.getColumns().get(1).getCurrentWinner()
            + "     " + "Winning B3: " +
            matchModel.getColumns().get(2).getCurrentWinner());
        //print out the discard piles for each player
        System.out.println("[DECK]     " + checkIfDiscardNull(
            matchModel.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard())
            + "     [DECK]     " + checkIfDiscardNull(
                matchModel.getPlayer(PlayerID.PLAYER2).getDeck().peekDiscard()));
    }

    /**
     * Prints the card out to the screen a certain way
     * based on whether it's null or not.
     * @param c  Top card on a side of a base
     * @return String representation of the base
     */
    public String checkIfSideNull(Card c)
    {
        if (c.getType() == CardType.BASE && c.getColor() == Color.COLORLESS)
        {
            return "[--]";
        }
        return "[" + c + "]";
    }

    /**
     * Prints the top card of the discard pile
     * out to the screen a certain way
     * based on whether it's null or not.
     * @param c  Top card on a side of a base
     * @return String representation of the top discard
     */
    public String checkIfDiscardNull(Card c)
    {
        if (c == null)
        {
            return "[--]";
        }
        return "[" + c + "]";
    }

    /**
     * Displays the deck info for a character when the user chooses a character.
     * @param player  player choosing the character
     * @param bc brawl character
     */
    public void updateDeckInfo(PlayerID player, BrawlCharacter bc)
    {

    }

    /**
     * Pauses the game.
     */
    public void paused()
    {
    }

    /**
     * Displays the gameplay options screen.
     */
    public void displayGameplayOptionsScreen()
    {
    }

    /**
     * Displays the control screen.
     */
    public void displayControlScreen()
    {
    }

    /**
     * Displays the about screen.
     */
    public void displayAboutScreen()
    {
    }
    
    /**
     * Displays the tutorial screen.
     */
    public void displayTutorialScreen()
    {
    }

    /**
     * Updating key bindings.
     */
    public void updateControlsScreen()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Sets the next page in the tutorial screen.
     * @param keyCode determines if the user wants to go forward or backward
     *        a page.
     */
    public void setNextTutorialPage(int keyCode)
    {
    }
    /**
     * Blinks a panel to give feedback that an invalid key was pressed
     */
    public void blinkInvalid()
    {
        
    }
}