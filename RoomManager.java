
/**
 * The RoomManager class manages the room structure of the game. Here rooms are initialised and given purpose
 *
 * @author 21821570
 * @version 0.1, 22.11.18
 */
public class RoomManager
{
    private Room currentRoom;
    private Room reception, loo, cafe, groundHallway, firstHallway, office1, office2, officeBoss, kitchen, lab, safeRoom;
    /**
     * Constructor for objects of class RoomManager
     * @params none
     */
    public RoomManager()
    {
        // initialise instance variables
        createRooms();
        initialiseRooms();
        currentRoom = reception;  // start game in reception room
    }
    
    /**
     * Accessor to the current room object.
     * @params none
     * @return Room The crrent room.
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * Mutator for the current room object. When the player is moving, the current room needs to be updated.
     * @params Room The new room object
     * @return void
     */
    public void setCurrentRoom(Room someRoom){
        this.currentRoom = someRoom;
    }
    
    /**
     * Accessor to the current room name's long description.
     * @params none
     * @return String long description of the current room
     */
    public String getCurrentRoomLong(){
        return currentRoom.getLongDescription();
    }
    
    /**
     * Accessor to the current room name's short description.
     * @params none
     * @return String short description of the current room
     */
    public String getCurrentRoomShort(){
        return currentRoom.getShortDescription();
    }
    
    /**
     * Accessor to the safe room's short description.
     * @params none
     * @return String short description of the safe
     */
    public String getSafeShort(){
        return safeRoom.getShortDescription();
    }
    
    /**
     * Accessor to the loo room's short description.
     * @params none
     * @return String short description of the loo
     */
    public String getLooShort(){
        return loo.getShortDescription();
    }
    
    /**
     * Unlocks the office
     * @params none
     * @return void
     */
    public void gainAccess(){
        firstHallway.getDoors().addLock("office", true);//sets access for this room: player can now enter the office
    }
    
    /**
     * Create all links between the rooms.
     * @params none
     * @return void
     */
    private void initialiseRooms(){
        // initialise room exits. Exits are automatically unlocked, for some the exits are locked by calling the addLock method
        reception.setExit("cafe", cafe);
        reception.setExit("loo", loo);
        reception.setExit("hallway", groundHallway);
        
        loo.setExit("reception", reception);
        cafe.setExit("reception", reception);
        
        groundHallway.setExit("reception", reception);
        groundHallway.setExit("office", office1);
        
        groundHallway.setExit("upstairs", firstHallway);
        groundHallway.setExit("downstairs", lab);
        
        firstHallway.setExit("downstairs", groundHallway);
        firstHallway.setExit("office", officeBoss);
        firstHallway.getDoors().addLock("office", false);//sets access for this room: player can't enter
        firstHallway.setExit("kitchen", kitchen);
        
        office1.setExit("hallway", groundHallway);
        officeBoss.setExit("hallway", firstHallway);
        officeBoss.setExit("elevator", safeRoom);
        kitchen.setExit("hallway", firstHallway);
        
        office2.setExit("lab", lab);
        
        lab.setExit("upstairs", groundHallway);
        lab.setExit("strongroom", safeRoom);
        lab.setExit("office", office2);
        
        lab.getDoors().addLock("strongroom", false);  
        
        safeRoom.setExit("lab", lab);
    
    }

    /**
     * Create all the rooms.
     * @params none
     * @return void
     */
    private void createRooms()
    {
        
        // create the rooms with a descriptive String as parameter
        reception = new Room("at the reception");
        loo = new Room("in the loo");
        cafe = new Room("in the cafe");
        groundHallway = new Room("in the ground floor hallway");
        firstHallway = new Room("in the first floor hallway");
        office1 = new Room("in Jack's office");
        office2 = new Room("in Bill's office");
        officeBoss = new Room("in the office of your boss");
        kitchen = new Room("in the staff kitchen");
        lab = new Room("in the secret screwdriver lab");
        safeRoom = new Room("in the strongroom where they store all sorts of secret items.");
        
    }
    
    
}
