package brawl.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.CardType;
import brawl.model.enums.Color;
import junit.framework.TestCase;

/**
 *
 * @author Stephen
 */
public class CardTest extends TestCase {
    
    public CardTest(String testName) {
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

    public void testGetImagePath()
    {
        Card card1 = new Card(Color.RED, CardType.HIT);
        Card card2 = new Card(Color.BLUE, CardType.FREEZE);
        Card card3 = new Card(Color.RED, CardType.BASE);
        Card card4 = new Card(Color.BLUE, CardType.HIT2);
        Card card5 = new Card(Color.GREEN, CardType.BLOCK);
        Card card6 = new Card(Color.GREEN, CardType.PRESS);
        Card card7 = new Card(Color.GREEN, CardType.CLEAR);
        assertEquals("resource/RedHitCard.png",card1.getImagePath(BrawlCharacter.HUMPHREY));
        assertEquals("resource/FreezeCard.png",card2.getImagePath(BrawlCharacter.HUMPHREY));
        assertEquals("resource/humphreyBaseCard.png",card3.getImagePath(BrawlCharacter.HUMPHREY));
        assertEquals("resource/BlueHit2Card.png",card4.getImagePath(BrawlCharacter.HUMPHREY));
        assertEquals("resource/GreenBlockCard.png",card5.getImagePath(BrawlCharacter.HUMPHREY));
        assertEquals("resource/PressCard.png",card6.getImagePath(BrawlCharacter.HUMPHREY));
        assertEquals("resource/ClearCard.png",card7.getImagePath(BrawlCharacter.HUMPHREY));
    }

    public void testToString()
    {
        Card c1 = new Card(Color.RED, CardType.HIT);
        Card c2 = new Card(Color.BLUE, CardType.HIT2);
        Card c3 = new Card(Color.GREEN, CardType.BASE);
        Card c4 = new Card(Color.COLORLESS, CardType.BLOCK);
        Card c5 = new Card(Color.RED, CardType.CLEAR);
        Card c6 = new Card(Color.BLUE, CardType.FREEZE);
        Card c7 = new Card(Color.GREEN, CardType.PRESS);
        assertEquals("RHi", c1.toString());
        assertEquals("BH2", c2.toString());
        assertEquals("GBa", c3.toString());
        assertEquals("CBl", c4.toString());
        assertEquals("RCl", c5.toString());
        assertEquals("BFr", c6.toString());
        assertEquals("GPr", c7.toString());
    }

    public void testParseCard()
    {
        Card c1 = new Card(Color.RED, CardType.HIT);
        Card c2 = new Card(Color.BLUE, CardType.HIT2);
        Card c3 = new Card(Color.GREEN, CardType.BASE);
        Card c4 = new Card(Color.COLORLESS, CardType.BLOCK);
        Card c5 = new Card(Color.RED, CardType.CLEAR);
        Card c6 = new Card(Color.BLUE, CardType.FREEZE);
        Card c7 = new Card(Color.GREEN, CardType.PRESS);
        assertEquals(c1, Card.parseCard("RHi"));
        assertEquals(c2, Card.parseCard("BH2"));
        assertEquals(c3, Card.parseCard("GBa"));
        assertEquals(c4, Card.parseCard("CBl"));
        assertEquals(c5, Card.parseCard("RCl"));
        assertEquals(c6, Card.parseCard("BFr"));
        assertEquals(c7, Card.parseCard("GPr"));
    }
}
