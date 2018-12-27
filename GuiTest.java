

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
    Gui gui;
    /**
     * Default constructor for test class GuiTest
     */
    public GuiTest()
    {
        
    }
    
    @Test
    public void testSetup(){//quick testing of how initial gui looks like
        gui = new Gui();
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
