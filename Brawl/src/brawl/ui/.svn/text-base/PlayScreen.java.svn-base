package brawl.ui;

import brawl.GameController;
import brawl.MatchModel;
import brawl.model.Card;
import brawl.model.Column;
import brawl.model.Move;
import brawl.model.Player;
import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.CardType;
import brawl.model.enums.Color;
import brawl.model.enums.GameMode;
import brawl.model.enums.PlayerID;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

/**
 * A PlayScreen represents the screen where the user plays on the game board.
 * @author Minh Nguyen
 */
public class PlayScreen extends JPanel
{
    private JLayeredPane bottomLeft1, bottomMiddle1, bottomRight1, topLeft1,
    topMiddle1, topRight1;
    private JLayeredPane bottomLeft2, bottomMiddle2, bottomRight2, topLeft2,
    topMiddle2, topRight2;
    private JLayeredPane deck1, discards1, deck2, discards2;
    private JPanel baseLeft1, baseMiddle1, baseRight1;
    private JPanel baseLeft2, baseMiddle2, baseRight2;
    private JPanel columnLeft1, columnMiddle1, columnRight1, columnLeft2,
    columnMiddle2, columnRight2;
    private JLabel playerOneLabel, playerTwoLabel;
    private JPanel cardsPanel1, cardsPanel2;
    private int discardsOffset1 = 1, discardsOffset2 = 1;
    private int discardLayerNdx1 = 0, discardLayerNdx2 = 0;
    private StackOfCards topLeftStack, topMiddleStack, topRightStack,
    bottomLeftStack, bottomMiddleStack, bottomRightStack, discardStack1,
    discardStack2;
    private final static int CARD_DIST_HGHT = 107, CARD_DIST_WIDTH = 160;
    private final static int LEFT_COLUMN = 0, MIDDLE_COLUMN = 1,
    RIGHT_COLUMN = 2;
    private final static int CLEAR = -2, CARD_LIMIT = 3, CARD_SEPARATE_DIST = 3,
    DISCARD_LIMIT = 2;
    private final static int TOP_START_POS = 31, START_WIDTH = 55;
    private final static int TURN_FONT_SIZE = 20;
    private MatchModel matchModel;
    private GameMode mode;
    private final static Dimension COLUMNDIM = new Dimension(213, 490),
    CARDSPANELDIM = new Dimension(640, 160);
    private final static java.awt.Color DIRT = new java.awt.Color(186, 116, 55),
    WINNER = new java.awt.Color(164, 255, 173),
    LOSER = new java.awt.Color(255, 145, 145);

    /**
     * Constructs a PlayScreen
     * @param ctrl the controller for the PlayScreen
     */
    public PlayScreen(GameController ctrl)
    {
        this.addKeyListener(ctrl);
    }

    /**
     * Sets the MatchModel that represents the board
     * @param matchMdl the MatchModel to set
     * @param mde the mode of the game
     */
    public void setMatchModel(MatchModel matchMdl, GameMode mde)
    {
        this.matchModel = matchMdl;
        this.mode = mde;
    }

