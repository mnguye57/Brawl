package brawl.utils;

import brawl.model.enums.CardType;
import brawl.model.enums.Color;
import brawl.model.Card;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;



/**
 *
 * @author Zander
 */
public class MoveValidatorTest extends TestCase {

    public MoveValidatorTest(String testName) {
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
        Card rh = mock(Card.class);//new Card(Color.RED, CardType.HIT);
        Card bh = mock(Card.class);//(Color.BLUE, CardType.HIT);
        Card rh2 = mock(Card.class);//(Color.RED, CardType.HIT2);
        Card gh2 = mock(Card.class);//(Color.GREEN, CardType.HIT2);
        Card base = mock(Card.class);//(Color.COLORLESS, CardType.BASE);
        Card gb = mock(Card.class);//(Color.GREEN, CardType.BLOCK);
        Card rb = mock(Card.class);//(Color.RED, CardType.BLOCK);
        Card p = mock(Card.class);//(Color.COLORLESS, CardType.PRESS);

        when(rh.getColor()).thenReturn(Color.RED);
        when(rh.getType()).thenReturn(CardType.HIT);

        when(bh.getColor()).thenReturn(Color.BLUE);
        when(bh.getType()).thenReturn(CardType.HIT);

        when(rh2.getColor()).thenReturn(Color.RED);
        when(rh2.getType()).thenReturn(CardType.HIT2);

        when(gh2.getColor()).thenReturn(Color.GREEN);
        when(gh2.getType()).thenReturn(CardType.HIT2);

        when(base.getColor()).thenReturn(Color.COLORLESS);
        when(base.getType()).thenReturn(CardType.BASE);

        when(gb.getColor()).thenReturn(Color.GREEN);
        when(gb.getType()).thenReturn(CardType.BLOCK);

        when(rb.getColor()).thenReturn(Color.RED);
        when(rb.getType()).thenReturn(CardType.BLOCK);

        when(p.getColor()).thenReturn(Color.COLORLESS);
        when(p.getType()).thenReturn(CardType.PRESS);

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
        when(p.getColor()).thenReturn(Color.GREEN);
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
