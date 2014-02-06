package brawl.ui;

import brawl.GameController;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A MainMenuScreen represents the main menu of the Brawl game which provides
 * access to all the modes and options of the game.
 *
 * @author Minh Nguyen
 */
public class MainMenuScreen extends JPanel
{

    private static final int FONT_32 = 32, FONT_48 = 48;
    /**
     * Constructs a MainMenuScreen
     * @param ctrl the controller of the MainMenuScreen
     */
    public MainMenuScreen(GameController ctrl)
    {
         /* Set a listener for each button in the Main Menu */
        JButton trainingButton = new JButton("Training");
        trainingButton.setFont(new Font("Helvetica", Font.BOLD, FONT_32));
        trainingButton.addActionListener(ctrl);
        trainingButton.setAlignmentX(CENTER_ALIGNMENT);
        JButton tournamentButton = new JButton("Tournament");
        tournamentButton.setFont(new Font("Helvetica", Font.BOLD, FONT_32));
        tournamentButton.addActionListener(ctrl);
        tournamentButton.setAlignmentX(CENTER_ALIGNMENT);
        JButton tutorialButton = new JButton("Tutorial");
        tutorialButton.setFont(new Font("Helvetica", Font.BOLD, FONT_32));
        tutorialButton.addActionListener(ctrl);
        tutorialButton.setAlignmentX(CENTER_ALIGNMENT);
        JButton optionsButton = new JButton("Options");
        optionsButton.setFont(new Font("Helvetica", Font.BOLD, FONT_32));
        optionsButton.addActionListener(ctrl);
        optionsButton.setAlignmentX(CENTER_ALIGNMENT);
        JButton aboutButton = new JButton("About");
        aboutButton.setFont(new Font("Helvetica", Font.BOLD, FONT_32));
        aboutButton.setAlignmentX(CENTER_ALIGNMENT);
        aboutButton.addActionListener(ctrl);
        JButton quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Helvetica", Font.BOLD, FONT_32));
        quitButton.addActionListener(ctrl);
        quitButton.setAlignmentX(CENTER_ALIGNMENT);

        /* Add the buttons to the main menu */
        JLabel mainMenuLabel = new JLabel("Main Menu");
        mainMenuLabel.setAlignmentX(CENTER_ALIGNMENT);
        mainMenuLabel.setFont(new Font("Helvetica", Font.BOLD, FONT_48));

        JLabel background = new JLabel(new ImageIcon(getClass()
                .getClassLoader().getResource("resource/mainMenuScreen.jpg")));
        background.setAlignmentX(CENTER_ALIGNMENT);
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        background.add(mainMenuLabel);
        background.add(trainingButton);
        background.add(tournamentButton);
        background.add(tutorialButton);
        background.add(optionsButton);
        background.add(aboutButton);
        background.add(quitButton);
        add(background);
    }
}
