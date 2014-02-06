package brawl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import brawl.model.enums.Color;
import java.lang.reflect.Field;
import java.util.Stack;
import brawl.model.Card;
import brawl.model.Deck;
import brawl.model.Move;
import brawl.model.Player;
import brawl.model.enums.CardType;
import brawl.model.enums.GameMode;
import brawl.model.enums.PlayerID;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;
/**
 *
 * @author Stephen
 */
public class MatchModelTest extends TestCase {
    
    public MatchModelTest(String testName) {
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

    // Defect #405
    public void testConstructorBug405()
    {
        Deck deck1 = mock(Deck.class);
        Deck deck2 = mock(Deck.class);
        Player py1 = mock(Player.class);
        Player py2 = mock(Player.class);
        MatchModel model = null;
        
        Field deckf = null;
        Field deckf2 = null;

        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf2 = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
            deckf2.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(py1, deck1);
            deckf2.set(py2, deck2);
        }
        catch (IllegalAccessException ex) {}


        Stack p1stk = mock(Stack.class);
        for (int i = 0; i < 35; i++)
        {
            p1stk.add(new Card(Color.COLORLESS, CardType.BASE));
        }
        Stack p2stk = mock(Stack.class);
        p2stk.addAll(p1stk);

        when(py1.getDeck()).thenReturn(deck1);
        when(py2.getDeck()).thenReturn(deck2);
        when(deck1.getDeck()).thenReturn(p1stk);
        when(deck2.getDeck()).thenReturn(p2stk);
        when(p1stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p2stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p1stk.size()).thenReturn(34);
        when(p2stk.size()).thenReturn(34);
        model = new MatchModel(py1, py2, GameMode.TOURNAMENT);

        assertEquals(2, model.getColumns().size());
        assertEquals(34, model.getPlayer(PlayerID.PLAYER1).getDeck().getDeck().size());
        assertEquals(34, model.getPlayer(PlayerID.PLAYER2).getDeck().getDeck().size());
        assertEquals(GameMode.TOURNAMENT, model.getGameMode());
        assertEquals(null, model.getTurn());
    }

    public void testDiscardHit()
    {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d1 = mock(Deck.class);
        Deck d2 = mock(Deck.class);

        Field deckf = null;
        Field deckf2 = null;

        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf2 = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
            deckf2.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(p1, d1);
            deckf2.set(p2, d2);
        }
        catch (IllegalAccessException ex) {}

        Stack p1stk = mock(Stack.class);
        for (int i = 0; i < 35; i++)
        {
            p1stk.add(new Card(Color.RED, CardType.HIT));
        }
        Stack p2stk = mock(Stack.class);
        p2stk.addAll(p1stk);

