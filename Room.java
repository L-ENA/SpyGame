import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Class Room - a room in the Spy game.
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room. It owns a door object, which stores information about locks on the room's exits.
 * Each room also has an associated picture for the GUI.
 * 
 * @author 21821570
 * @version 0.1, 22.11.18
 */

public class Room 
{
    //Add some compiled image as attribute by giving path as constructor argument
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private Doors doors;
    private BufferedImage img;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String pathToPic) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
        this.doors = new Doors();
        try{//loading the image file for this room and adjusting its size
            img = ImageIO.read(new File(pathToPic));
            
        } catch (IOException ex){
            System.out.println(pathToPic+ " is not available. Please make sure that it exists in this directory.");
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Accessor for the image.
     * @param none
     * @return BufferedImage The image describing this room.
     */
    public BufferedImage getImg(){
        return img;
    }
    
    /**
     * Accessor for doors attribute
     * @param none
     * @return The doors hashmap for access to locked status
     */
    public Doors getDoors(){
        return doors;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     * @return void
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);//adds an exit
        doors.addLock(direction, true);//adds access status for this exit. defaults to open but can be changed in the course of the game
    }

    /**
     * Returns the short description of this room
     * @param none
     * @return The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @param none    
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @param void
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

