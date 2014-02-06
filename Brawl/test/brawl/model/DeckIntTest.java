/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brawl.model;
import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.CardType;
import brawl.model.enums.Color;
import junit.framework.TestCase;

/**
 *
 * @author Ebele
 */
public class DeckIntTest extends TestCase
{
    public DeckIntTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

    public void testGetCharacter()
    {
        Deck deck = new Deck(BrawlCharacter.BEATRICE);
        assertEquals(BrawlCharacter.BEATRICE, deck.getCharacter());
    }

    public void testReset()
    {
        Deck deck = new Deck(BrawlCharacter.ESTHER);
        deck.shuffle();

        deck.draw();
        deck.draw();
        deck.draw();

        assertEquals(32, deck.getDeck().size());
        deck.reset();
        assertEquals(35, deck.getDeck().size());
    }

    public void testShuffle()
    {
        Deck deck = new Deck(BrawlCharacter.HUMPHREY);
        deck.shuffle();
        for (int i = 0; i < 32; i++)
        {
            deck.draw();
        }

        assertEquals(CardType.FREEZE, deck.getDeck().peek().getType());
        deck.draw();
        assertEquals(CardType.FREEZE, deck.getDeck().peek().getType());
        deck.draw();
        assertEquals(CardType.FREEZE, deck.getDeck().peek().getType());
    }

    public void testDraw()
    {
        Deck deck = new Deck(BrawlCharacter.GERTRUDE);
        deck.shuffle();

        deck.draw();
        deck.draw();
        deck.draw();

        assertEquals(32, deck.getDeck().size());

        for (int i = 0; i < 32; i++)
        {
            deck.draw();
        }
        assertTrue(deck.isEmpty());
    }

    public void testGetDeck()
    {
        Deck deck = new Deck(BrawlCharacter.MILTON);
        deck.shuffle();

        assertEquals(35, deck.getDeck().size());
    }


    public void testGenerateDeck()
    {
        Deck deck = new Deck(BrawlCharacter.BEATRICE);
        deck.generateDeck("RHi GHi BHi RH2 BBl CFr CCl CBa GBl");

        deck.draw();
        assertEquals(new Card(Color.GREEN, CardType.BLOCK), deck.popDiscard());
        deck.draw();
        assertEquals(new Card(Color.COLORLESS, CardType.BASE), deck.popDiscard());
        deck.draw();
        assertEquals(new Card(Color.COLORLESS, CardType.CLEAR), deck.popDiscard());
        deck.draw();
        assertEquals(new Card(Color.COLORLESS, CardType.FREEZE), deck.popDiscard());
        deck.draw();
        assertEquals(new Card(Color.BLUE, CardType.BLOCK), deck.popDiscard());
        deck.draw();
        assertEquals(new Card(Color.RED, CardType.HIT2), deck.popDiscard());
        deck.draw();
        assertEquals(new Card(Color.BLUE, CardType.HIT), deck.popDiscard());
        deck.draw();
        assertEquals(new Card(Color.GREEN, CardType.HIT), deck.popDiscard());
        deck.draw();
        assertEquals(new Card(Color.RED, CardType.HIT), deck.popDiscard());
    }
}
