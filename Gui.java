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
    protected MyQuizPane contentPaneQuiz;
    
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
        contentPaneQuiz = new MyQuizPane(standardBorder, contentBorder, listener);
        
        mainFrame.add(contentPaneMain);
        //contentPaneMain.setVisible(true);
        mainFrame.add(contentPaneQuiz);
        //contentPaneQuiz.setVisible(false);
        switchPanes(1);//switching to the main pane
        
        
    }
    
    
    
    
    protected void updateMain(int[] stats, BufferedImage img, Set<String> exits, String currentShort, String roomMsg){
        contentPaneMain.updating(stats, img, exits, currentShort, roomMsg);
        switchPanes(1);//switching to the main pane
    }
    
    protected void updateMessageBoard(String message, int importance){
        contentPaneMain.updateMessages(message, importance);
    }
    
    protected void updateQuiz(String encounterWith, String question){
        contentPaneQuiz.updateQuiz(encounterWith, question);
        switchPanes(2);//switching to the quiz pane
    }
    
    protected void switchPanes(int paneNr){
        switch (paneNr) {
            case 1:  mainFrame.setContentPane(contentPaneMain);
                     break;
            case 2:  mainFrame.setContentPane(contentPaneQuiz);
                     break;
            }
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
