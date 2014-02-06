package brawl.ui;

import brawl.GameController;
import brawl.MatchModel;
import brawl.model.enums.PlayerID;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A PlayAgainScreen represents the screen after the game is over where
 * the user can decide to either quit the game or play again.
 *
 * @author Minh Nguyen
 */
public class PlayAgainScreen extends JPanel
{
    private final static int WINNER_FONT = 100;
    private final static int BUTTON_FONT = 32;
    private MatchModel matchModel;
    private JLabel pWins = new JLabel();
    /**
     * Constructs a PlayAgainScreen
     * @param ctrl the controller of the PlayAgainScreen
     */
    public PlayAgainScreen(GameController ctrl)
    {
        JPanel overall = new JPanel();
        JButton playAgain = new JButton();
        JButton quit = new JButton();
        JButton mainMenu = new JButton();

        overall.setLayout(new BoxLayout(overall, BoxLayout.Y_AXIS));

        pWins.setFont(new Font("Helvetica", Font.BOLD, WINNER_FONT));
        pWins.setAlignmentX(CENTER_ALIGNMENT);
        pWins.setAlignmentY(CENTER_ALIGNMENT);

        playAgain.setText("Play Again");
        playAgain.setFont(new Font("Helvetica", Font.BOLD, BUTTON_FONT));
        playAgain.setActionCommand("playAgainPAS");
        playAgain.setAlignmentX(CENTER_ALIGNMENT);
        playAgain.setAlignmentY(BOTTOM_ALIGNMENT);
        playAgain.addActionListener(ctrl);

        mainMenu.setText("Back to Main Menu");
        mainMenu.setFont(new Font("Helvetica", Font.BOLD, BUTTON_FONT));
        mainMenu.setActionCommand("mainMenuPAS");
        mainMenu.setAlignmentX(CENTER_ALIGNMENT);
        mainMenu.setAlignmentY(BOTTOM_ALIGNMENT);
        mainMenu.addActionListener(ctrl);

        quit.setText("Quit Game");
        quit.setFont(new Font("Helvetica", Font.BOLD, BUTTON_FONT));
        quit.setActionCommand("quitPAS");
        quit.setAlignmentX(CENTER_ALIGNMENT);
        quit.setAlignmentY(BOTTOM_ALIGNMENT);
        quit.addActionListener(ctrl);

        overall.add(pWins);
        overall.add(playAgain);
        overall.add(mainMenu);
        overall.add(quit);

        add(overall);
    }

    /**
     * Sets winner text on the screen based on the winner of the match
     */
    public void setWinner()
    {
        PlayerID winner = matchModel.getWinner();

        if (winner == PlayerID.PLAYER1)
        {
            pWins.setText("Player 1 Wins!");
        }
        else if (winner == PlayerID.PLAYER2)
        {
            pWins.setText("Player 2 Wins!");
        }
        else if (winner == null)
        {
            pWins.setText("Tie Game!");
        }

        pWins.revalidate();
    }

    /**
     * Sets the current MatchModel to determine who the winner is
     * @param matchModel the MatchModel used to find the winner
     */
    public void setMatchModel(MatchModel matchModel)
    {
        this.matchModel = matchModel;
    }
}