        when(p1.getDeck()).thenReturn(d1);
        when(p2.getDeck()).thenReturn(d2);
        when(d1.getDeck()).thenReturn(p1stk);
        when(d2.getDeck()).thenReturn(p2stk);
        when(p1stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p2stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p1stk.size()).thenReturn(34);
        when(p2stk.size()).thenReturn(34);
        when(p1.getDeck().peekDiscard()).thenReturn(new Card(Color.RED, CardType.HIT));

        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.drawCard(PlayerID.PLAYER1);
        Card card = p1.getDeck().peekDiscard();
        assertEquals(card, model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard());
    }

    public void testDiscardBaseClear()
    {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d1 = mock(Deck.class);
        Deck d2 = mock(Deck.class);

        Field deckf = null;
        Field deckf2 = null;

        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf2 = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
            deckf2.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(p1, d1);
            deckf2.set(p2, d2);
        }
        catch (IllegalAccessException ex) {}

        Stack p1stk = mock(Stack.class);
        p1stk.push(new Card(Color.COLORLESS, CardType.CLEAR));
        p1stk.push(new Card(Color.COLORLESS, CardType.BASE));
        p1stk.push(new Card(Color.COLORLESS, CardType.BASE));
        
        Stack p2stk = mock(Stack.class);
        p2stk.addAll(p1stk);

        when(p1.getDeck()).thenReturn(d1);
        when(p2.getDeck()).thenReturn(d2);
        when(d1.getDeck()).thenReturn(p1stk);
        when(d2.getDeck()).thenReturn(p2stk);
        when(p1stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p2stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p1stk.size()).thenReturn(34);
        when(p2stk.size()).thenReturn(34);
        when(p1.getDeck().peekDiscard()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));

        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.drawCard(PlayerID.PLAYER1);
        Card card = p1.getDeck().peekDiscard();
        assertEquals(card, model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard());
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(3, model.getColumns().size());
        when(p1.getDeck().peekDiscard()).thenReturn(new Card(Color.COLORLESS, CardType.CLEAR));
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(2, model.getColumns().size());
    }

    public void testDiscardFreeze()
    {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d1 = mock(Deck.class);
        Deck d2 = mock(Deck.class);

        Field deckf = null;
        Field deckf2 = null;

        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf2 = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
            deckf2.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(p1, d1);
            deckf2.set(p2, d2);
        }
        catch (IllegalAccessException ex) {}

        Stack p1stk = mock(Stack.class);
        Stack p2stk = mock(Stack.class);

        when(p1.getDeck()).thenReturn(d1);
        when(p2.getDeck()).thenReturn(d2);
        when(d1.getDeck()).thenReturn(p1stk);
        when(d2.getDeck()).thenReturn(p2stk);
        when(p1stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p2stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p1stk.size()).thenReturn(34);
        when(p2stk.size()).thenReturn(34);
        when(p1.getDeck().peekDiscard()).thenReturn(new Card(Color.COLORLESS, CardType.FREEZE));

        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertTrue(model.getColumns().get(0).isFrozen());
    }
    
    public void testGameOver()
    {       
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d1 = mock(Deck.class);
        Deck d2 = mock(Deck.class);

        Field deckf = null;
        Field deckf2 = null;

        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf2 = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
            deckf2.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(p1, d1);
            deckf2.set(p2, d2);
        }
        catch (IllegalAccessException ex) {}

        Stack p1stk = mock(Stack.class);
        Stack p2stk = mock(Stack.class);

        when(p1.getDeck()).thenReturn(d1);
        when(p2.getDeck()).thenReturn(d2);
        when(d1.getDeck()).thenReturn(p1stk);
        when(d2.getDeck()).thenReturn(p2stk);
        when(p1stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p2stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p1stk.size()).thenReturn(34);
        when(p2stk.size()).thenReturn(34);
        when(p1.getDeck().peekDiscard()).thenReturn(new Card(Color.COLORLESS, CardType.FREEZE));

        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertTrue(model.getColumns().get(0).isFrozen());
        assertFalse(model.isGameOver());
        model.drawCard(PlayerID.PLAYER1);
        Card card = p1.getDeck().peekDiscard();
        System.out.println("Card is " + card.toString());
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER2, 2);
        assertTrue(model.getColumns().get(1).isFrozen());
        assertTrue(model.isGameOver());
    }

    public void testWinner()
    {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d1 = mock(Deck.class);
        Deck d2 = mock(Deck.class);

        Field deckf = null;
        Field deckf2 = null;

        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf2 = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
            deckf2.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(p1, d1);
            deckf2.set(p2, d2);
        }
        catch (IllegalAccessException ex) {}

        Stack p1stk = mock(Stack.class);
        Stack p2stk = mock(Stack.class);

        when(p1.getDeck()).thenReturn(d1);
        when(p2.getDeck()).thenReturn(d2);
        when(d1.getDeck()).thenReturn(p1stk);
        when(d2.getDeck()).thenReturn(p2stk);
        when(p1stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p2stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p1.getDeck().peekDiscard()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));

        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(3, model.getColumns().size());
        assertEquals(PlayerID.PLAYER1, model.getWinner());
    }

    public void testSwitchTurn()
    {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d1 = mock(Deck.class);
        Deck d2 = mock(Deck.class);

        Field deckf = null;
        Field deckf2 = null;

        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf2 = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
            deckf2.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(p1, d1);
            deckf2.set(p2, d2);
        }
        catch (IllegalAccessException ex) {}

        Stack p1stk = mock(Stack.class);
        Stack p2stk = mock(Stack.class);

        when(p1.getDeck()).thenReturn(d1);
        when(p2.getDeck()).thenReturn(d2);
        when(d1.getDeck()).thenReturn(p1stk);
        when(d2.getDeck()).thenReturn(p2stk);
        when(p1stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p2stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p1stk.size()).thenReturn(34);
        when(p2stk.size()).thenReturn(34);
        when(p1.getDeck().peekDiscard()).thenReturn(new Card(Color.BLUE, CardType.HIT));

        MatchModel model = new MatchModel(p1, p2, GameMode.TRAINING);
        assertEquals(PlayerID.PLAYER1, model.getTurn());
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(PlayerID.PLAYER2, model.getTurn());   
    }

    public void testGetMove()
    {
        Player p1 = mock(Player.class);
        Player p2 = mock(Player.class);
        Deck d1 = mock(Deck.class);
        Deck d2 = mock(Deck.class);

        Field deckf = null;
        Field deckf2 = null;

        try
        {
            deckf = Player.class.getDeclaredField("deck");
            deckf2 = Player.class.getDeclaredField("deck");
            deckf.setAccessible(true);
            deckf2.setAccessible(true);
        }
        catch (NoSuchFieldException ex) {}

        try
        {
            deckf.set(p1, d1);
            deckf2.set(p2, d2);
        }
        catch (IllegalAccessException ex) {}

        Stack p1stk = mock(Stack.class);
        Stack p2stk = mock(Stack.class);

        when(p1.getDeck()).thenReturn(d1);
        when(p2.getDeck()).thenReturn(d2);
        when(d1.getDeck()).thenReturn(p1stk);
        when(d2.getDeck()).thenReturn(p2stk);
        when(p1stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p2stk.pop()).thenReturn(new Card(Color.COLORLESS, CardType.BASE));
        when(p1stk.size()).thenReturn(34);
        when(p2stk.size()).thenReturn(34);
        when(p1.getDeck().peekDiscard()).thenReturn(new Card(Color.BLUE, CardType.HIT));
        when(p1.getMove(0)).thenReturn(new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, 0));

        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.drawCard(PlayerID.PLAYER1);
        Move m = p1.getMove(0);
        assertEquals(m, model.getMove(0));
    }
}
