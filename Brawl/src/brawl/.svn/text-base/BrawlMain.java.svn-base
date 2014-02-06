package brawl;

import brawl.ui.ConsoleUI;
import brawl.ui.GameView;
import brawl.ui.SwingUI;

/**
 * Main class for Brawl, responsible for managing MVC triad.
 *
 * @author FBRD-Paul Doyle
 * @version 1.0
 */
public class BrawlMain
{
    /**
     * Main method that connects MVC triad and manages the program components.
     *
     * @param args the command line arguments (none supported yet)
     */
    public static void main(String[] args)
    {
        SettingsModel sModel = new SettingsModel();
        GameController controller = new GameController();
        GameView view;

        if (args.length > 0)
        {
            if (args[0].equals("--console"))
            {
                view = new ConsoleUI();
            }
            else
            {
                view = new SwingUI();
            }
        }
        else
        {
            view = new SwingUI();
        }
        controller.setView(view);
        controller.setModel(sModel);

        view.setController(controller);
        view.setSettingsModel(sModel);
        view.init();
    }
}
