import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
/**
 * Write a description of class Gui here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gui
{
    protected JFrame mainFrame;
    protected JPanel contentPaneMain;
    protected JPanel contentPaneQuiz;
    protected JPanel statsPanel;
    protected JPanel picturePanel;
    protected JPanel navigationPanel;
    
    GridBagConstraints c;
    protected Border standardBorder;
    
    JMenuBar menuBar;
    
    /**
     * Constructor for objects of class Gui
     */
    public Gui()
    {
        setUp();
    }

    /**
     * Method to set up the basic gui
     *
     * @param  none
     * @return    void
     */
    private void setUp()
    {
        mainFrame = new JFrame("SpyGame");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//what happens if the x is pressed: exit the game
        initMenuBar();
        
        standardBorder = new LineBorder(Color.GREEN);//Customizes the line colour for the following TitledBorder instances
        contentPaneMain = new JPanel(new GridBagLayout());//a panel that covers the frame
        initPaneMain();//setting up the main content pane
        contentPaneQuiz = new JPanel(new GridBagLayout());//a panel that covers the frame
        
        
        
       
        
        
        mainFrame.add(contentPaneMain);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    /**
     * Method to set up the menu bar
     *
     * @param  none
     * @return    void
     */
    private void initMenuBar(){
        JMenuItem someItem;
        menuBar = new JMenuBar();
        
        JMenu gameMenu = new JMenu("Game");
        JMenuItem closeItem = new JMenuItem("Close");
		
	closeItem.addActionListener(new ActionListener(){//what happens if the close action is invoked
	    public void actionPerformed(ActionEvent e) {
	        int ret = JOptionPane.showConfirmDialog(mainFrame.getContentPane(), "Are you sure you want to exit? ");
		if(ret == JOptionPane.YES_OPTION)
		     System.exit(0);		
		}});
		
	gameMenu.add(closeItem);
        menuBar.add(gameMenu);
        
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);
        
        JMenuItem sourceItem = new JMenuItem("Sources");
        sourceItem.addActionListener(new ActionListener(){//what happens if the source action is invoked
	    public void actionPerformed(ActionEvent e) {
	        JOptionPane.showMessageDialog(mainFrame.getContentPane(), "Picture sources: "
						+ " \n pic"
						+ " \n www.picsource.de"
						+ " \n  rcrettw"
						+ " \n fghjˆolikujhgf"
						+ " \n  ﬂﬂ‰‰‰‰‰‰‰‰‰"
						+ " \n hi");
	        		
		}});
        
        aboutMenu.add(sourceItem);
        
        
        
	mainFrame.setJMenuBar(menuBar);
    }
    
    /**
     * Method to set up the main gameplay pane
     *
     * @param  none
     * @return    void
     */
    private void initPaneMain(){
        c = new GridBagConstraints();//to manage layout
        c.fill = GridBagConstraints.BOTH;//stretch both horizontally and vertically
        c.weighty = 1;//sets size of this panel compared with other panels: they all stretch over the whole height
        
        
        c.weightx = 0.2;//sets size of this panel compared with other panels: this one is slightly small in x direction
        statsPanel = new JPanel();
        statsPanel.setBackground(Color.white);
        statsPanel.setBorder(new TitledBorder(standardBorder, "Statistics"));//uses the border customized before and adds a title
        c.gridx = 0;//grid coordinates where the panel will sit
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;//if window is resized
        
        contentPaneMain.add(statsPanel, c);
        
        c.weightx = 0.5;
        picturePanel = new JPanel();
        picturePanel.setBackground(Color.white);
        picturePanel.setBorder(new TitledBorder(standardBorder, "Location: "));
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        contentPaneMain.add(picturePanel, c);
        
        c.weightx = 0.3;
        navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.white);
        navigationPanel.setBorder(new TitledBorder(standardBorder, "Directions"));
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        contentPaneMain.add(navigationPanel, c);
        
    }
    
}
