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
    
    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param Command - a command that is evaluated in this method
     * @return void
     */
    protected void goRoom(Command command, boolean teabreak) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();//exit name where player wants to go

        // Try to leave current room.
        if(teabreak==false){//it is not breaktime. Therefore, the player won't be able to sneak into offices.
            noBreaktimeMoving(direction);
        } else{//it is breaktime, so there are no restrictions for offices (unless the door of the office is locked)
            enterRoom(direction);
        }
    }
    
    /** 
     * It is not breaktime. Therefore, only non-offices are accessible
     * @param String - the exit that the user tries to use
     * @return void
     */
    private void noBreaktimeMoving(String direction){
        Room nextRoom = currentRoom.getExit(direction);//the room that is attempted.
        if(direction.equals("office")){//check if this room is an office. If it is, the user gets some message, but can't enter
            System.out.println("Looks like there is somebody working "+nextRoom.getShortDescription()+". It won't be smart to disturb them...");
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
            if(currentRoom.equals(safeRoom)){//unless the player is in the final room the description is displayed
                System.out.println("You take the elevator. There are no buttons...\nThe elevator takes you down and when the door opens you realise that you are in the strongroom.\nThe blueprints are on the table!\nYou grab them and leave the building without attracting any attention.");
            } else {
                System.out.println(currentRoom.getLongDescription());
            }    
        }
    }
}