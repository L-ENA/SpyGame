import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.*;
/**
 * The test class RoomTest. It tests functionalities such as locking doors and returning the status of locking doors. It also 
 * tests if pictures belonging to the rooms are read and displayed correctly.
 *
 * @205232
 * @08.01.2019
 */
public class RoomTest
{
    private Room officeBoss;
    private Room firstHallway;
    /**
     * Default constructor for test class RoomTest. Sets up some rooms
     */
    public RoomTest()
    {
        officeBoss = new Room("in the office of your boss", "officeBoss.jpg");
        firstHallway = new Room("in the hallway", "hallway.jpg");
        firstHallway.setExit("office", officeBoss);
    }
    
    /**
     * Testing the locking status of a room
     */
    @Test
    public void doStatusTest(){
        assertEquals(firstHallway.getDoors().checkStatus("office"), true);//should be unlocked by default
        firstHallway.getDoors().addLock("office", false);//sets access for this room: lock the door
        assertEquals(firstHallway.getDoors().checkStatus("office"), false);//should be unlocked by default
    }
    
    /**
     * Testing the return value of the getShortDescription() method. 
     */
    @Test
    public void doDescriptionTest(){
        assertEquals(officeBoss.getShortDescription(), "in the office of your boss");//see if the method returns the right information
    }
    
    /**
     * Testing if images can be loaded and displayed. 
     */
    @Test
    public void doImageTest(){
         BufferedImage img = officeBoss.getImg();
         
         //some JOptionPane to display the image
         JOptionPane.showMessageDialog(null,
         null,
         "Img test",
         JOptionPane.INFORMATION_MESSAGE,
         new ImageIcon(img));
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
