package brawl.ui;

import brawl.GameController;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * An IntroScreen represents the introduction screen to the Brawl game.
 *
 * @author Minh Nguyen
 */
public class IntroScreen extends JPanel
{
    /**
     * Constructs an IntoScreen
     * @param ctrl the controller of the IntroScreen
     */
    public IntroScreen(GameController ctrl)
    {
         /*Set a listener for button on intro screen */
        JButton introButton = new JButton("Proceed");
        introButton.addActionListener(ctrl);
        introButton.setAlignmentX(CENTER_ALIGNMENT);

        /*Add button to intro screen*/
        add(introButton);
        try
        {
            BufferedImage splashScreen = ImageIO.read(getClass()
                    .getClassLoader().getResource("resource/brawlScreen.jpg"));
            JLabel splashPanel = new JLabel(new ImageIcon(splashScreen));
            add(splashPanel);
        }
        catch (Exception e)
        {
            System.out.println("Couldn't find the splash screen image. ");
        }
    }
}
