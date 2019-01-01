

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GuiTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
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
    
    
    @Test
    public void instructionPaneTest(){
        gui.switchPanes(3);
    }
    
    @Test
    public void mainPaneTest(){
        gui.switchPanes(1);
    }
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
