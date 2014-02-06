/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brawl.utils;

import brawl.model.Move;
import brawl.model.enums.PlayerID;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Utility class that contains maps of key bindings and interfaces with the JSON
 * files that define them.  Allows for persistent user key bindings.
 * @author FBRD-Paul Doyle
 */
public class KeyMapper
{
    /*
     * Master key mappings for the game, to be used on first run and if user
     * wants to reset to default
     * Package private for testing purposes
     */
    private static String masterKeysPath = "resource/keyBindingsMaster.json";

    /*
     * Location of persistent key bindings file
     * Package private for testing purposes
     */
    private static String userKeysPath = JSONUtil.SETTINGS_PATH + "keyBindings.json";

    /*
     * List of keys that we don't want to be bound as they could hurt other
     * functionality in the game
     */
    private static final List<Integer> KEYS_BLACKLIST = Arrays.asList(
            KeyEvent.VK_ESCAPE,
            KeyEvent.VK_WINDOWS);

    /*
     * Map of key bindings for Player 1
     */
    private static Map<Integer, Move> p1Keys;

    /*
     * Map of key bindings for Player 2
     */
    private static Map<Integer, Move> p2Keys;

    /**
     * Represents a pass move
     */
    public static final int PASS_COLUMN = -2;

    static
    {
        // On class load, read in a set of key bindings
        readBindings();
    }

    /**
     * Binds a given keyCode to the move in question (it is not yet written to
     * the file)
     *
     * @param keyCode The code for the key to bind
     * @param move The move to bind the key to
     * @return Returns whether or not the move was bound; move will not be bound
     * if the Move object is invalid or the keyCode is already bound
     */
    public static boolean changeBinding(int keyCode, Move move)
    {
        // First make sure this key is a valid choice
        if (KEYS_BLACKLIST.contains(keyCode))
        {
            return false;
        }
        // Then confirm that this binding isn't already mapped
        if (p1Keys.containsKey(keyCode))
        {
            return false;
        }
        if (p2Keys.containsKey(keyCode))
        {
            return false;
        }

        // Lookup up the move so we know which binding to replace
        int code = lookupCurrentBinding(move);
        if (code == -1)
        {
            return false;
        }
        // Replace the entry for the move in question with a new binding
        if (PlayerID.PLAYER1.equals(move.getSource()))
        {
            p1Keys.remove(code);
            p1Keys.put(keyCode, move);
            return true;
        }
        else
        {
            p2Keys.remove(code);
            p2Keys.put(keyCode, move);
            return true;
        }
    }

    /**
     * Returns the key bindings map corresponding to the given player
     *
     * @param playerId Player whose key binding should be returned
     * @return The Map of key bindings for the player in question
     */
    public static Map<Integer, Move> getKeys(PlayerID playerId)
    {
        // Choose the correct map and return it
        if (playerId == PlayerID.PLAYER1)
        {
            return p1Keys;
        }
        else if (playerId == PlayerID.PLAYER2)
        {
            return p2Keys;
        }
        // If invalid input, return null
        else
        {
            return null;
        }
    }

    /**
     * Resets the key bindings to the ones in the master file.
     */
    public static void resetBindings()
    {
        // Read the master bindings and write them back to the user bindings
        readBindings(masterKeysPath);
        writeBindings();
    }

    /**
     * Write the current key bindings into the user's settings file.
     */
    public static void writeBindings()
    {
        // Create JSON hierarchy objects
        JSONObject keyBindings = new JSONObject();
        JSONArray p1KeyMap = new JSONArray();
        JSONArray p2KeyMap = new JSONArray();
        // Populate JSONArrays with JSONObjects for each binding
        for (Map.Entry<Integer, Move> entry : p1Keys.entrySet())
        {
            Map<String, String> bindingMap = new HashMap<String, String>();
            bindingMap.put("key", entry.getKey().toString());
            bindingMap.put("side", entry.getValue().getSide().toString());
            bindingMap.put("column", Integer.toString(
                    entry.getValue().getColumn()));
            p1KeyMap.add(new JSONObject(bindingMap));
        }
        for (Map.Entry<Integer, Move> entry : p2Keys.entrySet())
        {
            Map<String, String> bindingMap = new HashMap<String, String>();
            bindingMap.put("key", entry.getKey().toString());
            bindingMap.put("side", entry.getValue().getSide().toString());
            bindingMap.put("column", Integer.toString(
                    entry.getValue().getColumn()));
            p2KeyMap.add(new JSONObject(bindingMap));
        }
        // Add the arrays to the main JSONObject
        keyBindings.put("Player1", p1KeyMap);
        keyBindings.put("Player2", p2KeyMap);

        // Write the results to the JSON file
        JSONUtil.writeToJSON(keyBindings, userKeysPath);
    }

