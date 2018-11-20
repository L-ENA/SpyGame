
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ColleagueTester. Tests quiz functionalities
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ColleagueTester
{
    
    private ColleagueManager colleagueMan; // for access to the colleagues
    /**
     * Default constructor for test class ColleagueTester
     */
    public ColleagueTester()
    {
        
        colleagueMan = new ColleagueManager();
    }
    
    @Test
    public void doQuizTest(){
        assertEquals(colleagueMan.encounter(), false);//should return false if answer is not given correctly
    }
    
    @Test
    public void doEndTest(){
        colleagueMan.endGame();//test if statement is printed properly
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
