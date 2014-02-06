/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package brawl.model;

import java.util.ArrayList;
import junit.framework.TestCase;
import brawl.model.enums.*;
import java.lang.reflect.Field;


/**
 *
 * @author Ray
 */
public class AIIntTest extends TestCase {
    
    public AIIntTest(String testName) {
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
        Column c1 = new Column(new Base(PlayerID.PLAYER2));
        Deck deck = new Deck(BrawlCharacter.MILTON);
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

        // HIT !FROZEN
        c1.addCard(new Card(Color.RED, CardType.HIT), PlayerID.PLAYER1);
        deck.generateDeck("RHi");
        deck.draw();
        list.add(c1);
        
        result = ai.getNextMove(list, true);
        assertEquals(expected1.getColumn(), result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        // HIT2 !FROZEN
        result = ai.getNextMove(list, true);
        assertEquals(expected1.getColumn(), result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        // BLOCK !FROZEN
        deck.generateDeck("RBl");
        deck.draw();
        c1.addCard(new Card(Color.RED, CardType.HIT), PlayerID.PLAYER2);
        result = ai.getNextMove(list, true);
        assertEquals(expected2.getColumn(), result.getColumn());
        assertEquals(expected2.getSide(), result.getSide());
        assertEquals(expected2.getSource(), result.getSource());

        // PRESS !FROZEN
        deck.generateDeck("RPr");
        deck.draw();
        c1.addCard(new Card(Color.RED, CardType.BLOCK), PlayerID.PLAYER1);
        result = ai.getNextMove(list, true);
        assertEquals(expected1.getColumn(), result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        // CLEAR size < 2
        deck.generateDeck("RCl RCl");
        deck.draw();
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // CLEAR size >= 2
        Column c2 = new Column(new Base(PlayerID.PLAYER1));
        list.add(c2);

        // Add cards to the other side so that AI sees it's losing this column
        c1.addCard(new Card(Color.RED, CardType.HIT), PlayerID.PLAYER2);
        c1.addCard(new Card(Color.RED, CardType.HIT), PlayerID.PLAYER2);
        result = ai.getNextMove(list, true);
        assertEquals(0, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // CLEAR DRAW
        list.remove(c1);
        c1 = new Column(new Base(PlayerID.PLAYER1));
        list.add(0, c1);
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // BASE
        deck.generateDeck("RBa");
        deck.draw();
        result = ai.getNextMove(list, true);
        assertEquals(0, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // BASE DRAW
        Column c3 = new Column(new Base(PlayerID.PLAYER1));
        list.add(c3);
        result = ai.getNextMove(list, false);
        assertEquals(-2, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        list.remove(c3);

        // FREEZE
        deck.generateDeck("RFr GFr");
        deck.draw();
        list.remove(c2);
        c2 = new Column(new Base(PlayerID.PLAYER2));
        list.add(c2);
        result = ai.getNextMove(list, true);
        assertEquals(0, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // FREEZE DRAW
        list.remove(c1);
        c1 = new Column(new Base(PlayerID.PLAYER2));
        list.add(0, c1);
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(PlayerID.PLAYER1, result.getSide());
        assertEquals(PlayerID.PLAYER1, result.getSource());

        // FROZEN COLUMNS
        list.remove(c1);
        list.remove(c2);
        c1.freeze();

        deck.generateDeck("RHi GH2");
        deck.draw();
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        deck.generateDeck("RH2 GH2");
        deck.draw();
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        deck.generateDeck("RBl GH2");
        deck.draw();
        result = ai.getNextMove(list, true);
        assertEquals(-1, result.getColumn());
        assertEquals(expected1.getSide(), result.getSide());
        assertEquals(expected1.getSource(), result.getSource());

        deck.generateDeck("RPr RBl");
        deck.draw();
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

}