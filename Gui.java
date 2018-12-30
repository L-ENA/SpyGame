import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.image.BufferedImage;
import java.util.Set;
/**
 * Write a description of class Gui here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gui
{
    protected JFrame mainFrame;
    protected MyMainPane contentPaneMain;
    protected JPanel contentPaneQuiz;
    
    protected Border standardBorder;//border style used for all titled borders
    private Border contentBorder;
    
    private MyMenuBar menuBar;
    private ActionListener listener;
    
    /**
     * Constructor for objects of class Gui
     */
    public Gui(ActionListener listener)
    {
        this.listener = listener;
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
        menuBar = new MyMenuBar(mainFrame);
        mainFrame.setJMenuBar(menuBar);
        
        standardBorder = new LineBorder(Color.GREEN);//Customizes the line colour for the following TitledBorder instances
        contentBorder = new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.BLACK);//border for all content
        
        contentPaneMain = new MyMainPane(standardBorder, contentBorder, listener);
        contentPaneQuiz = new JPanel(new GridBagLayout());//a panel that covers the frame
        
        mainFrame.add(contentPaneMain);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
        
    }
    
    
    
    
    protected void updateMain(int[] stats, BufferedImage img, Set<String> exits, String currentShort){
        contentPaneMain.updating(stats, img, exits, currentShort);
    }
    
    protected void updateMessageBoard(String message, int importance){
        contentPaneMain.updateMessages(message, importance);
    }
    
}
