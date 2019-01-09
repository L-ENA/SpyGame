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
 * The GUI class serves as a controller of the more specific parts of the GUI. Its methods are called by the GAME class and it 
 * forwards the given parameters to each piece of the GUI that is to be updated. It owns instances of all GUI parts and has a method that 
 * allows changing their visibility by setting them as the content pane.
 *
 * @205232
 * @08.01.2019
 */
public class Gui
{
    private JFrame mainFrame;
    private MyMainPane contentPaneMain;
    private MyQuizPane contentPaneQuiz;
    private MyInstructionPane contentPaneInstruction;
    private Border standardBorder;//border style used for all titled borders
    private Border contentBorder;
    private MyMenuBar menuBar;
    private ActionListener listener;
    
    /**
     * Constructor for objects of class Gui. It sets the ActionListener parameter, which is to be forwarded to the different GUI elements and calls the setup for these elements.
     * @param ActionListener the listener for evaluating actions in the GUI.
     */
    public Gui(ActionListener listener)
    {
        this.listener = listener;
        setUp();
    }

    /**
     * Method to set up the basic gui elements: the main frame, menu bar, panels and some style parameters that all GUI elements will implement.
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
        contentPaneInstruction = new MyInstructionPane(standardBorder, contentBorder, listener);
        
        mainFrame.add(contentPaneMain);//adding the com
        mainFrame.add(contentPaneQuiz);
        mainFrame.add(contentPaneInstruction);
        switchPanes(1);//switching to the main pane
        
        
    }
    
     /**
     * This method sets the current content pane according to a given parameter. Throughout the game there are many changes like this so it was important to create this method for avoiding a lot of duplicate code. 1 is the contentPaneMain, 2 is the quiz pane and 3 the instruction pane.
     * @param  int  an integer that represents the desired panel.
     * @return    void
     */
    protected void switchPanes(int paneNr){
        switch (paneNr) {//simple switch to reduce if-elses and to set the desired pane quickly.
            case 1:  mainFrame.setContentPane(contentPaneMain);
                     break;
            case 2:  mainFrame.setContentPane(contentPaneQuiz);
                     break;
            case 3:  mainFrame.setContentPane(contentPaneInstruction);
                     break;        
            }
        mainFrame.pack();//to finalise changes and visualise them
        mainFrame.setVisible(true);
    }
    
    /**
     * Method to update the contentPaneMain. It is called after each step the player takes and the panel will be updated according to the parameters.
     * 
     * @param  int[]  the content for the stats panel
     * @param  BufferedImage  the room image for the picture panel
     * @param  Set<String>  the exits for the creation of the navigation buttons
     * @param  String  description of the current room
     * @param  String  some message associated with the current room
     * @return    void
     */
    protected void updateMain(int[] stats, BufferedImage img, Set<String> exits, String currentShort, String roomMsg){
        contentPaneMain.updating(stats, img, exits, currentShort, roomMsg);//updating method of the MyMainPane class
        switchPanes(1);//switching to the panel
    }
    
    /**
     * Method to update the Message Board on the contentPaneMain. This is done every few steps when tea and worktime change and is independent from the updates in updateMain().
     * @param  String  the message to be shown
     * @paranm int the importance level, which will decide the color code in the actual update.
     * @return    void
     */
    protected void updateMessageBoard(String message, int importance){
        contentPaneMain.updateMessages(message, importance);//updating method of the message board in the MyMainPane class
    }
    
    /**
     * Method to update the quiz pane. The name of the interrogating colleague is given along with the question.
     * @param  String  the name of the colleague who asks
     * @param  String  the quiz question
     * @return    void
     */
    protected void updateQuiz(String encounterWith, String question){
        contentPaneQuiz.updateQuiz(encounterWith, question);//updating method in the MyQuizPane class
        switchPanes(2);//switching to the quiz pane
    }
    
    /**
     * Method to update the instruction pane. It focuses the players attention on a message with a given title and is used in different stages of the game.
     * @param  String  the message
     * @param  String  the title
     * @return    void
     */
    protected void updateInstructionPane(String message, String title){
        contentPaneInstruction.updateContent(title, message);//update method if the MyInstructionPane class
    }
    
    /**
     * The final stats are given and the player has a choice of ending or playing again.
     * @param  String  the message
     * @param  String  the title
     * @return    void
     */
    protected boolean exitMessage(String message, String title){
        String[] options = {"Play again","Exit"};

        int choice = JOptionPane.showOptionDialog(mainFrame, //Component parentComponent
               message, //Object message,
               title, //String title
               JOptionPane.YES_NO_OPTION, //int optionType
               JOptionPane.INFORMATION_MESSAGE, //int messageType
               null, //Icon icon,
               options,
               options[1]);
        if(choice == 0 ){
           return true;
         }else{
           System.exit(0);
         }
        return false;
                              
    }
    
    /**
     * The player wants to play again, therefore the current main frame is terminated.
     * @param  none
     * @return    void
     */
    protected void killGui(){
        mainFrame.dispose();
    }
}
