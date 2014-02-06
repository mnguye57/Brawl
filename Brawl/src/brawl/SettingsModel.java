package brawl;

import brawl.model.AI;
import brawl.model.Human;
import brawl.model.Move;
import brawl.model.Player;
import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.GameMode;
import brawl.model.enums.PlayerID;
import brawl.utils.KeyMapper;
import java.util.HashMap;

/**
 * The SettingsModel class is responsible for keeping track of the state that
 * the menu is in. This includes player settings for a match as well as
 * the key maps for each player.
 *
 * @author Paul
 * @version 1.0
 */
public class SettingsModel
{
    /**
     * Represents the player that is currently choosing their options
     */
    private PlayerID currentPlayerId;
    /**
     * Indicates whether or not the current player has selected the human
     * player option
     */
    private boolean isHuman;
    /**
     * Represents the difficulty of an AI player (if AI is selected)
     */
    private int difficulty;
    /**
     * The selected character whose deck will be used by the current player
     */
    private BrawlCharacter character;
    /**
     * Player object for Player 1 (to be passed to the MatchModel)
     */
    private Player p1;
    /**
     * Player object for Player 2 (to be passed to the MatchModel)
     */
    private Player p2;
    /**
     * Tracks whether or not the game is tournament or training/tutorial
     */
    private GameMode mode;

    /**
     * Construct a new SettingsModel and initialize it with default values
     */
    public SettingsModel()
    {
        // Set some default values
        currentPlayerId = PlayerID.PLAYER1;
        isHuman = true;
        difficulty = -1;
        p1 = null;
        p2 = null;
        character = BrawlCharacter.HUMPHREY;
        mode = GameMode.TOURNAMENT;
    }

    /**
     * Generate and store a player object based on the currently selected
     * options
     */
    public void setPlayer()
    {
        // Get an appropriate new player object
        Player newPlayer;
        if (isHuman)
        {
            HashMap<Integer, Move> keyMap = (HashMap)KeyMapper.getKeys(currentPlayerId);
            newPlayer = new Human(character, currentPlayerId, keyMap);
        }
        else
        {
            newPlayer = new AI(character, currentPlayerId, difficulty);
        }

        // Set the correct reference variable to this new player
        if (currentPlayerId == PlayerID.PLAYER1)
        {
            p1 = newPlayer;
        }
        else
        {
            p2 = newPlayer;
        }
    }

    /**
     * Generate a MatchModel with the correct Player inputs and pass it back to
     * the caller
     *
     * @return the new model to be stored in the controller
     */
    public MatchModel startGame()
    {
        return new MatchModel(p1, p2, mode);
    }

    /**
     * Accessor for the currentPlayerId
     *
     * @return the current Player whose options are available to modify
     */
    public PlayerID getCurrentPlayerId()
    {
        return currentPlayerId;
    }

    /**
     * Switch the PlayerID whose options are being set up
     */
    public void changeCurrentPlayerId()
    {
        // Set the currentPlayerId to be the opposite of what it is now
        currentPlayerId = currentPlayerId.getOther();
    }

    /**
     * Accessor for the isHuman value
     *
     * @return whether or not the current player is human
     */
    public boolean isHuman()
    {
        return isHuman;
    }

    /**
     * Setter for isHuman variable
     *
     * @param isHuman toggle whether or not the Player is Human or AI
     */
    public void setIsHuman(boolean isHuman)
    {
        this.isHuman = isHuman;
    }

    /**
     * Accessor for the difficulty of an AI player
     *
     * @return the difficulty of the current AI player
     */
    public int getDifficulty()
    {
        if (isHuman)
        {
            return -1;
        }
        return difficulty;
    }

    /**
     * Setter for the difficulty of an AI character
     *
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    /**
     * Accessor to the character variable
     *
     * @return the character
     */
    public BrawlCharacter getCharacter()
    {
        return character;
    }

    /**
     * Setter for the character variable
     *
     * @param character the character to assign to the current Player
     */
    public void setCharacter(BrawlCharacter character)
    {
        this.character = character;
    }

    /**
     * Accessor for the game mode
     *
     * @return the game mode currently selected
     */
    public GameMode getMode()
    {
        return mode;
    }

    /**
     * Set the game mode
     *
     * @param mode the desired mode to set
     */
    public void setMode(GameMode mode)
    {
        this.mode = mode;
    }

}