    /**
     * Reset the board
     * exempted from line length
     */
    public void resetBoard()
    {
        topLeftStack = new StackOfCards();
        topMiddleStack = new StackOfCards();
        topRightStack = new StackOfCards();
        bottomLeftStack = new StackOfCards();
        bottomMiddleStack = new StackOfCards();
        bottomRightStack = new StackOfCards();
        discardStack1 = new StackOfCards();
        discardStack2 = new StackOfCards();

        /* Player 1 and Player 2 frames */
        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();

        /* Top stacks */
        topLeft1 = new JLayeredPane();
        topMiddle1 = new JLayeredPane();
        topRight1 = new JLayeredPane();

        /* Bottom stacks */
        bottomLeft1 = new JLayeredPane();
        bottomMiddle1 = new JLayeredPane();
        bottomRight1 = new JLayeredPane();

        /* the bases */
        baseLeft1 = new JPanel();
        baseMiddle1 = new JPanel();
        baseRight1 = new JPanel();

        /* "bases" holds columnLeft, columnMiddle, and columnRight */
        JPanel bases1 = new JPanel();

        /* columnLeft holds topLeft, bottomLeft, and baseLeft */
        columnLeft1 = new JPanel();

        /* columnMiddle holds topMiddle, bottomMiddle, and baseMiddle */
        columnMiddle1 = new JPanel();

        /* columnRight holds topRight, bottomRight, and baseRight */
        columnRight1 = new JPanel();

        /* cardsPanel holds the deck and discards */
        cardsPanel1 = new JPanel();
        deck1 = new JLayeredPane();
        discards1 = new JLayeredPane();

        baseLeft1.setBackground(DIRT);
        baseLeft1.setAlignmentX(CENTER_ALIGNMENT);
        baseMiddle1.setBackground(DIRT);
        baseMiddle1.setAlignmentX(CENTER_ALIGNMENT);
        baseRight1.setBackground(DIRT);
        baseRight1.setAlignmentX(CENTER_ALIGNMENT);

        removeAll();

        playerOneLabel = new JLabel(" ");
        playerOneLabel.setFont(new Font("Helvetica", Font.BOLD, TURN_FONT_SIZE));
        playerOneLabel.setForeground(java.awt.Color.WHITE);
        player1.add(playerOneLabel);
        player1.setLayout(new BoxLayout(player1, BoxLayout.PAGE_AXIS));
        playerOneLabel.setAlignmentX(CENTER_ALIGNMENT);
        player1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        player1.setBackground(DIRT);

        playerTwoLabel = new JLabel(" ");
        playerTwoLabel.setFont(new Font("Helvetica", Font.BOLD, TURN_FONT_SIZE));
        playerTwoLabel.setForeground(java.awt.Color.WHITE);
        playerTwoLabel.setAlignmentX(CENTER_ALIGNMENT);
        player2.setLayout(new BoxLayout(player2, BoxLayout.PAGE_AXIS));
        player2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        player2.add(playerTwoLabel);
        player2.setBackground(DIRT);

        setTurn(PlayerID.PLAYER1);

        columnLeft1.setLayout(new BoxLayout(columnLeft1, BoxLayout.PAGE_AXIS));
        columnLeft1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        columnLeft1.setPreferredSize(COLUMNDIM);
        columnLeft1.setMinimumSize(COLUMNDIM);
        columnLeft1.setMaximumSize(COLUMNDIM);

        columnMiddle1.setLayout(new BoxLayout(columnMiddle1,
            BoxLayout.PAGE_AXIS));
        columnMiddle1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        columnMiddle1.setPreferredSize(COLUMNDIM);
        columnMiddle1.setMinimumSize(COLUMNDIM);
        columnMiddle1.setMaximumSize(COLUMNDIM);

        columnRight1.setLayout(new BoxLayout(columnRight1,
            BoxLayout.PAGE_AXIS));
        columnRight1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        columnRight1.setPreferredSize(COLUMNDIM);
        columnRight1.setMinimumSize(COLUMNDIM);
        columnRight1.setMaximumSize(COLUMNDIM);

        cardsPanel1.setLayout(new BoxLayout(cardsPanel1, BoxLayout.LINE_AXIS));
        cardsPanel1.add(deck1);
        cardsPanel1.add(discards1);
        cardsPanel1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        cardsPanel1.setBackground(DIRT);
        cardsPanel1.setPreferredSize(CARDSPANELDIM);
        cardsPanel1.setMinimumSize(CARDSPANELDIM);
        cardsPanel1.setMaximumSize(CARDSPANELDIM);

        columnLeft1.add(topLeft1);
        columnLeft1.add(baseLeft1);
        columnLeft1.add(bottomLeft1);
        columnMiddle1.add(topMiddle1);
        columnMiddle1.add(baseMiddle1);
        columnMiddle1.add(bottomMiddle1);
        columnRight1.add(topRight1);
        columnRight1.add(baseRight1);
        columnRight1.add(bottomRight1);
        columnLeft1.setBackground(WINNER);
        columnMiddle1.setBackground(DIRT);
        columnRight1.setBackground(LOSER);

        bases1.setLayout(new BoxLayout(bases1, BoxLayout.LINE_AXIS));
        bases1.add(columnLeft1);
        bases1.add(columnMiddle1);
        bases1.add(columnRight1);
        /*
         * New components since Swing does not allow you to add the same components
         * to multiple containers.
         */

        topLeft2 = new JLayeredPane();
        topMiddle2 = new JLayeredPane();
        topRight2 = new JLayeredPane();
        bottomLeft2 = new JLayeredPane();
        bottomMiddle2 = new JLayeredPane();
        bottomRight2 = new JLayeredPane();
        baseLeft2 = new JPanel();
        baseMiddle2 = new JPanel();
        baseRight2 = new JPanel();
        columnLeft2 = new JPanel();
        columnMiddle2 = new JPanel();
        columnRight2 = new JPanel();
        JPanel bases2 = new JPanel();
        cardsPanel2 = new JPanel();
        deck2 = new JLayeredPane();
        discards2 = new JLayeredPane();
        baseLeft2.setBackground(DIRT);
        baseLeft2.setAlignmentX(Component.CENTER_ALIGNMENT);
        baseMiddle2.setBackground(DIRT);
        baseMiddle2.setAlignmentX(CENTER_ALIGNMENT);
        baseRight2.setBackground(DIRT);
        baseRight2.setAlignmentX(CENTER_ALIGNMENT);


        columnLeft2.setLayout(new BoxLayout(columnLeft2, BoxLayout.PAGE_AXIS));
        columnLeft2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        columnLeft2.setPreferredSize(COLUMNDIM);
        columnLeft2.setMinimumSize(COLUMNDIM);
        columnLeft2.setMaximumSize(COLUMNDIM);

        columnMiddle2.setLayout(new BoxLayout(columnMiddle2,
            BoxLayout.PAGE_AXIS));
        columnMiddle2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        columnMiddle2.setPreferredSize(COLUMNDIM);
        columnMiddle2.setMinimumSize(COLUMNDIM);
        columnMiddle2.setMaximumSize(COLUMNDIM);

        columnRight2.setLayout(new BoxLayout(columnRight2,
            BoxLayout.PAGE_AXIS));
        columnRight2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        columnRight2.setPreferredSize(COLUMNDIM);
        columnRight2.setMinimumSize(COLUMNDIM);
        columnRight2.setMaximumSize(COLUMNDIM);

        columnLeft2.add(topLeft2);
        columnLeft2.add(baseLeft2);
        columnLeft2.add(bottomLeft2);
        columnMiddle2.add(topMiddle2);
        columnMiddle2.add(baseMiddle2);
        columnMiddle2.add(bottomMiddle2);
        columnRight2.add(topRight2);
        columnRight2.add(baseRight2);
        columnRight2.add(bottomRight2);

        columnLeft2.setBackground(WINNER);
        columnMiddle2.setBackground(DIRT);
        columnRight2.setBackground(LOSER);

        bases2.setLayout(new BoxLayout(bases2, BoxLayout.LINE_AXIS));
        bases2.add(columnLeft2);
        bases2.add(columnMiddle2);
        bases2.add(columnRight2);

        cardsPanel2.setLayout(new BoxLayout(cardsPanel2, BoxLayout.LINE_AXIS));
        cardsPanel2.add(deck2);
        cardsPanel2.add(discards2);
        cardsPanel2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        cardsPanel2.setBackground(DIRT);
        cardsPanel2.setPreferredSize(CARDSPANELDIM);
        cardsPanel2.setMinimumSize(CARDSPANELDIM);
        cardsPanel2.setMaximumSize(CARDSPANELDIM);

        player1.add(bases1);
        player1.add(cardsPanel1);

        player2.add(bases2);
        player2.add(cardsPanel2);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(player1);
        this.add(player2);


        //Add images for decks and bases. Had difficulty getting right size
//        //and positioning
        deck1.add(createImageLabel("resource/backOfCard.png"));
        deck1.setLayout(new BoxLayout(deck1, BoxLayout.PAGE_AXIS));

        deck2.add(createImageLabel("resource/backOfCard.png"));
        deck2.setLayout(new BoxLayout(deck2, BoxLayout.PAGE_AXIS));

        BrawlCharacter bc = matchModel.getPlayer(PlayerID.PLAYER1)
            .getDeck().getCharacter();
        BrawlCharacter bc2 = matchModel.getPlayer(PlayerID.PLAYER2)
            .getDeck().getCharacter();

        Card c1 = new Card(Color.COLORLESS, CardType.BASE);
        Card c2 = new Card(Color.COLORLESS, CardType.BASE);

        String c1src = c1.getImagePath(bc);
        String c2src = c2.getImagePath(bc2);

        baseLeft1.add(createBaseImageLabel(c1src));
        baseLeft1.setLayout(new BoxLayout(baseLeft1, BoxLayout.PAGE_AXIS));

        baseLeft2.add(createBaseImageLabel(c2src));
        baseLeft2.setLayout(new BoxLayout(baseLeft2, BoxLayout.PAGE_AXIS));

        baseRight1.add(createBaseImageLabel(c2src));
        baseRight1.setLayout(new BoxLayout(baseRight1, BoxLayout.PAGE_AXIS));

        baseRight2.add(createBaseImageLabel(c1src));
        baseRight2.setLayout(new BoxLayout(baseRight2, BoxLayout.PAGE_AXIS));
    }

