
/**
 * The RoomManager class manages the room structure of the game. Here rooms are initialised and given purpose
 *
 * @author LS
 * @version 30.10.18
 */
public class RoomManager
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Room reception, loo, cafe, groundHallway, firstHallway, office1, office2, officeBoss, staffRoom, lab, safeRoom;
    

    /**
     * Constructor for objects of class RoomManager
     */
    public RoomManager()
    {
        // initialise instance variables
        createRooms();
        initialiseRooms();
        currentRoom = reception;  // start game in reception room
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
     * Accessor to the current room name's long description.
     * @params none
     * @return String long description of the current room
     */
    public String getCurrentRoomShort(){
        return currentRoom.getShortDescription();
    }
    
    /**
     * Accessor to the loo's short description.
     * @params none
     * @return String short description of the loo
     */
    public String getLooShort(){
        return loo.getShortDescription();
    }
    
    /**
     * Create all links between the rooms.
     * @params none
     * @return void
     */
    private void initialiseRooms(){
        // initialise room exits
        reception.setExit("east", cafe);
        reception.setExit("west", loo);
        reception.setExit("north", groundHallway);
        
        loo.setExit("east", reception);
        cafe.setExit("west", reception);
        
        groundHallway.setExit("south", reception);
        groundHallway.setExit("east", office1);
        groundHallway.setExit("west", office2);
        groundHallway.setExit("upstairs", firstHallway);
        groundHallway.setExit("downstairs", lab);
        
        firstHallway.setExit("downstairs", groundHallway);
        firstHallway.setExit("east", officeBoss);
        firstHallway.setExit("west", staffRoom);
        
        office1.setExit("west", groundHallway);
        office2.setExit("east", groundHallway);
        
        officeBoss.setExit("west", firstHallway);
        staffRoom.setExit("east", firstHallway);
        
        lab.setExit("upstairs", firstHallway);
        lab.setExit("east", safeRoom);
        safeRoom.setExit("west", lab);
    
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
        office1 = new Room("in some person's office");
        office2 = new Room("in some person's office");
        officeBoss = new Room("in the office of your boss");
        staffRoom = new Room("in the staff room");
        lab = new Room("in the secret screwdriver lab");
        safeRoom = new Room("in the safe room where they store the blueprints");
        
        
        
        
    }
    
    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param Command - a command that is evaluated in this method
     * @return void
     */
    protected void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
}
