/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package brawl.model;

import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.CardType;
import brawl.model.enums.Color;
import brawl.model.enums.PlayerID;
import java.util.ArrayList;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
import java.lang.reflect.Field;
import java.util.Stack;


/**
 *
 * @author Ray
 */
public class AITest extends TestCase {
    
    public AITest(String testName) {
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

    public void testGetNextMove()
    {
        AI ai = new AI(BrawlCharacter.MILTON, PlayerID.PLAYER1, 1);
        Column c1 = mock(Column.class);
        Deck deck = mock(Deck.class);
        Move expected1 = new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, 1);
        Move expected2 = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 1);
        Move result;

        // Use java.lang.reflect to make private fields accessible
        Field deckf = null;
        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(ai, deck);
        }
        catch (IllegalAccessException ex) {}


        //ai.setDeck(deck);
        ArrayList<Column> list = new ArrayList<Column>();
        list.add(c1);

        // HIT !FROZEN
        when(c1.peekTopCard(PlayerID.PLAYER1)).
                thenReturn(new Card(Color.RED, CardType.HIT));
        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.HIT));

        when(deck.getDeck()).thenReturn(mock(Stack.class));
        when(deck.getDeck().size()).thenReturn(1);
        result = ai.getNextMove(list, true);
        assertEquals(expected1.getColumn(), result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        // HIT2 !FROZEN
        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.HIT2));
        result = ai.getNextMove(list, true);
        assertEquals(expected1.getColumn(), result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        // BLOCK !FROZEN
        // Defect 391: Fixing so that it's looking at the right side
        when(c1.peekTopCard(PlayerID.PLAYER2)).
                thenReturn(new Card(Color.RED, CardType.HIT));
        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.BLOCK));
        result = ai.getNextMove(list, true);
        assertEquals(expected2.getColumn(), result.getColumn());
        assertEquals(expected2.getSide(), result.getSide());
        assertEquals(expected2.getSource(), result.getSource());

        // PRESS !FROZEN
        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.PRESS));
        when(c1.peekTopCard(PlayerID.PLAYER1)).thenReturn(new Card(Color.RED,
                CardType.BLOCK));
        result = ai.getNextMove(list, true);
        assertEquals(expected1.getColumn(), result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        // CLEAR size < 2
        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.CLEAR));
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // CLEAR size >= 2
        Column c2 = mock(Column.class);
        list.add(c2);

        when(c1.getCurrentWinner()).thenReturn(PlayerID.PLAYER2);
        when(c2.getCurrentWinner()).thenReturn(PlayerID.PLAYER1);
        result = ai.getNextMove(list, true);
        assertEquals(0, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // CLEAR DRAW
        when(c1.getCurrentWinner()).thenReturn(PlayerID.PLAYER1);
        when(c2.getCurrentWinner()).thenReturn(PlayerID.PLAYER1);
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // BASE
        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.BASE));
        result = ai.getNextMove(list, true);
        assertEquals(0, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // BASE DRAW
        Column c3 = mock(Column.class);
        list.add(c3);
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        list.remove(c3);

        // FREEZE
        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.FREEZE));
        when(c1.getCurrentWinner()).thenReturn(PlayerID.PLAYER1);
        when(c2.getCurrentWinner()).thenReturn(PlayerID.PLAYER2);
        result = ai.getNextMove(list, true);
        assertEquals(0, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // FREEZE DRAW
        when(c1.getCurrentWinner()).thenReturn(PlayerID.PLAYER2);
        when(c2.getCurrentWinner()).thenReturn(PlayerID.PLAYER2);
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // FROZEN COLUMNS
        list.remove(c1);
        list.remove(c2);
        c1.freeze();

        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.HIT));
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.HIT2));
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.BLOCK));
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.PRESS));
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());
    }

    public void testGetMove()
    {
        AI ai = new AI(BrawlCharacter.MILTON, PlayerID.PLAYER1, 1);
        assertNull(ai.getMove(0));    
    }

    // Test for defect 391
    public void testBlockSide()
    {
        AI ai = new AI(BrawlCharacter.MILTON, PlayerID.PLAYER1, 1);
        Column c1 = mock(Column.class);
        Deck deck = mock(Deck.class);
        
        // Use java.lang.reflect to make private fields accessible
        Field deckf = null;
        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(ai, deck);
        }
        catch (IllegalAccessException ex) {}

        Move expected = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 1);
        Move result;
        ArrayList<Column> list = new ArrayList<Column>();
        list.add(c1);

        when(c1.peekTopCard(PlayerID.PLAYER2)).
                thenReturn(new Card(Color.RED, CardType.HIT));
        when(deck.peekDiscard()).thenReturn(new Card(Color.RED, CardType.BLOCK));
        when(deck.getDeck()).thenReturn(mock(Stack.class));
        when(deck.getDeck().size()).thenReturn(1);
        result = ai.getNextMove(list, true);
        assertNotNull(result);
        assertEquals(expected.getColumn(), result.getColumn());
        assertEquals(expected.getSide(), result.getSide());
        assertEquals(expected.getSource(), result.getSource());
    }

}
