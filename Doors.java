import java.util.HashMap;
/**
 * Doors represents exits of a room. It has a hashmap attribute that
 * stores a descriptive String (name of the exit) and a boolean value for the status of locking of every exit in this room. A door object is created by default when an instance of Room is created.
 *
 * @author 21821570
 * @version 0.1, 22.11.18
 * 
 */
public class Doors
{
    // storing the locking status of each exit
    private HashMap<String, Boolean> exits;

    /**
     * Constructor for objects of class Door. It initialised the hashmap attribute
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
    public void addLock(String description, Boolean status)
    {
        exits.put(description, status);
    }
    
    /**
     * Returns entry for specific hashmap entry
     *
     * @param String description
     * @return boolean
     */
    public boolean checkStatus(String description){//returns access status, as stored in the hashmap value
        return exits.get(description);
    }
    
}
