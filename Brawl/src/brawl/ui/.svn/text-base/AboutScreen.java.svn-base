package brawl.ui;

import brawl.GameController;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * An AboutScreen represents a screen containing information about the product
 * such as the version number, the vendor, and the home page.
 * @author Minh Nguyen
 */
public class AboutScreen extends JPanel
{
    private final static int TITLE_FONT = 50, REGULAR_FONT = 30;

    /**
     * Constructs an About Screen.
     * @param ctrl the controller of the About Screen
     */
    public AboutScreen(GameController ctrl)
    {
        JPanel overallPanel = new JPanel();
        overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.PAGE_AXIS));
        JLabel background = new JLabel(new ImageIcon(getClass()
                .getClassLoader().getResource("resource/mainMenuScreen.jpg")));
        background.setLayout(new BoxLayout(background, BoxLayout.LINE_AXIS));

        JLabel titleLbl = new JLabel("Brawl: Garden Time");
        titleLbl.setFont(new Font("Helvetica", Font.BOLD, TITLE_FONT));
        titleLbl.setAlignmentX(LEFT_ALIGNMENT);

        JLabel infoLbl = new JLabel("A Java Application based on the Swing" +
            " Application Framework");
        infoLbl.setFont(new Font("Helvetica", Font.PLAIN, REGULAR_FONT));
        infoLbl.setAlignmentX(LEFT_ALIGNMENT);

        JPanel versionPanel = makeNewLbl("Product Version: ", "2.0");

        JPanel vendorPanel = makeNewLbl("Vendor: ", "FBRD");

        JPanel homePanel = makeNewLbl("Homepage: ",
            "https://wiki.csc.calpoly.edu/FBRD/wiki");

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(ctrl);
        mainMenuButton.setAlignmentX(LEFT_ALIGNMENT);

        overallPanel.add(titleLbl);
        overallPanel.add(infoLbl);
        overallPanel.add(versionPanel);
        overallPanel.add(vendorPanel);
        overallPanel.add(homePanel);
        overallPanel.add(mainMenuButton);
        overallPanel.setAlignmentY(BOTTOM_ALIGNMENT);
        overallPanel.setOpaque(false);
        background.add(overallPanel);
        add(background);
    }

    private JPanel makeNewLbl(String name, String info)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel nameLbl = new JLabel(name);
        nameLbl.setFont(new Font("Helvetica", Font.BOLD, REGULAR_FONT));
        JLabel infoLbl = new JLabel(info);
        infoLbl.setFont(new Font("Helvetica", Font.PLAIN, REGULAR_FONT));
        panel.add(nameLbl);
        panel.add(infoLbl);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setOpaque(false);

        return panel;
    }
}
