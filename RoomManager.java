import java.awt.image.BufferedImage;
import java.util.Set;
/**
 * The RoomManager class manages the room structure of the game. Here rooms are initialised and given purpose. Methods in this class
 * are called in the main Game to check or alter the status of rooms.
 *
 * @205232
 * @08.01.2019
 */
public class RoomManager
{
    private Room currentRoom;
    private Room reception, loo, cafe, groundHallway, firstHallway, office1, office2, officeBoss, kitchen, lab, safeRoom;
    /**
     * Constructor for objects of class RoomManager to call methods which create and set rooms up.
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
     * Unlocks the office
     * @params none
     * @return void
     */
    protected void gainAccess(){
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
     * Create all the rooms by giving descriptive String and picture path.
     * @params none
     * @return void
     */
    private void createRooms()
    {
        
        // create the rooms with a descriptive String as parameter
        reception = new Room("at the reception", "reception.jpg");
        loo = new Room("in the loo", "loo.jpg");
        cafe = new Room("in the cafe", "cafe.jpg");
        groundHallway = new Room("in the ground floor hallway", "hallway.jpg");
        firstHallway = new Room("in the first floor hallway", "hallway.jpg");
        office1 = new Room("in Jack's office", "office.jpg");
        office2 = new Room("in Bill's office", "office.jpg");
        officeBoss = new Room("in the office of your boss","officeBoss.jpg");
        kitchen = new Room("in the staff kitchen", "kitchen.jpg");
        lab = new Room("in the secret screwdriver lab", "lab.jpg");
        safeRoom = new Room("in the strongroom where they store all sorts of secret items.", "safe.jpg");
        
        
    }
    
     /** 
     * Try to go to one direction with regard to the breaktimes.
     * @param boolean  a boolean indicating if it is breaktime.
     * @param String the desired direction.
     * @return String any description of interest in terms of moving between rooms.
     */
    protected String goRoom(boolean isBreak, String direction) 
    {
       
        // Try to leave current room.
        if(isBreak==false){//it is not breaktime. Therefore, the player won't be able to sneak into offices.
            return noBreaktimeMoving(direction);
        } else{//it is breaktime, so there are no restrictions for offices (unless the door of the office is locked in the door class)
            return enterRoom(direction);
        }
    }
    
    /** 
     * It is not breaktime. Therefore, only non-offices are accessible
     * @param String the desired direction.
     * @return String any description of interest in terms of moving between rooms.
     */
    private String noBreaktimeMoving(String direction){
        Room nextRoom = currentRoom.getExit(direction);//the room that is attempted.
        if (nextRoom!=null){//if there is an actual room for the specified exit
            if(direction.equals("office")){//check if this room is an office. If it is, the user gets some message, but can't enter
                System.out.println(">>>Access to office denied");
                return "Looks like there is somebody working "+nextRoom.getShortDescription()+". It won't be smart to disturb them...";
            } else{
                return enterRoom(direction);
            } 
        } else {//some mistake was made when entering the room
            System.out.println("No door found!");
            return "There is no door!";
            
        }
        
    }
    
    /** 
     * General method to enter a room
     * @param String the exit that the user tries to use
     * @return String any description of interest in terms of moving between rooms.
     */
    private String enterRoom(String direction){
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            return("There is no door!");
        } else if(currentRoom.getDoors().checkStatus(direction) == false){//if the room is locked
            return("You can't be "+nextRoom.getShortDescription()+". This door is locked...");
        } else {//player can move to the new room
            currentRoom = nextRoom;
            if(currentRoom.equals(safeRoom)){//unless the player is in the final room the description is displayed in the log
                return("");
            } else {
                System.out.println(currentRoom.getLongDescription());
                return("");
            }    
        }
    }
   
    /**
     * Accessor to a String set for all exits of the current room
     * @param void
     * @return Set<String> the exits
     */
    public Set<String> getCurrentExitSet()
    {
        return currentRoom.getExitSet();
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
     * Accessor to the current room's image object.
     * @params none
     * @return BufferedImage The currrent room's image for GUI.
     */
    public BufferedImage getCurrentRoomImg(){
        return currentRoom.getImg();
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
    
}