

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The test class MyQuizPaneTest. It offered a quick way of displaying the Quiz pane to see how this part of the GUI is 
 * behaving during the development.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MyQuizPaneTest
{
    Border standardBorder; 
    Border contentBorder; 
    ActionListener listener;
    MyQuizPane myQuizPane;
    /**
     * Default constructor for test class MyQuizPaneTest
     */
    public MyQuizPaneTest()
    {
        standardBorder = new LineBorder(Color.GREEN);//Customizes the line colour for the following TitledBorder instances
        contentBorder = new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.BLACK);//border for all content
        listener = new Game(true);//the empty game constructor
    }
    
    @Test
    public void initQuizPane(){
        JFrame mainFrame = new JFrame("Quiztest");
        myQuizPane = new MyQuizPane(standardBorder, contentBorder, listener);
        mainFrame.add(myQuizPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
