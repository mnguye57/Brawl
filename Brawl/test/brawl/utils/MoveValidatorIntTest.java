package brawl.utils;

import brawl.model.Card;
import junit.framework.TestCase;
import brawl.model.enums.*;
 
/**
 *
 * @author Zander
 */
public class MoveValidatorIntTest extends TestCase {

    public MoveValidatorIntTest(String testName) {
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


    public void testIsValid() {
        Card rh = new Card(Color.RED, CardType.HIT);
        Card bh = new Card(Color.BLUE, CardType.HIT);
        Card rh2 = new Card(Color.RED, CardType.HIT2);
        Card gh2 = new Card(Color.GREEN, CardType.HIT2);
        Card base = new Card(Color.COLORLESS, CardType.BASE);
        Card gb = new Card(Color.GREEN, CardType.BLOCK);
        Card rb = new Card(Color.RED, CardType.BLOCK);
        Card p = new Card(Color.COLORLESS, CardType.PRESS);
       
        //Hit on Base
        assertTrue(MoveValidator.isValid(rh,base));
        //Hit on Hit, correct color
        assertTrue(MoveValidator.isValid(rh,rh));
        //Hit on Hit, incorrect color
        assertFalse(MoveValidator.isValid(bh,rh));
        //Hit on Hit2, correct color
        assertTrue(MoveValidator.isValid(rh, rh2));
        //Hit on Hit2, incorrect color
        assertFalse(MoveValidator.isValid(bh, gh2));
        //Hit on Block, non matching colors
        assertFalse(MoveValidator.isValid(rh, gb));
        //Hit on Block, matching colors
        assertFalse(MoveValidator.isValid(rh, rb));
        //Hit on Press - not allowed, becuase still colorless
        assertFalse(MoveValidator.isValid(bh, p));


        //Hit2 on Base
        assertFalse(MoveValidator.isValid(rh2, base));
        //Hit2 on Hit, correct color
        assertTrue(MoveValidator.isValid(rh2, rh));
        //Hit2 on Hit, incorrect color
        assertFalse(MoveValidator.isValid(rh2, gb));
        //Hit2 on Hit2, correct color
        assertTrue(MoveValidator.isValid(rh2, rh2));
        //Hit2 on Hit2, incorrect color
        assertFalse(MoveValidator.isValid(gh2, rh2));
        //Hit2 on Block, non matching colors
        assertFalse(MoveValidator.isValid(rh2, gb));
        //Hit2 on Block, matching colors
        assertFalse(MoveValidator.isValid(rh2, rb));
        //Hit2 on Press
        assertFalse(MoveValidator.isValid(gh2, p));


        //Block on Base
        assertFalse(MoveValidator.isValid(gb, base));
        //Block on Hit, correct color
        assertTrue(MoveValidator.isValid(rb, rh));
        //Block on Hit, incorrect color
        assertFalse(MoveValidator.isValid(gb, rh));
        //Block on Hit2, correct color
        assertTrue(MoveValidator.isValid(rb, rh2));
        //Block on Hit2, incorrect color
        assertFalse(MoveValidator.isValid(gb, rh2));
        //Block on Block, non matching colors
        assertFalse(MoveValidator.isValid(rb, gb));
        //Block on Block, matching colors
        assertFalse(MoveValidator.isValid(gb, gb));
        //Block on Press

        //Press on Base
        assertFalse(MoveValidator.isValid(p, base));
        //Press on Hit
        assertFalse(MoveValidator.isValid(p, bh));
        //Press on Hit2
        assertFalse(MoveValidator.isValid(p, gh2));
        //Press on Block
        assertTrue(MoveValidator.isValid(p, gb));
        //Press on Press
        assertFalse(MoveValidator.isValid(p, p));


    }

}
