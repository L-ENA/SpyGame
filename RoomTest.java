import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoomTest. It tests functionalities such as locking doors and returning the status of locking.
 *
 * @author 21821570
 * @version 0.1, 22.11.18
 */
public class RoomTest
{
    private Room officeBoss;
    private Room firstHallway;
    /**
     * Default constructor for test class RoomTest
     */
    public RoomTest()
    {
        officeBoss = new Room("in the office of your boss");
        firstHallway = new Room("in the hallway");
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