    /**
     * Updates the turn indicator text.
     * @param player the player's text to update
     */
    public void setTurn(PlayerID player)
    {
        if (mode == GameMode.TRAINING)
        {
            if (player == PlayerID.PLAYER1)
            {
                playerOneLabel.setText("Your Turn");
                playerTwoLabel.setText(" ");
            }
            else if (player == PlayerID.PLAYER2)
            {
                playerTwoLabel.setText("Your Turn");
                playerOneLabel.setText(" ");
            }
            playerOneLabel.revalidate();
            playerTwoLabel.revalidate();
        }
    }

    /**
     * Blinks a panel to give feedback that an invalid key was pressed
     * @param player the player who attempted the move.
     */
    public void blinkInvalid(PlayerID player)
    {
        final int delay = 300;
        if (player == PlayerID.PLAYER1)
        {
            cardsPanel1.setBackground(java.awt.Color.WHITE);
            cardsPanel1.revalidate();

            ActionListener task = new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    cardsPanel1.setBackground(DIRT);
                    cardsPanel1.revalidate();
                }
            };
            Timer timer = new Timer(delay, task);
            timer.setRepeats(false);
            timer.start();
            
        }
        else if (player == PlayerID.PLAYER2)
        {
            cardsPanel2.setBackground(java.awt.Color.WHITE);
            cardsPanel2.revalidate();
            ActionListener task = new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    cardsPanel2.setBackground(DIRT);
                    cardsPanel2.revalidate();
                }
            };
            Timer timer = new Timer(delay, task);
            timer.setRepeats(false);
            timer.start();
        }
    }

    private JLabel createBaseImageLabel(String source)
    {
        JLabel result = null;
        try
        {
            BufferedImage chosen = ImageIO.read(getClass()
                .getClassLoader().getResource(source));
            BufferedImage chosen2 = rotateImage(chosen);
            BufferedImage chosen3 = chosen2.getSubimage(chosen.getWidth(),
                chosen.getHeight()-chosen.getWidth(), chosen.getHeight(),
                chosen.getWidth());
            result = new JLabel(new ImageIcon(chosen3));
        }
        catch (Exception e)
        {

            System.out.println("Couldn't find the file.");
        }

        return result;
    }

    private JLabel createImageLabel(String source)
    {
        JLabel result = null;
        try
        {
            BufferedImage chosen = ImageIO.read(getClass().getClassLoader()
            .getResource(source));
            result = new JLabel(new ImageIcon(chosen));
        }
        catch (Exception e)
        {

            System.out.println("Couldn't find the file.");
        }

        return result;
    }

    private BufferedImage rotateImage(BufferedImage image)
    {
        AffineTransform trans = new AffineTransform();
        trans.rotate(Math.PI / 2.0, image.getWidth(), image.getHeight());
        AffineTransformOp transOp = new AffineTransformOp(trans,
            AffineTransformOp.TYPE_BILINEAR);
        return transOp.filter(image, null);
    }

    /**
     * Perform a move made by the user
     * @param newMove the move to make
     */
    public void makeMove(Move newMove)
    {
        PlayerID source = newMove.getSource();
        Player player = matchModel.getPlayer(source);

        if (newMove.isDraw())
        {
            if (source == PlayerID.PLAYER1)
            {
                if (player.getDeck().isEmpty())
                {
                    deck1.removeAll();
                }
                updateDiscard(newMove);
            }
            else
            {
                if (player.getDeck().isEmpty())
                {
                    deck2.removeAll();
                }
                updateDiscard(newMove);
            }
        }
        else
        {
            if (source == PlayerID.PLAYER1)
            {
                discardStack1.remove(discardStack1.size()-1);
                repaintDiscard1();
            }
            else
            {
                discardStack2.remove(discardStack2.size()-1);
                repaintDiscard2();
            }

            playCard(newMove);
        }
    }

    private void repaintDiscard1()
    {
        discardsOffset1 = 1;
        discardLayerNdx1 = 0;
        discards1.removeAll();
        JLabel cd;

        for (int ndx = 0; ndx < discardStack1.size(); ndx++)
        {
            if (ndx >= discardStack1.size()-DISCARD_LIMIT)
            {
                cd = discardStack1.get(ndx);
                addDiscard1(cd);
            }
        }
        if (discardStack1.size() == 1 && discards1.highestLayer() == 1)
        {
            discards1.remove(1);
        }
        discards1.revalidate();
        discards1.repaint();
    }

    private void repaintDiscard2()
    {
        discardsOffset2 = 1;
        discardLayerNdx2 = 0;
        discards2.removeAll();
        JLabel cd;

        for (int ndx = 0; ndx < discardStack2.size(); ndx++)
        {
            if (ndx >= discardStack2.size()-DISCARD_LIMIT)
            {
                cd = discardStack2.get(ndx);
                addDiscard2(cd);
            }
        }
        if (discardStack2.size() == 1 && discards2.highestLayer() == 1)
        {
            discards2.remove(1);
        }
        discards2.revalidate();
        discards2.repaint();
    }

    private void addDiscard1(JLabel card)
    {
        card.setLocation((discardsOffset1++) * (CARD_DIST_HGHT/CARD_SEPARATE_DIST)
            , 0);
        card.setSize(CARD_DIST_HGHT, CARD_DIST_WIDTH);
        discards1.add(card, new Integer(discardLayerNdx1++));
        discards1.revalidate();
    }

    private void addDiscard2(JLabel card)
    {
        card.setLocation((discardsOffset2++) * (CARD_DIST_HGHT/CARD_SEPARATE_DIST)
            , 0);
        card.setSize(CARD_DIST_HGHT, CARD_DIST_WIDTH);
        discards2.add(card, new Integer(discardLayerNdx2++));
        discards2.revalidate();
    }

    /**
     * Update the discard pile in the UI
     * @param move the move the user made
     */
    private void updateDiscard(Move move)
    {
        PlayerID source = move.getSource();
        Player player = matchModel.getPlayer(source);
        BrawlCharacter bc = player.getDeck().getCharacter();

        if (player.getDeck().peekDiscard() != null)
        {
            String discardCard = player.getDeck().peekDiscard().getImagePath(bc);
            try
            {
                BufferedImage cardImg = ImageIO.read(getClass().getClassLoader()
                .getResource(discardCard));
                JLabel card = new JLabel(new ImageIcon(cardImg));
                if (source == PlayerID.PLAYER1)
                {
                    discardStack1.add(card);
                    repaintDiscard1();
                }
                else if (source == PlayerID.PLAYER2)
                {
                    discardStack2.add(card);
                    repaintDiscard2();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Couldn't find the file: " + discardCard);
            }
        }
    }

    /**
     * Plays a card and updates the UI
     * @param move the move made by the user
     */
    private void playCard(Move move)
    {
        ArrayList<Column> columns = matchModel.getColumns();
        int colNdx = move.getColumn();

        if (colNdx == CLEAR)
        {
            repaintColumns();
        }
        else
        {
            Column col = columns.get(colNdx);

            PlayerID side = move.getSide();
            BrawlCharacter bc = matchModel.getPlayer(side).getDeck()
            .getCharacter();

            Card topCard = col.peekTopCard(side);

            if (topCard.getType() == CardType.BASE)
            {
                repaintColumns();
            }
            else if (col.isFrozen())
            {
                colNdx = convertColNdx(columns.size(), colNdx);

                if (colNdx == 0)
                {
                    freezeLeft(bc);
                }
                else if (colNdx == 1)
                {
                    freezeMiddle(bc);
                }
                else if (colNdx == 2)
                {
                    freezeRight(bc);
                }

                repaint();
            }
            else
            {
                playNormalCard(move);
            }
        }
    }

    private void freezeLeft(BrawlCharacter bc)
    {
        bottomLeft1.removeAll();
        topLeft1.removeAll();
        topRight2.removeAll();
        bottomRight2.removeAll();
        baseLeft1.removeAll();
        baseRight2.removeAll();


        Card c1 = new Card(Color.COLORLESS, CardType.FREEZE);

        String c1src = c1.getImagePath(bc);

        baseLeft1.add(createImageLabel(c1src));
        baseRight2.add(createImageLabel(c1src));

        repaint();
    }

    private void freezeMiddle(BrawlCharacter bc)
    {
        bottomMiddle1.removeAll();
        topMiddle1.removeAll();
        bottomMiddle2.removeAll();
        topMiddle2.removeAll();
        baseMiddle1.removeAll();
        baseMiddle2.removeAll();

        Card c1 = new Card(Color.COLORLESS, CardType.FREEZE);

        String c1src = c1.getImagePath(bc);

        baseMiddle1.add(createImageLabel(c1src));
        baseMiddle2.add(createImageLabel(c1src));

        repaint();
    }

    private void freezeRight(BrawlCharacter bc)
    {
        bottomRight1.removeAll();
        topRight1.removeAll();
        bottomLeft2.removeAll();
        topLeft2.removeAll();
        baseRight1.removeAll();
        baseLeft2.removeAll();

        Card c1 = new Card(Color.COLORLESS, CardType.FREEZE);

        String c1src = c1.getImagePath(bc);

        baseRight1.add(createImageLabel(c1src));
        baseLeft2.add(createImageLabel(c1src));

        repaint();
    }

    private int convertColNdx(int size, int colNdx)
    {
        int result = colNdx;

        switch(size)
        {
            case 1:
                if (colNdx == 0)
                {
                    result = 1;
                }

                break;
            case 2:
                if (colNdx == 1)
                {
                    result = 2;
                }

                break;

            default:
                break;
        }

        return result;
    }

    private void addBottomStack(JLayeredPane panel1, JLayeredPane panel2,
        JLabel card1, JLabel card2, StackOfCards cards)
    {
        card1.setLocation(START_WIDTH, cards.getOffset1()
                * (CARD_DIST_HGHT / CARD_SEPARATE_DIST));
        card1.setSize(CARD_DIST_HGHT, CARD_DIST_WIDTH);
        panel1.add(card1, new Integer(cards.getLayerNdx1()));

        card2.setLocation(START_WIDTH, TOP_START_POS + ((cards.getOffset2())
                * (-CARD_DIST_HGHT / CARD_SEPARATE_DIST)));
        card2.setSize(CARD_DIST_HGHT, CARD_DIST_WIDTH);
        panel2.add(card2, new Integer(cards.getLayerNdx2()));

        panel1.revalidate();
        panel2.revalidate();
    }

    private void playBottomLeft(JLabel card1, JLabel card2)
    {
        if (addCard(bottomLeftStack, card1))
        {
            addBottomStack(bottomLeft1, topRight2, card1, card2, bottomLeftStack);
        }
        else
        {
            bottomLeftStack.reset();

            bottomLeft1.removeAll();
            topRight2.removeAll();

            for (JLabel card : bottomLeftStack)
            {
                addBottomStack(bottomLeft1, topRight2, card,
                    new JLabel(card.getIcon()), bottomLeftStack);
            }
        }
    }

    private void playBottomMiddle(JLabel card1, JLabel card2)
    {
        if (addCard(bottomMiddleStack, card1))
        {
            addBottomStack(bottomMiddle1, topMiddle2, card1, card2, bottomMiddleStack);
        }
        else
        {
            bottomMiddleStack.reset();

            bottomMiddle1.removeAll();
            topMiddle2.removeAll();

            for (JLabel card : bottomMiddleStack)
            {
                addBottomStack(bottomMiddle1, topMiddle2, card,
                    new JLabel(card.getIcon()), bottomMiddleStack);
            }
        }
    }

    private void playBottomRight(JLabel card1, JLabel card2)
    {
        if (addCard(bottomRightStack, card1))
        {
            addBottomStack(bottomRight1, topLeft2, card1, card2, bottomRightStack);
        }
        else
        {
            bottomRightStack.reset();

            bottomRight1.removeAll();
            topLeft2.removeAll();

            for (JLabel card : bottomRightStack)
            {
                addBottomStack(bottomRight1, topLeft2, card,
                    new JLabel(card.getIcon()), bottomRightStack);
            }
        }
    }

    private void playPlayer1Card(Column col, int colNdx,
        JLabel card1, JLabel card2)
    {
        if (colNdx == 0)
        {
            setColColor(col, columnLeft1, columnRight2);
            playBottomLeft(card1, card2);
        }
        else if (colNdx == 1)
        {
            setColColor(col, columnMiddle1, columnMiddle2);
            playBottomMiddle(card1, card2);
        }
        else if (colNdx == 2)
        {
            setColColor(col, columnRight1, columnLeft2);
            playBottomRight(card1, card2);
        }
    }

    private void addTopStack(JLayeredPane panel1, JLayeredPane panel2,
        JLabel card1, JLabel card2, StackOfCards cards)
    {
        card1.setLocation(START_WIDTH, TOP_START_POS + ((cards.getOffset1()
                * (-CARD_DIST_HGHT / CARD_SEPARATE_DIST))));
        card1.setSize(CARD_DIST_HGHT, CARD_DIST_WIDTH);
        panel1.add(card1, new Integer(cards.getLayerNdx1()));

        card2.setLocation(START_WIDTH, cards.getOffset2()
                * (CARD_DIST_HGHT / CARD_SEPARATE_DIST));
        card2.setSize(CARD_DIST_HGHT, CARD_DIST_WIDTH);
        panel2.add(card2, new Integer(cards.getLayerNdx2()));

        panel1.revalidate();
        panel2.revalidate();
    }

    private void playTopLeft(JLabel card1, JLabel card2)
    {
        if (addCard(topLeftStack, card1))
        {
            addTopStack(topLeft1, bottomRight2, card1, card2, topLeftStack);
        }
        else
        {
            topLeftStack.reset();

            topLeft1.removeAll();
            bottomRight2.removeAll();

            for (JLabel card : topLeftStack)
            {
                addTopStack(topLeft1, bottomRight2, card,
                    new JLabel(card.getIcon()), topLeftStack);
            }
        }
    }

    private void playTopMiddle(JLabel card1, JLabel card2)
    {
        if (addCard(topMiddleStack, card1))
        {
            addTopStack(topMiddle1, bottomMiddle2, card1, card2, topMiddleStack);
        }
        else
        {
            topMiddleStack.reset();

            topMiddle1.removeAll();
            bottomMiddle2.removeAll();

            for (JLabel card : topMiddleStack)
            {
                addTopStack(topMiddle1, bottomMiddle2, card,
                    new JLabel(card.getIcon()), topMiddleStack);
            }
        }
    }

    private void playTopRight(JLabel card1, JLabel card2)
    {
        if (addCard(topRightStack, card1))
        {
            addTopStack(topRight1, bottomLeft2, card1, card2, topRightStack);
        }
        else
        {
            topRightStack.reset();

            topRight1.removeAll();
            bottomLeft2.removeAll();

            for (JLabel card : topRightStack)
            {
                addTopStack(topRight1, bottomLeft2, card,
                    new JLabel(card.getIcon()), topRightStack);
            }
        }
    }
    private void playPlayer2Card(Column col, int colNdx,
        JLabel card1, JLabel card2)
    {
        if (colNdx == 0)
        {
            setColColor(col, columnLeft1, columnRight2);
            playTopLeft(card1, card2);
        }
        else if (colNdx == 1)
        {
            setColColor(col, columnMiddle1, columnMiddle2);
            playTopMiddle(card1, card2);
        }
        else if (colNdx == 2)
        {
            setColColor(col, columnRight1, columnLeft2);
            playTopRight(card1, card2);
        }
    }

    private void playNormalCard(Move move)
    {
        ArrayList<Column> columns = matchModel.getColumns();
        int colNdx = move.getColumn();
        Column col = columns.get(colNdx);

        colNdx = convertColNdx(columns.size(), colNdx);

        PlayerID side = move.getSide();
        PlayerID source = move.getSource();
        BrawlCharacter bc = matchModel.getPlayer(source).getDeck()
        .getCharacter();

        Card topCard = col.peekTopCard(side);
        String topCardSrc = topCard.getImagePath(bc);

        try
        {
            BufferedImage cardImg = ImageIO.read(getClass().getClassLoader()
            .getResource(topCardSrc));
            JLabel card1 = new JLabel(new ImageIcon(cardImg));
            JLabel card2 = new JLabel(new ImageIcon(cardImg));
            if (side == PlayerID.PLAYER2)
            {
                playPlayer2Card(col, colNdx, card1, card2);
            }
            else if (side == PlayerID.PLAYER1)
            {
                playPlayer1Card(col, colNdx, card1, card2);
            }
        }
        catch (Exception e)
        {
            System.out.println("Couldn't find the file: " + topCardSrc);
        }
    }

    private void paintBottom(Stack<Card> stack, StackOfCards cards,
        JLayeredPane panel1, JLayeredPane panel2)
    {
        Card cd;

        BrawlCharacter bc = matchModel.getPlayer(PlayerID.PLAYER1)
            .getDeck().getCharacter();
        for (int cardNdx = 0; cardNdx < stack.size(); cardNdx++)
        {
            if (cardNdx >= stack.size() - CARD_LIMIT)
            {
                cd = stack.get(cardNdx);
                String cardSrc = cd.getImagePath(bc);
                try
                {
                    BufferedImage cardImg = ImageIO.read(getClass()
                        .getClassLoader().getResource(cardSrc));
                    JLabel card1 = new JLabel(new ImageIcon(cardImg));
                    JLabel card2 = new JLabel(new ImageIcon(cardImg));
                    cards.add(card1);
                    addBottomStack(panel1, panel2, card1, card2, cards);
                }
                catch (Exception e)
                {
                    System.out.println("Couldn't find the file: " + cardSrc);
                }
            }
        }
    }

    private void paintTop(Stack<Card> stack, StackOfCards cards,
        JLayeredPane panel1, JLayeredPane panel2)
    {
        Card cd;

        BrawlCharacter bc = matchModel.getPlayer(PlayerID.PLAYER1)
            .getDeck().getCharacter();
        for (int cardNdx = 0; cardNdx < stack.size(); cardNdx++)
        {
            if (cardNdx >= stack.size() - CARD_LIMIT)
            {
                cd = stack.get(cardNdx);
                String cardSrc = cd.getImagePath(bc);
                try
                {
                    BufferedImage cardImg = ImageIO.read(getClass()
                        .getClassLoader().getResource(cardSrc));
                    JLabel card1 = new JLabel(new ImageIcon(cardImg));
                    JLabel card2 = new JLabel(new ImageIcon(cardImg));
                    cards.add(card1);
                    addTopStack(panel1, panel2, card1, card2, cards);
                }
                catch (Exception e)
                {
                    System.out.println("Couldn't find the file: " + cardSrc);
                }
            }
        }
    }

    private void paintMiddleColumn(Column col)
    {
        Stack<Card> stack1 = col.getP1Stack();
        Stack<Card> stack2 = col.getP2Stack();

        PlayerID owner = col.getBase().getOwner();
        BrawlCharacter bc = matchModel.getPlayer(owner).getDeck().getCharacter();

        if (col.isFrozen())
        {
            freezeMiddle(bc);
        }
        else
        {
            Card c1 = new Card(Color.COLORLESS, CardType.BASE);

            String c1src = c1.getImagePath(bc);

            baseMiddle1.add(createBaseImageLabel(c1src));
            baseMiddle1.setLayout(new BoxLayout(baseMiddle1,
                    BoxLayout.PAGE_AXIS));
            baseMiddle2.add(createBaseImageLabel(c1src));
            baseMiddle2.setLayout(new BoxLayout(baseMiddle2,
                    BoxLayout.PAGE_AXIS));

            Card cd;
            bottomMiddleStack.clear();
            // paint cards on player one's side
            paintBottom(stack1, bottomMiddleStack, bottomMiddle1, topMiddle2);

            topMiddleStack.clear();
            // paint cards on player two's side
            paintTop(stack2, topMiddleStack, topMiddle1, bottomMiddle2);
        }
    }

    private void paintLeftColumn(Column col)
    {
        PlayerID owner = col.getBase().getOwner();
        BrawlCharacter bc = matchModel.getPlayer(owner).getDeck().getCharacter();

        if (col.isFrozen())
        {
            freezeLeft(bc);
        }
        else
        {
            Card c1 = new Card(Color.COLORLESS, CardType.BASE);

            String c1src = c1.getImagePath(bc);
            Stack<Card> stack1 = col.getP1Stack();
            Stack<Card> stack2 = col.getP2Stack();
            baseLeft1.add(createBaseImageLabel(c1src));
            baseLeft1.setLayout(new BoxLayout(baseLeft1, BoxLayout.PAGE_AXIS));
            baseRight2.add(createBaseImageLabel(c1src));
            baseRight2.setLayout(new BoxLayout(baseRight2,
                    BoxLayout.PAGE_AXIS));

            bottomLeftStack.clear();
            paintBottom(stack1, bottomLeftStack, bottomLeft1, topRight2);

            topLeftStack.clear();
            // paint cards on player two's side
            paintTop(stack2, topLeftStack, topLeft1, bottomRight2);
        }
    }

    private void paintRightColumn(Column col)
    {
        PlayerID owner = col.getBase().getOwner();
        BrawlCharacter bc = matchModel.getPlayer(owner).getDeck()
        .getCharacter();

        if (col.isFrozen())
        {
            freezeRight(bc);
        }
        else
        {
            Card c1 = new Card(Color.COLORLESS, CardType.BASE);

            String c1src = c1.getImagePath(bc);
            Stack<Card> stack1 = col.getP1Stack();
            Stack<Card> stack2 = col.getP2Stack();
            baseRight1.add(createBaseImageLabel(c1src));
            baseRight1.setLayout(new BoxLayout(baseRight1, BoxLayout.PAGE_AXIS));
            baseLeft2.add(createBaseImageLabel(c1src));
            baseLeft2.setLayout(new BoxLayout(baseLeft2,
                    BoxLayout.PAGE_AXIS));

            bottomRightStack.clear();
            paintBottom(stack1, bottomRightStack, bottomRight1, topLeft2);

            topRightStack.clear();
            // paint cards on player two's side
            paintTop(stack2, topRightStack, topRight1, bottomLeft2);
        }

    }

    private void paintTwoColumns(ArrayList<Column> columns)
    {
        Column col;

        for (int colNdx = 0; colNdx < columns.size(); colNdx++)
        {
            col = columns.get(colNdx);

            if (colNdx == LEFT_COLUMN)
            {
                setColColor(col, columnLeft1, columnRight2);
                paintLeftColumn(col);
            }
            else if (colNdx == RIGHT_COLUMN-1)
            {
                setColColor(col, columnRight1, columnLeft2);
                paintRightColumn(col);
            }
        }
        columnMiddle1.setBackground(DIRT);
        columnMiddle2.setBackground(DIRT);
    }

    private void paintThreeColumns(ArrayList<Column> columns)
    {
        Column col;
        for (int colNdx = 0; colNdx < columns.size(); colNdx++)
        {
            col = columns.get(colNdx);

            if (colNdx == LEFT_COLUMN)
            {
                setColColor(col, columnLeft1, columnRight2);
                paintLeftColumn(col);
            }
            else if (colNdx == MIDDLE_COLUMN)
            {
                setColColor(col, columnMiddle1, columnMiddle2);
                paintMiddleColumn(col);
            }
            else if (colNdx == RIGHT_COLUMN)
            {
                setColColor(col, columnRight1, columnLeft2);
                paintRightColumn(col);
            }
        }

    }

    private void resetOffsets()
    {
        bottomLeftStack.reset();
        bottomMiddleStack.reset();
        bottomRightStack.reset();
        topLeftStack.reset();
        topMiddleStack.reset();
        topRightStack.reset();
    }

    private void repaintColumns()
    {
        resetOffsets();
        ArrayList<Column> columns = matchModel.getColumns();

        bottomLeft1.removeAll();
        bottomMiddle1.removeAll();
        bottomRight1.removeAll();
        topLeft1.removeAll();
        topMiddle1.removeAll();
        topRight1.removeAll();
        bottomLeft2.removeAll();
        bottomMiddle2.removeAll();
        bottomRight2.removeAll();
        topLeft2.removeAll();
        topMiddle2.removeAll();
        topRight2.removeAll();
        baseLeft1.removeAll();
        baseMiddle1.removeAll();
        baseRight1.removeAll();
        baseLeft2.removeAll();
        baseMiddle2.removeAll();
        baseRight2.removeAll();

        Column col;
        int colNdx = 0;

        if (columns.size() == LEFT_COLUMN + 1)
        {
            col = columns.get(colNdx);
            setColColor(col, columnMiddle1, columnMiddle2);
            columnLeft1.setBackground(DIRT);
            columnRight1.setBackground(DIRT);
            columnLeft2.setBackground(DIRT);
            columnRight2.setBackground(DIRT);
            paintMiddleColumn(col);
        }
        else if (columns.size() == MIDDLE_COLUMN + 1)
        {
            paintTwoColumns(columns);
        }
        else if (columns.size() == RIGHT_COLUMN + 1)
        {
            paintThreeColumns(columns);
        }

        revalidate();
    }

    private boolean addCard(ArrayList<JLabel> cards, JLabel card)
    {
        boolean result = true;
        if (cards.size() >= CARD_LIMIT)
        {
            cards.remove(0);
            result = false;
        }

        cards.add(card);
        return result;
    }

    private void setColColor(Column col, JPanel panel1, JPanel panel2)
    {
        PlayerID winner = col.getCurrentWinner();

        if (winner == PlayerID.PLAYER1)
        {
            panel1.setBackground(WINNER);
            panel2.setBackground(LOSER);
        }
        else if (winner == PlayerID.PLAYER2)
        {
            panel2.setBackground(WINNER);
            panel1.setBackground(LOSER);
        }

        panel1.repaint();
        panel2.repaint();
    }

    /**
     * This class represents the offsets for the location and layer of the
     * cards in a panel.
     */
    private class StackOfCards extends ArrayList<JLabel>
    {
        private int offset1 = 0, offset2 = 0, layerNdx1 = 0, layerNdx2 = 0;

        public int getOffset1()
        {
            return offset1++;
        }

        public int getOffset2()
        {
            return offset2++;
        }

        public int getLayerNdx1()
        {
            return layerNdx1++;
        }

        public int getLayerNdx2()
        {
            return layerNdx2++;
        }

        public void reset()
        {
            offset1 = 0;
            offset2 = 0;
            layerNdx1 = 0;
            layerNdx2 = 0;
        }
    }
}
