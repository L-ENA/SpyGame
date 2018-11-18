import java.util.HashMap;
/**
 * Doors represents exits of a room. It has a hashmap attribute that
 * stores a descriptive String and a boolean value for the status of locking of every exit in this room.
 *
 * @author LS
 * @version 30.10.18
 * 
 */
public class Doors
{
    // instance variables - replace the example below with your own
    private HashMap<String, Boolean> exits;

    /**
     * Constructor for objects of class Door
     */
    public Doors()
    {
        
        exits = new HashMap<String, Boolean>();
    }

    /**
     * Adds an exit to the hashmap
     *
     * @param String description
     * @param Boolean locked status
     * @return void
     */
    public void addDoor(String description, Boolean status)
    {
        exits.put(description, status);
    }
}
