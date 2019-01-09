

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GuiTest. It was used throughout the creation of the GUI to test the look of the GUI without having to start
 * the actual game.
 *
 * @205232
 * @08.01.2019
 */
public class GuiTest
{
    public Game game;
    public Gui gui;
    /**
     * Default constructor for test class GameTest
     */
    public GuiTest()
    {
        game = new Game(true);//empty constructor. This is only for gui testing
        gui = new Gui(game);
    }
    
    /**
     * 
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    @Test
    public void instructionPaneTest(){
        gui.switchPanes(3);
    }
    
    /**
     * 
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    @Test
    public void mainPaneTest(){
        RoomManager roomMan = new RoomManager();
        
        gui.updateMain(new int[]{0,0,3}, roomMan.getCurrentRoomImg(), roomMan.getCurrentExitSet(), roomMan.getCurrentRoomShort(), "Everybody is working");
        gui.switchPanes(1);
    }
    
    /**
     * 
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    @Test
    public void quizPaneTest(){
        gui.switchPanes(2);
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
