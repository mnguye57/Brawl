package brawl.model;

import brawl.model.enums.*;
import brawl.utils.KeyMapper;
import brawl.utils.MoveValidator;
import java.util.List;

/**
 * The AI class represents an AI-controlled player and is responsible for
 * determining and triggering the actions performed by the AI.
 * @author FBRD-Zander Mitchell
 * @version 1.0
 */
public class AI extends Player
{
    private static final int MAX_COLUMNS = 3;

    /**
     * Represents the difficulty level of the AI, which will translate to the
     * speed at which it decides its moves
     */
    private int difficulty;

    private BrawlCharacter character;

    /**
     * Constructs a new AI Player from the given character and difficulty level
     *
     * @param character the character associated with this AI instance
     * @param id the PlayerID corresponding to this unit
     * @param difficulty value corresponding to how quickly the AI makes its
     * moves
     */
    public AI(BrawlCharacter character, PlayerID id, int difficulty)
    {
        super(character, id);
        this.character = character;
        this.difficulty = difficulty;
    }

    /**
     * Given the columns on the board, finds the first valid move and returns
     * a valid command corresponding to it.
     *
     * @param columns the list of columns on the board, which will be
     * @param canDraw Whether or not the player can draw on the next move
     * @return returns the String associated with an ActionCommand that
     * corresponds to the move to be played
     */
    public Move getNextMove(List<Column> columns, boolean canDraw)
    {
        Deck deck = super.getDeck();
        PlayerID side = id;
        int col = -1;
        if (deck.peekDiscard() == null)
        {
            return new Move(id, null, -1);
        }

        CardType cardType = deck.peekDiscard().getType();
        switch(cardType)
        {

            case HIT:
            case HIT2:
                for (int ndx = 0; ndx < columns.size(); ndx++)
                {
                    //IF valid
                    if (MoveValidator.isValid(deck.peekDiscard(),
                            columns.get(ndx).peekTopCard(id)))
                    {
                        // IF not frozen
                        if (!columns.get(ndx).isFrozen())
                        {
                            side = id;
                            col = ndx;
                        }
                    }
                }
                break;
            case BLOCK:
                for (int ndx = 0; ndx < columns.size(); ndx++)
                {
                    // IF valid
                    if (MoveValidator.isValid(deck.peekDiscard(),
                            columns.get(ndx).peekTopCard(id.getOther())))
                    {
                        // IF not frozen
                        if (!columns.get(ndx).isFrozen())
                        {
                            side = id.getOther();
                            col = ndx;
                        }
                    }
                }
                break;
            case PRESS:
                for (int ndx = 0; ndx < columns.size(); ndx++)
                {
                    // IF valid
                    if (MoveValidator.isValid(deck.peekDiscard(),
                            columns.get(ndx).peekTopCard(id)))
                    {
                        // IF not frozen
                        if (!columns.get(ndx).isFrozen())
                        {
                            // RETURN play player id on side id on column
                            side = id;
                            col = ndx;
                        }
                    }
                }
                break;
            case CLEAR:
                // IF only 1 column in play
                if (columns.size() < 2)
                {
                    break;
                }
                for (int ndx = 0; ndx < columns.size(); ndx++)
                {
                    // IF not frozen
                    if (!columns.get(ndx).isFrozen())
                    {
                        // IF not winning this column
                        if (columns.get(ndx).getCurrentWinner() != id)
                        {   //IF !(3 columns and playing on middle)
                            if( !(columns.size() == MAX_COLUMNS && ndx == 1) )
                            {
                                // RETURN clear column
                                side = id;
                                col = ndx;
                            }
                        }
                    }
                }
                break;
            case BASE:
                // IF there's room to play a base
                if (columns.size() < MAX_COLUMNS)
                {
                    // RETURN add base on left
                    side = id;
                    col = 0;
                }
                break;
            case FREEZE:
                for (int ndx = 0; ndx < columns.size(); ndx++)
                {
                    // IF not frozen
                    if (!columns.get(ndx).isFrozen())
                    {
                        // IF winning this column
                        if (columns.get(ndx).getCurrentWinner() == id)
                        {
                            side = id;
                            col = ndx;
                        }
                    }
                }
                break;
            default:
                break;
        }
        // Fix for defect 428
        switch(columns.size())
        {
            case 1:
                if (col == 0 && cardType != CardType.BASE)
                {
                    col = 1;
                }
                break;
            case 2:
                if (col == 1)
                {
                    col = 2;
                }
                break;
            default:
                break;
        }

        if (deck.getDeck().size() == 0 && col == -1)
        {
            col = KeyMapper.PASS_COLUMN;
        }
        if (col == -1 && !canDraw)
        {
            col = KeyMapper.PASS_COLUMN;
        }
        return new Move(id, side, col);
    }

    /**
     * Gets the move that corresponds to the key in the HashMap.
     * @param keyCode key for the HashMap
     * @return the Move that corresponds to the key in the HashMap
     */
    public Move getMove(int keyCode)
    {
        // No keyCode for AI
        return null;
    }

    /**
     * Accessor for difficulty value
     *
     * @return the difficulty of this UI
     */
    public int getDifficulty()
    {
        return difficulty;
    }
}
