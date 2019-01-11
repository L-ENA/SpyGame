

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
     * To test what the updated instruction pane looks like.
     * @param String the message
     * @param String the title
     * @return void
     */
    @Test
    public void instructionPaneUpdateTest(){
        gui.updateInstructionPane("Some updated message...", "someTitle");
        gui.switchPanes(3);
    }
    
    /**
     * To test what the initial main pane looks like.
     * 
     * @return    void
     */
    @Test
    public void mainPaneTest(){
        gui.switchPanes(1);
    }
    
    /**
     * To test what the updated main pane looks like.
     * 
     * @return    void
     */
    @Test
    public void mainPaneUpdateTest(){
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
     *To test what the updated quiz pane looks like.
     * @param String The colleague who asks the question
     * @param String The question
     * @return    void
     */
    @Test
    public void quizPaneUpdateTest(){
        gui.updateQuiz("randColleague spotted you and asks:","Random question.........");
        gui.switchPanes(2);
    }
    
    /**
     *To test the exit message functionality. Click Play again to see if the function returns true. At this point at runtime the game would be restarted.
     * @return    void
     */
    @Test
    public void exitOrPlayAgainTest(){
        gui.updateMain(new int[]{0,0,3}, game.getRoomMan().getCurrentRoomImg(), game.getRoomMan().getCurrentExitSet(), game.getRoomMan().getCurrentRoomShort(), "Everybody is working");//giving some nice background for the 
        gui.switchPanes(1);//making updated pane visible
        assertEquals(gui.exitMessage("Click Play again to make this test succeed!!!", "test", false), true);//show the jpotion pane with the choice
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
