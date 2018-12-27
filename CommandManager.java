
/**
 * Write a description of class CommandManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CommandManager

{
    private Parser parser;//parsing user input. Take from game class
    private boolean isBreak;
    private boolean wantToQuit;
    private RoomManager roomMan;
    /**
     * Constructor for objects of class CommandManager
     */
    public CommandManager(boolean isBreak, RoomManager roomMan)
    {
        parser = new Parser();
        wantToQuit = false;
        this.isBreak = isBreak;
        this.roomMan = roomMan;
    }
    
    protected RoomManager analyseInput(){
        Command command = parser.getCommand();//receive new input
        processCommand(command);//analyse input and execute the input commands
        return roomMan;//returns updated room manager. 
    }    

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command) 
    {
        

        CommandWord commandWord = command.getCommandWord();//to determine which action needs to be taken. 

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean... ");
            wantToQuit = false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {//player wants to change rooms. Since access depends on both locked doors and
            goRoom(command);   
        }
        else if (commandWord == CommandWord.QUIT) {
            quit(command);//executes the quit function, which updates the wantToQuit attribute
        }
        // else command not recognised.
        
    }


    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     * @param none
     * @return void
     */
    private void printHelp() 
    {
        System.out.println("Be careful, they are on to you!\n");//printing some sentences + available commands
        System.out.println("Your command words are:");
        parser.showCommands();
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

        String direction = command.getSecondWord();//exit name where player wants to go
        
        
        // Try to leave current room.
        if(isBreak==false){//it is not breaktime. Therefore, the player won't be able to sneak into offices.
            roomMan.noBreaktimeMoving(direction);
        } else{//it is breaktime, so there are no restrictions for offices (unless the door of the office is locked)
            roomMan.enterRoom(direction);
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param Command to evaluate if player wants to quit the game
     * @return boolean true, if this command quits the game, false otherwise.
     */
    private void quit(Command command) 
    {
        if(command.hasSecondWord()) {//since there is no second word expected for quitting the game.
            System.out.println("Quit what?");
            wantToQuit = false;
        }
        else {
            wantToQuit = true;  // signal that they want to quit
        }
    }
    
    public boolean getQuit(){
        return wantToQuit;
    }
}
