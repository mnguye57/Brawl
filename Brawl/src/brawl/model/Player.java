package brawl.model;

import brawl.model.enums.PlayerID;
import java.util.List;

/**
 * The Player class represents one of the players of the game, determining what
 * actions are to be performed.
 *
 * @author FBRD-Zander Mitchell
 * @version 1.0
 */
public abstract class Player
{
    /**
     * Represents the deck used by a particular player.
     */
    private Deck deck;
    /**
     * Represents which player this instance models (player 1 or player 2).
     */
    protected PlayerID id;

    /**
     * Constructs a new player with a deck containing
     * the given character.
     *
     * @param character the Brawl character corresponding to the Player instance
     * @param id the PlayerID for the Player instance 
     */
    public Player(brawl.model.enums.BrawlCharacter character, PlayerID id)
    {
        //SET this.deck to CREATE a new Deck with character
        this.deck = new Deck(character);
        //SET this.id to id
        this.id = id;
    }

    /**
     * Gets the move that corresponds to the keycode
     * @param keyCode The keycode to use to get the correct move
     * @return The move that corresponds to the keycode
     */
    public abstract Move getMove(int keyCode);

    /**
     * Gets the deck for this player
     * @return The deck for this player
     */
    public Deck getDeck()
    {
        return deck;
    }

    /**
     * Gets the next move for the player
     * @param columns Columns from which to generate a move
     * @param canDraw Whether or not the AI can draw (based on game mode)
     * @return a Move object
     */
    public abstract Move getNextMove(List<Column> columns, boolean canDraw);
}
