
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ColleagueTester. Tests quiz functionalities by parsing and printing quiz questions on the terminal or printing the end game info on terminal
 *
 * @author 21821570
 * @version 0.1, 22.11.18
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
    
     /**
     * Performs a quiz test: a question is printed to the terminal. Test succeeds if the input is given right
     */
    @Test
    public void doQuizTest(){
        assertEquals(colleagueMan.encounter(), true);//should return false if answer is not given correctly
    }
    
    /**
     * Performs the ending test: Displays text about a colleague blowing the players cover.
     */
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
