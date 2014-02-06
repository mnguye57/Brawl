package brawl.model;

import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.PlayerID;
import java.util.HashMap;
import java.util.List;

/**
 * The Human class represents a human player (controlled via keyboard inputs)
 * and is responsible for determining the appropriate action to perform when
 * a key is pressed.
 *
 * @author FBRD-Zander Mitchell
 * @version 1.0
 */
public class Human extends Player
{
    /**
     * Contains a mapping of keyboard button codes to different commands
     * available to the player
     */
    private HashMap<Integer, Move> keyMap;

    /**
     * Creates a new player object with the given character and Map of key
     * bindings
     *
     * @param character Brawl character corresponding to this Human instance
     * @param id The PlayerID that corresponds
     * @param keyMap a map of keycodes to strings representing corresponding
     * actions
     */
    public Human(BrawlCharacter character, PlayerID id,
            HashMap<Integer, Move> keyMap)
    {
        super(character, id);

        //SET this.keyMap to local variable, keyMap
        this.keyMap = keyMap;
    }

    /**
     * Gets the proper Move that corresponds to the given keyCode by using the
     * HashMap.
     * @param keyCode key that is looked up in the HashMap
     * @return a reference to the Move that corresponds to the location of the
     * keyCode in the HashMap
     */
    public Move getMove(int keyCode)
    {
        // RETURN CALL get on keyMap with keyCode
        return keyMap.get(keyCode);
    }

    /**
     * Gets the next move for the player
     * @param columns Columns from which to generate a move
     * @param canDraw Whether or not the player can draw on the next move
     * @return a Move object
     */
    public Move getNextMove(List<Column> columns, boolean canDraw)
    {
        return null;
    }
}
