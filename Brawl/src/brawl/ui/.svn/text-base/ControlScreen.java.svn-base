package brawl.ui;

import brawl.GameController;
import brawl.model.Move;
import brawl.model.enums.PlayerID;
import brawl.utils.KeyMapper;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A ControlScreen represents the screen that allows the user to customize
 * the key bindings for the Brawl game.
 *
 * @author Minh Nguyen
 */
public class ControlScreen extends JPanel
{
    private JPanel overallPanel, grid;
    private static final int SIZE = 20;
    private static final int GRID_WIDTH = 3;
    private static final int GRID_HEIGHT = 9;
    private static final int GAP = 5;
    private Font font = new Font("Helvetica", Font.PLAIN, SIZE);
    private GameController cont;

    private JLabel commandlabel, player1label, player2label;
    private JLabel passLabel, drawLabel, playBLeft, playBMiddle, playBRight, playTLeft,
    playTMiddle, playTRight;
    private Move mP1Draw, mP1Pass, mP1BL, mP1BM, mP1BR, mP1TL, mP1TM, mP1TR;
    private Move mP2Draw, mP2Pass, mP2BL, mP2BM, mP2BR, mP2TL, mP2TM, mP2TR;

    private JButton p1Draw, p1Pass, p1BL, p1BM, p1BR, p1TL, p1TM, p1TR;
    private JButton p2Draw, p2Pass, p2BL, p2BM, p2BR, p2TL, p2TM, p2TR;

