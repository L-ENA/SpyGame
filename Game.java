/**
 *  This class is the main class of the Spy game application. 
 *  It is a very simple, text based adventure game.  where do they walk and whats the point?
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Lena Schmidt, based on code developed by Michael Kolling and David J. Barnes
 * @version 0.1
 * @param
 * @return void
 * test for git push
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Class constructor
     * Create the game and initialise its internal map.
     * 
     * @param none
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room reception, loo, cafe, groundHallway, firstHallway, office1, office2, officeBoss, staffRoom, lab, safeRoom;
      
        // create the rooms
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
        
        // initialise room exits
        reception.setExit("east", cafe);
        reception.setExit("west", loo);
        reception.setExit("north", groundHallway);
        
        loo.setExit("east", reception);
        cafe.setExit("west", reception);
        
        groundHallway.setExit("south", reception);
        groundHallway.setExit("east", office1);
        groundHallway.setExit("west", office2);
        groundHallway.setExit("up the stairs", firstHallway);
        groundHallway.setExit("down the stairs", lab);
        
        firstHallway.setExit("down the stairs", groundHallway);
        firstHallway.setExit("east", officeBoss);
        firstHallway.setExit("west", staffRoom);
        
        office1.setExit("west", groundHallway);
        office2.setExit("east", groundHallway);
        
        officeBoss.setExit("west", firstHallway);
        staffRoom.setExit("east", firstHallway);
        
        lab.setExit("up the stairs", firstHallway);
        lab.setExit("east", safeRoom);
        safeRoom.setExit("west", lab);
        
        currentRoom = reception;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;                       //booolean to see if user wants to exit game
        int lunchbreak = 3;                              //counter to determine if it is lunchtime
        
        while (! finished) {
            
            
            if (lunchbreak % 8 == 0){                        // if lunchbreak counter reaches 8 the user is warned
                System.out.println("Soon everybody will come out of their offices for lunch! Beware");
                lunchbreak = 0;
                
                Command command = parser.getCommand();
                finished = processCommand(command);
            } else if (lunchbreak == 0 || lunchbreak == 1){
                System.out.println("It's lunchtime");
                Command command = parser.getCommand();
                finished = processCommand(command);
            } else {                                    //people are working now
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
            
            lunchbreak ++;
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Spy game!");
        System.out.println("You are a secret agent on an undercover mission. \n");
        System.out.println("OBJECTIVE:");
        System.out.println("Your objective is to steal blueprints for the world's first sonic screwdriver made by humans. ");
        System.out.println("You were hired as a java developer in the company that produces it. ");
        System.out.println("But your colleagues are suspicios.\nIf you meet one  of them, they might ask questions to check if you really are who you pretend to be.... ");
        System.out.println("If you get the wrong answer with the same colleague too often, they might raise an alarm and your cover is gone.");
        System.out.println("At lunchtime, they swarm out of their offices, so be prepared (or lock yourself in the loo...)\n");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean... ");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Be careful, they are on to you!\n");
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
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

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
