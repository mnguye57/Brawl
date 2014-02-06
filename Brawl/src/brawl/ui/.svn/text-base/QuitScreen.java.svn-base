package brawl.ui;

import brawl.GameController;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A QuitScreen represents the screen when the user decides to quit the game.
 *
 * @author Minh Nguyen
 */
public class QuitScreen extends JPanel
{
    private final static int YES_NO_FONT = 48, SURE_FONT = 60;
    /**
     * Constructs a QuitScreen
     * @param ctrl the controller for the QuitScreen
     */
    public QuitScreen(GameController ctrl)
    {
        JPanel overallPanel = new JPanel();
        overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        yesButton.setFont(new Font("Helvetica", Font.BOLD, YES_NO_FONT));
        JButton noButton = new JButton("No");

        noButton.setFont(new Font("Helvetica", Font.BOLD, YES_NO_FONT));
        JLabel youSure = new JLabel("Are you sure you want to quit?");
        youSure.setFont(new Font("Helvetica", Font.BOLD, SURE_FONT));

        yesButton.addActionListener(ctrl);
        noButton.addActionListener(ctrl);

        topPanel.add(youSure);
        middlePanel.add(yesButton);
        middlePanel.add(noButton);
        overallPanel.add(topPanel);
        topPanel.setAlignmentY(TOP_ALIGNMENT);
        overallPanel.add(middlePanel);
        middlePanel.setAlignmentY(CENTER_ALIGNMENT);
        overallPanel.add(bottomPanel);
        bottomPanel.setAlignmentY(BOTTOM_ALIGNMENT);

        middlePanel.add(yesButton);
        middlePanel.add(noButton);
        overallPanel.add(topPanel);
        topPanel.setAlignmentY(TOP_ALIGNMENT);
        overallPanel.add(middlePanel);
        middlePanel.setAlignmentY(CENTER_ALIGNMENT);
        overallPanel.add(bottomPanel);
        bottomPanel.setAlignmentY(BOTTOM_ALIGNMENT);

        add(overallPanel);
    }
}
