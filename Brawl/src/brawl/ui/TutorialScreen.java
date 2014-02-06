package brawl.ui;

import brawl.GameController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A TutorialScreen represents the screen containing the Tutorial feature of
 * the Brawl game.
 *
 * @author Minh Nguyen
 */
public class TutorialScreen extends JPanel
{
    private int pageCount = 1;
    private JLabel background;
    private final static int LASTPAGE = 9;
    private GameController ctrl;
    /**
     * Constructs a TutorialScreen
     * @param ctrl the controller for the TutorialScreen
     */
    public TutorialScreen(GameController ctrl)
    {
        String nextImg = "resource/TutorialScreen" +
                Integer.toString(pageCount++) + ".jpg";
        this.ctrl = ctrl;
        background = new JLabel(new ImageIcon(getClass()
            .getClassLoader().getResource(nextImg)));
        background.setAlignmentX(CENTER_ALIGNMENT);
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        add(background);
        addKeyListener(ctrl);
    }

    /**
     * Sets the next page in the tutorial screen.
     * @param keyCode determines if the user wants to go forward or backward
     *        a page.
     */
    public void setNextPage(int keyCode)
    {
        if (keyCode == KeyEvent.VK_LEFT)
        {
            pageCount--;
        }

        if (pageCount <= LASTPAGE && pageCount >= 1)
        {
            String nextImg = "resource/TutorialScreen" +
                Integer.toString(pageCount) + ".jpg";
            background.setIcon(new ImageIcon(getClass()
                .getClassLoader().getResource(nextImg)));
            revalidate();
            repaint();
        }
        else
        {
            pageCount = 1;
            String nextImg = "resource/TutorialScreen" +
                Integer.toString(pageCount) + ".jpg";
            background.setIcon(new ImageIcon(getClass()
                .getClassLoader().getResource(nextImg)));
            ctrl.actionPerformed(new ActionEvent(this,
                ActionEvent.ACTION_PERFORMED, "Main Menu"));
        }

        if (keyCode == KeyEvent.VK_RIGHT)
        {
            pageCount++;
        }
    }
}