    /*
     * If the user has a key bindings file, read from that, otherwise read
     * from the master file
     */
    static void readBindings()
    {
        // Make sure the user settings file exists before trying to read it
        if (new File(userKeysPath).exists())
        {
            readBindings(userKeysPath);
        }
        // Otherwise fall back on the master resource
        else
        {
            readBindings(masterKeysPath);
        }
    }

    /**
     * Load the key bindings in the given resource file into this utility's
     * Map objects
     * @param resource The file to read from
     */
    static void readBindings(String resource)
    {
        // Create new maps for the key bindings
        p1Keys = new HashMap<Integer, Move>();
        p2Keys = new HashMap<Integer, Move>();

        // If we want the master bindings, we'll tell getJSON to use the class
        // loader
        boolean isInJar = (resource.equals(masterKeysPath));
        Object keyBindingsJSON = null;
        try
        {
            keyBindingsJSON = JSONUtil.getJSON(resource, isInJar);
        }
        catch (Exception readBindingsException)
        {
            if (resource.equals(masterKeysPath))
            {
                // Fail if the master bindings can't be read
                System.err.println(
                        "Master key bindings error. Jar may be corrupt.");
                System.exit(-1);
            }
            // If we attempted to load user bindings in error, fall back on the
            // master bindings
            System.err.println("Could not find settings file " + resource +
                    "; falling back on master key bindings.");
            resetBindings();
        }
        // Create a map from the read-in JSONObject
        Map<String, JSONArray> keyMap = (Map<String, JSONArray>) keyBindingsJSON;
        // Get the respective arrays and populate the key binding maps
        JSONArray p1KeyMap = (JSONArray) keyMap.get("Player1");
        JSONArray p2KeyMap = (JSONArray) keyMap.get("Player2");
        p1Keys = loadKeyMap(PlayerID.PLAYER1, p1KeyMap, p1Keys);
        p2Keys = loadKeyMap(PlayerID.PLAYER2, p2KeyMap, p2Keys);
    }

    /**
     * Load a given map of key bindings
     * @param source The player whose key bindings are being loaded
     * @param keyMapJson JSON Object that will be loaded from
     * @param keyMap The key bindings map to populate
     * @return a populated map of keybindings for the given player
     */
    private static Map<Integer, Move> loadKeyMap(PlayerID source,
            JSONArray keyMapJson,
            Map<Integer, Move> keyMap)
    {
        // Iterate through all the objects in the JSONArray
        for (Object moveObj : keyMapJson)
        {
            // Generate a pnKeys entry for each Object
            Map moveAttribs = (Map) moveObj;
            int keyCode = Integer.parseInt((String) moveAttribs.get("key"));
            PlayerID side = PlayerID.parsePlayerID((String) moveAttribs.get(
                    "side"));
            int column = Integer.parseInt((String) moveAttribs.get("column"));
            Move move = new Move(source, side, column);
            keyMap.put(keyCode, move);
        }

        return keyMap;
    }

    /**
     * Returns a key code that maps to the given move, if one exists
     * @param move The Move to look for in the existing maps
     * @return An integer value that corresponds to the key binding, or -1
     * if no move is found
     */
    private static int lookupCurrentBinding(Move move)
    {
        // Make sure move is valid before calling getters on it
        if (move == null)
        {
            return -1;
        }
        Map<Integer, Move> keyMap;
        // Figure out which map to search in
        if (move.getSource() == PlayerID.PLAYER1)
        {
            keyMap = p1Keys;
        }
        else
        {
            keyMap = p2Keys;
        }
        // Iterate through the key bindings map
        for (Map.Entry<Integer, Move> entry : keyMap.entrySet())
        {
            // Return a matching move
            Move listMove = entry.getValue();
            if (move.equals(listMove))
            {
                return entry.getKey();
            }
        }
        // Return -1 to signify that the move was not found
        return -1;
    }
}
