

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
     * Default constructor for test class GameTest. it creates a gui and a game instance for testing.
     */
    public GuiTest()
    {
        game = new Game(true);//empty constructor. This is only for gui testing
        gui = new Gui(game);
    }
    
    /**
     * To test what the instruction pane looks like.
     * 
     * @return    void
     */
    @Test
    public void instructionPaneTest(){
        gui.switchPanes(3);
    }
    
    /**
     * To test what the main pane looks like.
     * 
     * @return    void
     */
    @Test
    public void mainPaneTest(){
        gui.updateMain(new int[]{0,0,3}, game.getRoomMan().getCurrentRoomImg(), game.getRoomMan().getCurrentExitSet(), game.getRoomMan().getCurrentRoomShort(), "Everybody is working");
        gui.switchPanes(1);
    }
    
    /**
     *To test what the quiz pane looks like.
     * 
     * @return    void
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
