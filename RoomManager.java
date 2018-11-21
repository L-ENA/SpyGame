
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
    
    private Room reception, loo, cafe, groundHallway, firstHallway, office1, office2, officeBoss, kitchen, lab, safeRoom;
    

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
     * Accessor to the current room name's short description.
     * @params none
     * @return String short description of the current room
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
        // initialise room exits
        reception.setExit("cafe", cafe);
        reception.setExit("loo", loo);
        reception.setExit("hallway", groundHallway);
        
        loo.setExit("reception", reception);
        cafe.setExit("reception", reception);
        
        groundHallway.setExit("reception", reception);
        groundHallway.setExit("office", office1);
        groundHallway.setExit("office", office2);
        groundHallway.setExit("upstairs", firstHallway);
        groundHallway.setExit("lab", lab);
        
        firstHallway.setExit("downstairs", groundHallway);
        firstHallway.setExit("office", officeBoss);
        firstHallway.getDoors().addLock("office", false);//sets access for this room: player can't enter
        firstHallway.setExit("kitchen", kitchen);
        
        office1.setExit("hallway", groundHallway);
        office2.setExit("hallway", groundHallway);
        
        officeBoss.setExit("hallway", firstHallway);
        officeBoss.setExit("elevator", safeRoom);
        kitchen.setExit("hallway", firstHallway);
        
        lab.setExit("upstairs", groundHallway);
        lab.setExit("east", safeRoom);
        lab.getDoors().addLock("east", false);  
        
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
        office1 = new Room("in some person's office");
        office2 = new Room("in some person's office");
        officeBoss = new Room("in the office of your boss");
        kitchen = new Room("in the staff kitchen");
        lab = new Room("in the secret screwdriver lab");
        safeRoom = new Room("in the safe room where they store the blueprints. You grab the documents and leave!");
        
        
        
        
    }
    
    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param Command - a command that is evaluated in this method
     * @return void
     */
    protected void goRoom(Command command, int teabreak) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        if(teabreak!=1 && teabreak!=2&& teabreak!=3){//it is not breaktime. Therefore, the player won't be able to sneak into offices
            noBreaktimeMoving(direction);
        } else{//it is breaktime, so there are no restrictions for offices
            enterRoom(direction);
        }
        

        
    }
    
    /** 
     * It is not breaktime. Therefore, only non-offices are accessible
     * @param String - the exit that the user tries to use
     * @return void
     */
    private void noBreaktimeMoving(String direction){
        Room nextRoom = currentRoom.getExit(direction);
        if(direction.equals("office")){
            System.out.println("Looks like there is a meeting "+nextRoom.getShortDescription()+". It won't be smart to go in there now..");
        } else{
            enterRoom(direction);
        }
    }
    
    /** 
     * General method to enter a room
     * @param String - the exit that the user tries to use
     * @return void
     */
    private void enterRoom(String direction){
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if(currentRoom.getDoors().checkStatus(direction) == false){//if the room is locked
            System.out.println("You can't be "+nextRoom.getShortDescription()+". This door is locked...");
        } else {//player can move to the new room
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
}
