package brawl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import brawl.model.AI;
import brawl.model.Card;
import brawl.model.Human;
import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.CardType;
import brawl.model.enums.Color;
import brawl.model.enums.GameMode;
import brawl.model.enums.PlayerID;
import java.awt.Button;
import java.awt.event.KeyEvent;
import junit.framework.TestCase;

/**
 *
 * @author Stephen
 */
public class MatchModelIntTest extends TestCase {
    
    public MatchModelIntTest(String testName) {
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
        AI p1 = new AI(BrawlCharacter.MILTON, PlayerID.PLAYER1, 2000);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        assertEquals(2, model.getColumns().size());
        assertEquals(34, model.getPlayer(PlayerID.PLAYER1).getDeck().getDeck().size());
        assertEquals(34, model.getPlayer(PlayerID.PLAYER2).getDeck().getDeck().size());
        assertEquals(GameMode.TOURNAMENT, model.getGameMode());
        assertEquals(null, model.getTurn());
    }

    public void testDiscard()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        AI p2 = new AI(BrawlCharacter.MILTON, PlayerID.PLAYER2, 2000);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        model.drawCard(PlayerID.PLAYER1);
        // look for a hit card
        while (model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.HIT)
        {
            model.drawCard(PlayerID.PLAYER1);
        }
        Card card = model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard();
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(card, model.getColumns().get(0).peekTopCard(PlayerID.PLAYER1));
    }

    public void testGameOver()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        assertFalse(model.isGameOver());
        model.getColumns().get(0).freeze();
        model.getColumns().get(1).freeze();
        assertTrue(model.isGameOver());
    }

    public void testDraw()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        Card temp = p1.getDeck().getDeck().peek();
        model.drawCard(PlayerID.PLAYER1);
        assertEquals(temp,p1.getDeck().peekDiscard());
        temp = p2.getDeck().getDeck().peek();
        model.drawCard(PlayerID.PLAYER2);
        assertEquals(temp, p2.getDeck().peekDiscard());
    }

    public void testAddDeleteColumn()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.addColumn(PlayerID.PLAYER1, 2);
        assertEquals(3, model.getColumns().size());
        model.clearColumn(0);
        assertEquals(2, model.getColumns().size());
    }
 
    public void testWinner()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.addColumn(PlayerID.PLAYER1, 2);
        assertEquals(PlayerID.PLAYER1, model.getWinner());

        model.clearColumn(2);
        assertFalse(PlayerID.PLAYER1.equals(model.getWinner()));

        model.clearColumn(0);
        assertEquals(PlayerID.PLAYER2, model.getWinner());

    }

    public void testSwitchTurn()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TRAINING);
        assertEquals(PlayerID.PLAYER1, model.getTurn());
        model.switchTurns();
        assertEquals(PlayerID.PLAYER2, model.getTurn());
    }

    public void testPressCardBug404()
    {
        Human p1 = new Human(BrawlCharacter.HUMPHREY, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        model.drawCard(PlayerID.PLAYER1);
        // look for a hit card
        model.getPlayer(PlayerID.PLAYER1).getDeck().generateDeck("RHi");
        model.drawCard(PlayerID.PLAYER1);

        Card hit = model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard();
        // play the hit card
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);

        model.drawCard(PlayerID.PLAYER1);
        // look for a block card of the same color as the hit card
        model.getPlayer(PlayerID.PLAYER1).getDeck().generateDeck("RBl");
        model.drawCard(PlayerID.PLAYER1);

        Card card = model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard();
        // Play the block card
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        // legal move - should have been played
        assertEquals(CardType.BLOCK, model.getColumns().get(0).getP1Stack().peek().getType());
        model.drawCard(PlayerID.PLAYER1);

        model.getPlayer(PlayerID.PLAYER1).getDeck().generateDeck("CPr");
        model.drawCard(PlayerID.PLAYER1);

        System.out.println("Current card: " + card.toString());
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        
        assertEquals(CardType.PRESS, model.getColumns().get(0).getP1Stack().peek().getType());
        assertEquals(card.getColor(), model.getColumns().get(0).getP1Stack().peek().getColor());
    }

    public void testNewBase()
    {
        Human p1 = new Human(BrawlCharacter.HUMPHREY, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        model.drawCard(PlayerID.PLAYER1);
        while(model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.BASE)
        {
            model.drawCard(PlayerID.PLAYER1);
        }
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(3, model.getColumns().size());
    }

    public void testClearCard()
    {
        Human p1 = new Human(BrawlCharacter.HUMPHREY, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        p1.getDeck().generateDeck("CCl CBa CBa CCl");
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(1, model.getColumns().size());

        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 2);
        assertEquals(2, model.getColumns().size());

        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(3, model.getColumns().size());
    }

    // cannot play a hit2 card as the first move
    public void testHit2Card()
    {
        Human p1 = new Human(BrawlCharacter.WILBUR, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        model.drawCard(PlayerID.PLAYER1);
        while (model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.HIT2)
        {
            model.drawCard(PlayerID.PLAYER1);
        }
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertTrue(model.getColumns().get(0).getP1Stack().isEmpty());
    }

    // should not have more than 3 bases on the board
    public void testMoreBases()
    {
        Human p1 = new Human(BrawlCharacter.WILBUR, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        assertEquals(2, model.getColumns().size());
        model.drawCard(PlayerID.PLAYER1);
        while (model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.BASE)
        {
            model.drawCard(PlayerID.PLAYER1);
        }
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(3, model.getColumns().size());
        
        model.drawCard(PlayerID.PLAYER1);
        while (model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.BASE)
        {
            model.drawCard(PlayerID.PLAYER1);
        }
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(3, model.getColumns().size());
        
    }

    // test can't clear with 1 base
    public void testLessBases()
    {
        Human p1 = new Human(BrawlCharacter.WILBUR, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        assertEquals(2, model.getColumns().size());
        model.drawCard(PlayerID.PLAYER1);
        while (model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.CLEAR)
        {
            model.drawCard(PlayerID.PLAYER1);
        }
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(1, model.getColumns().size());

        model.drawCard(PlayerID.PLAYER1);
        while (model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.CLEAR)
        {
            model.drawCard(PlayerID.PLAYER1);
        }
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(1, model.getColumns().size());
    }

    public void testDoNotRemoveMiddleBase()
    {
        Human p1 = new Human(BrawlCharacter.WILBUR, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        assertEquals(2, model.getColumns().size());
        model.drawCard(PlayerID.PLAYER1);
        while (model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.BASE)
        {
            model.drawCard(PlayerID.PLAYER1);
        }

        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(3, model.getColumns().size());
        model.drawCard(PlayerID.PLAYER1);
        while (model.getPlayer(PlayerID.PLAYER1).getDeck().peekDiscard().getType() != CardType.CLEAR)
        {
            model.drawCard(PlayerID.PLAYER1);
        }
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 1);
        assertEquals(3, model.getColumns().size());
    }

    public void testTrainingMode()
    {
        Human p1 = new Human(BrawlCharacter.WILBUR, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TRAINING);

        assertEquals(2, model.getColumns().size());
        assertEquals(PlayerID.PLAYER1, model.getTurn());

        p1.getDeck().generateDeck("RHi");
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(PlayerID.PLAYER2, model.getTurn());

        p2.getDeck().generateDeck("RHi BBl");
        model.drawCard(PlayerID.PLAYER2);
        assertEquals(new Card(Color.BLUE, CardType.BLOCK),
                model.getPlayer(PlayerID.PLAYER2).getDeck().peekDiscard());

        // Test Defect 413
        model.drawCard(PlayerID.PLAYER2);
        assertEquals(new Card(Color.BLUE, CardType.BLOCK),
                model.getPlayer(PlayerID.PLAYER2).getDeck().peekDiscard());
        //test Pass
        PlayerID current = model.getTurn();
        model.pass(current);
        assertFalse(model.getTurn().equals(current));

    }

    public void testPlayNullPointerBug393()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 2);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER2, 2);
        model.playDiscard(PlayerID.PLAYER2, PlayerID.PLAYER1, 0);
        model.playDiscard(PlayerID.PLAYER2, PlayerID.PLAYER1, 2);
        model.playDiscard(PlayerID.PLAYER2, PlayerID.PLAYER2, 0);
        model.playDiscard(PlayerID.PLAYER2, PlayerID.PLAYER2, 2);
    }

    public void testSwitchTurnBug403()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TRAINING);
        assertEquals(PlayerID.PLAYER1, model.getTurn());
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(PlayerID.PLAYER1, model.getTurn());
    }

    public void testTrainingBug409()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TUTORIAL);
        assertEquals(PlayerID.PLAYER1, model.getTurn());
        Card temp = model.getPlayer(PlayerID.PLAYER2).getDeck().getDeck().peek();
        model.drawCard(PlayerID.PLAYER2);
        assertEquals(temp, model.getPlayer(PlayerID.PLAYER2).getDeck().getDeck().peek());
    }

    public void testClearFreezeBug412()
    {
        Human p1 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        p1.getDeck().generateDeck("CCl CFr");
        model.drawCard(PlayerID.PLAYER1);
        assertEquals(2, model.getColumns().size());
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(2, model.getColumns().size());
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(2, model.getColumns().size());
    }

    public void testPlayClearCaseTwo()
    {
        Human p1 = new Human(BrawlCharacter.WILBUR, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        p1.getDeck().generateDeck("CCl CBa CCl");
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 2);
        assertEquals(1, model.getColumns().size());
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 2);
        assertEquals(2, model.getColumns().size());
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        assertEquals(1, model.getColumns().size());
    }

    public void testValidateMoveCaseOne()
    {
        Human p1 = new Human(BrawlCharacter.WILBUR, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        p1.getDeck().generateDeck("CCl RHi RHi RHi CCl");
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 2);
        assertEquals(1, model.getColumns().size());

        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 1);
        assertEquals(1, model.getColumns().get(0).getP1Stack().size());
    }

    public void testBug467PressCard()
    {
        Human p1 = new Human(BrawlCharacter.WILBUR, PlayerID.PLAYER1, null);
        Human p2 = new Human(BrawlCharacter.MILTON, PlayerID.PLAYER2, null);
        MatchModel model = new MatchModel(p1, p2, GameMode.TOURNAMENT);

        p1.getDeck().generateDeck("CPr");
        model.drawCard(PlayerID.PLAYER1);
        model.playDiscard(PlayerID.PLAYER1, PlayerID.PLAYER1, 2);
        assertTrue(model.getColumns().get(1).getP1Stack().isEmpty());
    }
}
