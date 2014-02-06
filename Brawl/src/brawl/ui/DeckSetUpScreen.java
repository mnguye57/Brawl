package brawl.ui;

import brawl.GameController;
import brawl.model.enums.BrawlCharacter;
import brawl.model.enums.PlayerID;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * A DeckSetUpScreen represents the screen in the game where the user selects
 * the deck of cards to play with and the player type (Human or AI).
 * @author Minh Nguyen
 */
public class DeckSetUpScreen extends JPanel
{
    private static final int FONT_THIRTY = 30, FONT_TWENTY = 20;
    private static final int GRID_SIZE = 3;
    private JPanel overallPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel charPanel = new JPanel();
    private JPanel playerPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JPanel readyPanel = new JPanel();
    private JPanel choicePanel = new JPanel();
    private JPanel mainMenuPanel = new JPanel();
    private JLabel youChose = new JLabel();
    private static final int MILTON = 0, WILBUR = 1, HUMPHREY = 2,
    BEATRICE = 3, GERTRUDE = 4, ESTHER = 5;
    /**
     * Constructs a DeckSetUpScreen.
     * @param ctrl the controller of the DeckSetUpScreen
     * @param player the player of the DeckSetUpScreen
     *
     */
    public DeckSetUpScreen(GameController ctrl, PlayerID player)
    {
        //Main menu button so you can go back
        JButton mainMenu = new JButton("Main Menu");
        mainMenu.setFont(new Font("Helvetica", Font.PLAIN, FONT_TWENTY));
        mainMenu.setActionCommand("mainMenuPAS");
        mainMenu.addActionListener(ctrl);
        mainMenu.setAlignmentX(CENTER_ALIGNMENT);
        mainMenuPanel.add(mainMenu);

        //Choosing your player (Human or AI)
        ButtonGroup playerButtons;
        playerButtons = new ButtonGroup();
        JLabel playerLbl;
        if (player == PlayerID.PLAYER1)
        {
            playerLbl =  new JLabel("Player One");
        }
        else
        {
            playerLbl =  new JLabel("Player Two");
        }
        playerLbl.setFont(new Font("Helvetica", Font.PLAIN, FONT_THIRTY));
        JRadioButton human = new JRadioButton("Human");
        human.setFont(new Font("Helvetica", Font.PLAIN, FONT_TWENTY));
        JRadioButton aiEasy = new JRadioButton("Computer Easy");
        aiEasy.setFont(new Font("Helvetica", Font.PLAIN, FONT_TWENTY));
        JRadioButton aiMedium = new JRadioButton("Computer Medium");
        aiMedium.setFont(new Font("Helvetica", Font.PLAIN, FONT_TWENTY));
        JRadioButton aiHard = new JRadioButton("Computer Hard");
        aiHard.setFont(new Font("Helvetica", Font.PLAIN, FONT_TWENTY));
        playerButtons.add(human);
        playerButtons.add(aiEasy);
        playerButtons.add(aiMedium);
        playerButtons.add(aiHard);
        human.setActionCommand("human1");
        human.addActionListener(ctrl);
        aiEasy.setActionCommand("computer easy");
        aiEasy.addActionListener(ctrl);

        aiMedium.setActionCommand("computer medium");
        aiMedium.addActionListener(ctrl);

        aiHard.setActionCommand("computer hard");
        aiHard.addActionListener(ctrl);

        choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.PAGE_AXIS));

        JButton milton = new JButton("Milton");
        milton.setActionCommand("milton1");
        milton.addActionListener(ctrl);

        milton.add(createImageLabel("resource/milton.png"));

        JButton wilbur = new JButton("Wilbur");
        wilbur.setActionCommand("wilbur1");
        wilbur.addActionListener(ctrl);

        wilbur.add(createImageLabel("resource/wilbur3.png"));

        JButton humphrey = new JButton("Humphrey");
        humphrey.setActionCommand("humphrey1");
        humphrey.addActionListener(ctrl);

        humphrey.add(createImageLabel("resource/humphrey2.png"));

        JButton beatrice = new JButton("Beatrice");
        beatrice.setActionCommand("beatrice1");
        beatrice.addActionListener(ctrl);

        beatrice.add(createImageLabel("resource/beatrice.jpg"));

        JButton gertrude = new JButton("Gertrude");
        gertrude.setActionCommand("gertrude1");

        gertrude.add(createImageLabel("resource/gertrude.png"));

        gertrude.addActionListener(ctrl);
        JButton esther = new JButton("Esther");
        esther.setActionCommand("esther1");
        esther.addActionListener(ctrl);

        esther.add(createImageLabel("resource/esther.jpg"));


        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        infoPanel.setAlignmentY(Component.RIGHT_ALIGNMENT);

        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.PAGE_AXIS));
        playerPanel.add(playerLbl);
        playerPanel.add(human);
        playerPanel.add(aiEasy);
        playerPanel.add(aiMedium);
        playerPanel.add(aiHard);

        JButton ready = new JButton("Ready To Play!");
        ready.setFont(new Font("Helvetica", Font.PLAIN, FONT_THIRTY));
        if (player == PlayerID.PLAYER1)
        {
            ready.setActionCommand("Ready P1");
        }
        else
        {
            ready.setActionCommand("Ready P2");
        }
        ready.addActionListener(ctrl);
        ready.setAlignmentX(CENTER_ALIGNMENT);
        readyPanel.add(ready);

        charPanel.setLayout(new GridLayout(2, GRID_SIZE, GRID_SIZE, GRID_SIZE));
        charPanel.setBorder(BorderFactory.createBevelBorder(0));
        charPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        charPanel.add(milton);
        charPanel.add(wilbur);
        charPanel.add(humphrey);
        charPanel.add(beatrice);
        charPanel.add(gertrude);
        charPanel.add(esther);

        topPanel.add(mainMenuPanel);
        topPanel.add(playerPanel);
        topPanel.add(choicePanel);
        topPanel.add(readyPanel);
        topPanel.setLayout(new FlowLayout());

        leftPanel.add(topPanel);
        leftPanel.add(charPanel);
        leftPanel.setAlignmentX(LEFT_ALIGNMENT);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        overallPanel.add(leftPanel);
        overallPanel.add(infoPanel);

        add(overallPanel);
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

    /**
     * Updates the deck information panel
     * @param bc updates the panel using this character's information
     */
    public void updateInfo(BrawlCharacter bc)
    {
        infoPanel.removeAll();
        choicePanel.removeAll();
        youChose.setText("You Chose:");
        youChose.setFont(new Font("Helvetica", Font.PLAIN, FONT_THIRTY));
        choicePanel.add(youChose);
        switch(bc.ordinal())
        {
            case MILTON:
                choicePanel.add(createImageLabel("resource/YouChoseMilton.jpg"));
                infoPanel.add(createImageLabel("resource/miltonDeckInfo.jpg"));
                break;
            case WILBUR:
                choicePanel.add(createImageLabel("resource/YouChoseWilbur.jpg"));
                infoPanel.add(createImageLabel("resource/wilburDeckInfo.jpg"));
                break;
            case HUMPHREY:
                choicePanel.add(createImageLabel("resource/YouChoseHumphrey.jpg"));
                infoPanel.add(createImageLabel("resource/humphreyDeckInfo.jpg"));
                break;
            case BEATRICE:
                choicePanel.add(createImageLabel("resource/YouChoseBeatrice.jpg"));
                infoPanel.add(createImageLabel("resource/beatriceDeckInfo.jpg"));
                break;
            case GERTRUDE:
                choicePanel.add(createImageLabel("resource/YouChoseGertrude.jpg"));
                infoPanel.add(createImageLabel("resource/gertrudeDeckInfo.jpg"));
                break;
            case ESTHER:
                choicePanel.add(createImageLabel("resource/YouChoseEsther.jpg"));
                infoPanel.add(createImageLabel("resource/estherDeckInfo.jpg"));
                break;
            default:
                break;
        }
        infoPanel.revalidate();
    }
}
