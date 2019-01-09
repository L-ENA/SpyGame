
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ColleagueTester. The colleague class is responsible for the interaction with the colleague objects. This
 * represents the main challenge in the game since colleagues are responsible for interrogating the player. Here, all quiz
 * functionalities are called and therefore, this testing class has some methods to do testig on a bigger scale in order to
 * see if all the functionalities are implemented correctly.
 * 
 * @205232
 * @08.01.2019
 */
public class ColleagueTester
{
    
    private ColleagueManager colleagueMan; // for access to the colleagues
    /**
     * Default constructor for test class ColleagueTester. Sets up the colleagueManager object for testing.
     */
    public ColleagueTester()
    {
        
        colleagueMan = new ColleagueManager();
    }
    
    /**
     * Performs the encounter test: 100 encounters with colleagues are simulated to see if the colleagues are random. Results are printed to the console
     */
    @Test
    public void doEncounterTest(){
        for(int i = 0; i<100; i++){
            System.out.println(colleagueMan.encounter());//see if colleague choosing works properly
        }
        
    }
    
    /**
     * Performs the asking test: displays questions. It tries to display more questions than there are questions in the 
     * question arrayList and tests if the illegalArgumentException there is properly dealt with by automatically refilling the List.
     */
    @Test
    public void doAskingTest(){
        for(int i = 0; i<100; i++){
            System.out.println(i+ ": "+colleagueMan.questionAsk());//test if question choosing works properly
        }
        
    }
    
     /**
      *
     * Performs a quiz test: a random question is printed to the terminal. An answer is given automatically and it is tested
     * if the evaluate function returns a boolean and if the reaction to the answer is appropriate.
     */
    @Test
    public void doQuizTest(){
        System.out.println(colleagueMan.encounter());
        System.out.println(colleagueMan.questionAsk());
        if (colleagueMan.evaluate("b")){
            System.out.println(colleagueMan.getPos());
        } else {
            System.out.println(colleagueMan.getNeg());
        }
    }
    
    /**
     * Testing method used for terminal interface.
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
