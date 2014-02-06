package brawl.model;

import brawl.model.enums.PlayerID;

/**
 * The Move class is responsible for containing the parameters of a move that
 * a player will make by pressing a keyboard button.
 *
 * @author FBRD-Paul Doyle
 * @version 1.0
 */
public class Move
{
    /**
     * Represents which player intends to play a card.
     */
    private PlayerID source;
    /**
     * Represents which player's side the card will be played on.
     */
    private PlayerID side;
    /**
     * Indicates the column on which a move is to be played.
     */
    private int column;
    /**
     * Defines whether or not this instance represents a draw move.
     */
    private boolean draw;

    /**
     * Constructor that initializes the fields of the object.
     * 
     * @param source Where the move originates
     * @param side What side the card is played
     * @param column Which column the card is played on
     */
    public Move(PlayerID source, PlayerID side, int column)
    {
        // SET this.source to source
        this.source = source;
        // SET this.side to side
        if (side == null)
        {
            this.side = source;
        }
        else
        {
            this.side = side;
        }
        // SET this.column to column
        this.column = column;
        if(column == -1)
        {
            draw = true;
        }
        else
        {
            draw = false;
        }
    }

    /**
     * Accesses the PlayerID for the Player who intends to play a Card.
     * @return the source
     */
    public PlayerID getSource()
    {
        return source;
    }

    /**
     * Accesses the side where the Player wants to play the Card on.
     * @return the side on which the card is to be played
     */
    public PlayerID getSide()
    {
        return side;
    }

    /**
     * Accesses the column index where the move will be made.
     * @return the column on which the card is to be played
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * Accesses whether or not the instance is a draw move.
     * @return whether or not this instance represents a draw move
     */
    public boolean isDraw()
    {
        return draw;
    }

    /**
     * Checks equality between two moves
     * @param obj The move object to compare against
     * @return true is moves are equal and false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        // Avoid NullPointerExceptions
        if (obj == null)
        {
            return false;
        }
        //Check source, side, and column of both moves
        Move move = (Move)obj;
        return ((this.source == move.getSource()) &&
            (this.side == move.getSide()) &&
            (this.column == move.getColumn()));
    }

    /**
     * Creates a string of the move
     *
     * @return the string representation of the move
     */
    @Override
    public String toString()
    {
        String result = "";
        result += source.toString();
        result += side.toString();
        result += "c" + Integer.toString(column);
        return result;
    }

    /**
     * Converts a string to a move
     *
     * @param moveString a string representation of a move
     * @return a new move
     */
    public static Move parseMove(String moveString)
    {
        final int ksecond = 4;
        final int kthird1 = 5;
        final int kthird2 = 6;
        PlayerID newSource = PlayerID.parsePlayerID(moveString.substring(0, 2));
        PlayerID newSide = PlayerID.parsePlayerID(
                moveString.substring(2, ksecond));
        int newColumn = Integer.parseInt(moveString.substring(
                kthird1, moveString.length()));
        
        //Checks to see if newSource and newSide are null
        if (newSource != null && newSide != null)
        {
            return new Move(newSource, newSide, newColumn);
        }
        return null;
    }
}
