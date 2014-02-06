/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brawl.utils;

import brawl.model.Move;
import brawl.model.enums.PlayerID;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author FBRD-Paul Doyle
 */
public class KeyMapperIntTest extends TestCase
{
    public KeyMapperIntTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        Field userKeysField = KeyMapper.class.getDeclaredField("userKeysPath");
        Field masterKeysField = KeyMapper.class.getDeclaredField("masterKeysPath");

        userKeysField.setAccessible(true);
        masterKeysField.setAccessible(true);

        userKeysField.set(null, JSONUtil.SETTINGS_PATH + "keyBindingsTest.json");
        masterKeysField.set(null, "resource/keyBindingsMasterTest.json");
        KeyMapper.readBindings((String)masterKeysField.get(null));
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Test of changeBinding method, of class KeyMapper.
     */
    public void testChangeBinding()
    {
        Move validMoveP1 = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        Move validMoveP2 = new Move(PlayerID.PLAYER2, PlayerID.PLAYER2, 0);
        Move invalidMove = new Move(PlayerID.PLAYER2, PlayerID.PLAYER2, 5);
        Integer newBinding = 0;
        // blacklisted remapping
        newBinding = KeyEvent.VK_ESCAPE;
        assertFalse(KeyMapper.changeBinding(newBinding, validMoveP1));
        // already-in-use remapping p1
        newBinding = 81;
        assertFalse(KeyMapper.changeBinding(newBinding, validMoveP1));
        // already-in-use remapping p2
        newBinding = 73;
        assertFalse(KeyMapper.changeBinding(newBinding, validMoveP1));
        // move not valid
        newBinding = 66;
        assertFalse(KeyMapper.changeBinding(newBinding, invalidMove));
        // valid remapping p1
        assertTrue(KeyMapper.changeBinding(newBinding, validMoveP1));
        Map result = KeyMapper.getKeys(PlayerID.PLAYER1);
        assertTrue(result.containsKey(newBinding));
        assertEquals(validMoveP1, result.get(newBinding));
        // valid remapping p2
        newBinding = 65;
        assertTrue(KeyMapper.changeBinding(newBinding, validMoveP2));
        result = KeyMapper.getKeys(PlayerID.PLAYER2);
        assertTrue(result.containsKey(newBinding));
        assertEquals(validMoveP2, result.get(newBinding));
    }

    /**
     * Test of KeyMapper.getKeys
     */
    public void testGetKeys()
    {
        Map<Integer, Move> expResult = new HashMap<Integer, Move>();
        expResult.put(90, new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, -1));
        expResult.put(81, new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0));
        Map result = KeyMapper.getKeys(PlayerID.PLAYER1);
        for (Integer keyCode : expResult.keySet())
        {
            assertTrue(result.containsKey(keyCode));
            assertEquals(expResult.get(keyCode),result.get(keyCode));
        }
    }

    /**
     * Test of writeBindings method, of class KeyMapper.
     */
    public void testWriteBindings()
    {
        testChangeBinding();
        Integer newBinding = new Integer(66);
        Move move = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        KeyMapper.writeBindings();
        KeyMapper.readBindings();
        Map writtenResults = KeyMapper.getKeys(PlayerID.PLAYER1);
        assertTrue(writtenResults.containsKey(newBinding));
        assertEquals(move, writtenResults.get(newBinding));
    }

    /**
     * Test of resetBindings method, of class KeyMapper.
     */
    public void testResetBindings()
    {
        testWriteBindings();
        Integer oldBinding = new Integer(81);
        Move move = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        KeyMapper.resetBindings();
        KeyMapper.readBindings();
        Map result = KeyMapper.getKeys(PlayerID.PLAYER1);
        assertTrue(result.containsKey(oldBinding));
        assertEquals(move, result.get(oldBinding));
    }
}