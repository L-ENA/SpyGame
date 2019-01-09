import javax.swing.*;
import java.awt.event.*;
/**
 * This class extends JMenuBar. The constructor returns a menu bar that is customized for the SpyGame. It contains the function to exit
 * the game and functions to provide information to the player.
 *
 * @205232
 * @08.01.2019
 */
public class MyMenuBar extends JMenuBar implements ActionListener
{
    // instance variables - replace the example below with your own
    JFrame mainFrame;

    /**
     * Constructor for objects of class MyMenuBar. 
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
                        + " \n pic"
                        + " \n www.picsource.de"
                        + " \n  rcrettw"
                        + " \n fghjˆolikujhgf"
                        + " \n  ﬂﬂ‰‰‰‰‰‰‰‰‰"
                        + " \n hi");
	} else if (e.getActionCommand().equals("help")){
	    JOptionPane.showMessageDialog(mainFrame, "This is some help info. Click and do stuff.");
	   
	}
}
}