    /**
     * Creates the Control Screen for the GUI
     * @param ctrl A GameController
     */
    public ControlScreen(GameController ctrl)
    {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.cont = ctrl;

        mP1Draw = new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, -1);
        mP1Pass = new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, KeyMapper.PASS_COLUMN);
        mP1BL = new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, 0);
        mP1BM = new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, 1);
        mP1BR = new Move(PlayerID.PLAYER1, PlayerID.PLAYER1, 2);
        mP1TL = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 0);
        mP1TM = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 1);
        mP1TR = new Move(PlayerID.PLAYER1, PlayerID.PLAYER2, 2);
        mP2Draw = new Move(PlayerID.PLAYER2, PlayerID.PLAYER2, -1);
        mP2Pass = new Move(PlayerID.PLAYER2, PlayerID.PLAYER2, KeyMapper.PASS_COLUMN);
        mP2BL = new Move(PlayerID.PLAYER2, PlayerID.PLAYER2, 2);
        mP2BM = new Move(PlayerID.PLAYER2, PlayerID.PLAYER2, 1);
        mP2BR = new Move(PlayerID.PLAYER2, PlayerID.PLAYER2, 0);
        mP2TL = new Move(PlayerID.PLAYER2, PlayerID.PLAYER1, 2);
        mP2TM = new Move(PlayerID.PLAYER2, PlayerID.PLAYER1, 1);
        mP2TR = new Move(PlayerID.PLAYER2, PlayerID.PLAYER1, 0);

        buildControlsScreen();
    }

    private void makeOverallPanel()
    {
        overallPanel = new JPanel();
        JButton backButton = new JButton("Save Settings");
        backButton.setFont(font);
        backButton.setActionCommand("Save Bindings");
        backButton.addActionListener(cont);
        overallPanel.add(backButton);
        JButton resetButton = new JButton("Reset to Defaults");
        resetButton.setFont(font);
        resetButton.setActionCommand("Reset Bindings");
        resetButton.addActionListener(cont);
        overallPanel.add(resetButton);
    }

    private void makeTopLabels()
    {
        commandlabel = new JLabel("Command");
        commandlabel.setFont(font);
        player1label = new JLabel("Player 1");
        player1label.setFont(font);
        player2label = new JLabel("Player 2");
        player2label.setFont(font);

        passLabel = new JLabel("Pass");
        passLabel.setFont(font);
        drawLabel = new JLabel("Draw");
        drawLabel.setFont(font);
        playBLeft = new JLabel("Play Bottom Left");
        playBLeft.setFont(font);
        playBMiddle = new JLabel("Play Bottom Middle");
        playBMiddle.setFont(font);
        playBRight = new JLabel("Play Bottom Right");
        playBRight.setFont(font);
        playTLeft = new JLabel("Play Top Left");
        playTLeft.setFont(font);
        playTMiddle = new JLabel("Play Top Middle");
        playTMiddle.setFont(font);
        playTRight = new JLabel("Play Top Right");
        playTRight.setFont(font);
    }

    private JButton makeButton(int key, Move move)
    {
        JButton button = new JButton(KeyEvent.getKeyText(key));
        button.setActionCommand(move.toString());
        button.setFont(font);
        button.addActionListener(cont);
        button.addKeyListener(cont);
        button.requestFocus();

        return button;
    }
    private void makeGrid()
    {
        grid = new JPanel();
        GridLayout layout = new GridLayout(GRID_HEIGHT, GRID_WIDTH);
        layout.setHgap(GAP);
        grid.setLayout(layout);

        grid.add(commandlabel);
        grid.add(player1label);
        grid.add(player2label);

        grid.add(drawLabel);
        grid.add(p1Draw);
        grid.add(p2Draw);

        grid.add(passLabel);
        grid.add(p1Pass);
        grid.add(p2Pass);

        grid.add(playBLeft);
        grid.add(p1BL);
        grid.add(p2BL);

        grid.add(playBMiddle);
        grid.add(p1BM);
        grid.add(p2BM);

        grid.add(playBRight);
        grid.add(p1BR);
        grid.add(p2BR);

        grid.add(playTLeft);
        grid.add(p1TL);
        grid.add(p2TL);

        grid.add(playTMiddle);
        grid.add(p1TM);
        grid.add(p2TM);

        grid.add(playTRight);
        grid.add(p1TR);
        grid.add(p2TR);

        add(grid);
        add(overallPanel);
    }

    private int getKey(Map<Integer, Move> map, Move move)
    {
        for (Entry<Integer, Move> entry : map.entrySet())
        {
            if (move.equals(entry.getValue()))
            {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * Updates the screen whenever a key is changed
     */
    public void updateControlsScreen()
    {
        this.removeAll();
        buildControlsScreen();
        revalidate();
    }

    private void buildControlsScreen()
    {
        makeOverallPanel();
        makeTopLabels();
        Map<Integer, Move> moveMap;
        moveMap = KeyMapper.getKeys(PlayerID.PLAYER1);
        p1Draw = makeButton(getKey(moveMap, mP1Draw), mP1Draw);
        p1Pass = makeButton(getKey(moveMap, mP1Pass), mP1Pass);
        p1BL = makeButton(getKey(moveMap, mP1BL), mP1BL);
        p1BM = makeButton(getKey(moveMap, mP1BM), mP1BM);
        p1BR = makeButton(getKey(moveMap, mP1BR), mP1BR);
        p1TL = makeButton(getKey(moveMap, mP1TL), mP1TL);
        p1TM = makeButton(getKey(moveMap, mP1TM), mP1TM);
        p1TR = makeButton(getKey(moveMap, mP1TR), mP1TR);
        moveMap = KeyMapper.getKeys(PlayerID.PLAYER2);
        p2Draw = makeButton(getKey(moveMap, mP2Draw), mP2Draw);
        p2Pass = makeButton(getKey(moveMap, mP2Pass), mP2Pass);
        p2BL = makeButton(getKey(moveMap, mP2BL), mP2BL);
        p2BM = makeButton(getKey(moveMap, mP2BM), mP2BM);
        p2BR = makeButton(getKey(moveMap, mP2BR), mP2BR);
        p2TL = makeButton(getKey(moveMap, mP2TL), mP2TL);
        p2TM = makeButton(getKey(moveMap, mP2TM), mP2TM);
        p2TR = makeButton(getKey(moveMap, mP2TR), mP2TR);

        makeGrid();
    }
}
