package brawl.model.enums;

/**
 * The PlayerID class represents each player in the game.
 * 
 * @author FBRD-Paul Doyle
 * @version 1.0
 */
public enum PlayerID
{
    /** enumeration to represent the player id of player 1*/
    PLAYER1,
    /** enumeration to represent the player id of player 2*/
    PLAYER2;

    /**
     * Accesses the PlayerID of the other player in the game.
     *
     * @return the other PlayerID
     */
    public PlayerID getOther()
    {
        //IF this is equal to PLAYER1
        if (this.equals(PLAYER1))
        {
            //RETURN PLAYER2
            return PLAYER2;
        }
        return PLAYER1;
    }

    /**
     * Reads a string and converts it to a PlayerID
     * @param playerID string that represents a PlayerID
     * @return PlayerID value
     */
    public static PlayerID parsePlayerID(String playerID)
    {
        if ("p1".equalsIgnoreCase(playerID))
        {
            return PLAYER1;
        }
        else if ("p2".equalsIgnoreCase(playerID))
        {
            return PLAYER2;
        }
        return null;
    }

    /**
     * Converts a PlayerID to a string
     * @return A string representation of a PlayerID
     */
    @Override
    public String toString()
    {
        if (this.equals(PLAYER1))
        {
            return "p1";
        }
        return "p2";
    }
}
