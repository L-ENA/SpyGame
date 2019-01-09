import javax.swing.*;
import java.awt.event.*;
/**
 * This class extends JMenuBar. The constructor returns a menu bar that is customized for the SpyGame. It contains the function to exit
 * the game and functions to provide some information to the player.
 *
 * @205232
 * @08.01.2019
 */
public class MyMenuBar extends JMenuBar implements ActionListener
{
    private JFrame mainFrame;
     /**
     * Constructor for objects of class MyMenuBar. Its submenues and items are created here. 
     */
    public MyMenuBar(JFrame mainFrame)
    {
        super();
        this.mainFrame = mainFrame;
        JMenu gameMenu = new JMenu("Game");//new menues
        JMenu aboutMenu = new JMenu("About");
        
        JMenuItem closeItem = new JMenuItem("Close");//create menu items and their action commands
        closeItem.setActionCommand("close");
        closeItem.addActionListener(this);
        
        JMenuItem helpItem = new JMenuItem("Help");//create menu items and their action commands
        helpItem.setActionCommand("help");
        helpItem.addActionListener(this);
        
        JMenuItem sourceItem = new JMenuItem("Sources");
        sourceItem.setActionCommand("source");
        sourceItem.addActionListener(this);
        
        gameMenu.add(closeItem);//adding menu item to their menu
        gameMenu.add(helpItem);
        aboutMenu.add(sourceItem);
        
        add(gameMenu);//adding the menus to the menu bar
        add(aboutMenu);
    }

    /**
     * Overriding the action performed method to carry out actions related to the menu bar. This method is the centralised 
     * event handling for the menu bar.
     *
     * @param  ActionEvent e
     * @return    void
     */
    public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("close")) {//what happens if the exit action is invoked: gives choice.
        int ret = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to exit? ");
                if(ret == JOptionPane.YES_OPTION)
                    System.exit(0);//exits the game
    } else if (e.getActionCommand().equals("source")) {//what happens if the source action is invoked
        JOptionPane.showMessageDialog(mainFrame, "Picture sources: "
                + " \n https://www.freepik.com/free-photos-vectors/paper >Paper image created by Freepik"
                + " \n https://www.freepik.com/free-photos-vectors/food >Food image created by Yanalya - Freepik.com"
                + " \n https://www.freepik.com/free-photos-vectors/background >Background image created by Nikitabuida - Freepik.com"
                + " \n https://www.freepik.com/free-photos-vectors/business >Business image created by Peoplecreations - Freepik.com"
                + " \n https://www.freepik.com/free-photos-vectors/business >Business image created by Rawpixel.com - Freepik.com"
                + " \n https://www.freepik.com/free-photo/restaurant_3563675.htm >Designed by Freepik"
                + " \n https://www.freepik.com/free-photo/public-toilet_1278624.htm >Designed by 4045"
                + " \n https://www.freepik.com/free-photos-vectors/business >Business image created by Katemangostar - Freepik.com");
    } else if (e.getActionCommand().equals("help")){
        JOptionPane.showMessageDialog(mainFrame, "This is some help info. Click and do stuff.");
       
    }
}
}
