package brawl.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import brawl.model.Move;
import brawl.model.enums.PlayerID;
import junit.framework.TestCase;

/**
 *
 * @author Stephen
 */
public class MoveTest extends TestCase {
    
    public MoveTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

    public void testSource()
    {
        Move move = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        assertEquals(move.getSource(),PlayerID.PLAYER1);
    }

    public void testSide()
    {
        Move move = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        assertEquals(move.getSide(),PlayerID.PLAYER2);
    }

    public void testDraw()
    {
        Move move1 = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        Move move2 = new Move(PlayerID.PLAYER1, null, -1);
        assertFalse(move1.isDraw());
        assertTrue(move2.isDraw());
    }

    public void testToString()
    {
        Move move = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2,0);
        assertEquals("p1p2c0",move.toString());
    }

    public void testParseMove()
    {
        Move move = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2,0);
        Move move2 = null;
        assertEquals(move,Move.parseMove("p1p2c0"));
        assertFalse(move.equals(move2));
        assertEquals(move2,Move.parseMove("p3p2c0"));
    }
}
