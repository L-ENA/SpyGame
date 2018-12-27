import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.image.BufferedImage;
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
        menuBar = new MyMenuBar();
        mainFrame.setJMenuBar(menuBar);
        
        standardBorder = new LineBorder(Color.GREEN);//Customizes the line colour for the following TitledBorder instances
        contentPaneMain = new MyMainPane(standardBorder);
        contentPaneQuiz = new JPanel(new GridBagLayout());//a panel that covers the frame
        
        
        
       
        
        
        mainFrame.add(contentPaneMain);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    protected void updateMain(int[] stats, BufferedImage img){
        //protected void updateMain(BufferedImage roomPicture, int[] stats){
        contentPaneMain.updating(stats, img);
    }
    
    
    
}
