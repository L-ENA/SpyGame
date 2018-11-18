
import java.util.Random;
/**
 *  This class is the main class of the Spy game application. 
 *  It is a very simple, text based adventure game about a spy in a software development company. The player is a spy who has to find some blueprints
 *  while his suspicions colleagues interrogate them.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 *  Available on GitHub: https://github.com/L-ENA/SpyGame.git
 *  
 * @author  Lena Schmidt, based on code developed by Michael Kolling and David J. Barnes
 * @version 0.1
 * @param
 * @return void
 * 
 */
//test
public class Game 
{
    private Parser parser;
    private RoomManager roomMan; //for access to the map
    private ColleagueManager colleagueMan; // for access to the colleagues
    private Random rand = new Random();
    
    private int lifes = 3;
    /**
     * Class constructor
     * Create the game and initialise the room manager and the colleague manager (access to the quiz functionalities)
     * 
     * @param none
     */
    public Game()
    {
        //instantiates objects used to control gameflow
        parser = new Parser();
        roomMan = new RoomManager();
        colleagueMan = new ColleagueManager();
        
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
        int teabreak = 3;                              //counter to determine if it is lunchtime
        int timeUntilFinished = 0;                          //increments every step until game is over
        
        while (! finished) {
            
            
            if (teabreak % 6 == 0){                        // if teabreak counter reaches 8 the user is warned
                System.out.println("----Soon everybody will come out of their offices for a tea break! Beware!----");
                teabreak = 0;                              // resets teabreak counter 
                
            } else if (teabreak == 1 || teabreak == 2){ //it is teatime, so there is a risk to encounter a colleague
                
                    if (rand.nextInt(5) < 3){//there is a potential (3/5) interaction with a colleague before the next move defined in this method
                    if(colleagueMan.meetColleague()==false){//the colleague asks a question. if user answers wrong they loose a life
                        lifes--;
                        System.out.println("You have " + lifes + " left.");
                        if(lifes == 0){//game over
                            break;
                        }    
                    }
                    System.out.println(roomMan.getCurrentRoomLong());//for re-orientation after the questioning. will be unncecessary once there is a GUI
                }
                
            }                                   //people are working now, so there is no chance of meeting a colleague
            
            Command command = parser.getCommand();
            finished = processCommand(command);
            
            teabreak ++;
            timeUntilFinished++;
        }
        if(lifes ==0){
            colleagueMan.endGame();
            System.out.println("You survived for " + timeUntilFinished + " steps.");
        } else{
            System.out.println("Thank you for playing. Good bye.");
        }
        
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
        System.out.println("If you get the wrong answer too often, they might raise an alarm and your cover is gone.");
        System.out.println("At teatime, they swarm out of their offices, so be prepared (or lock yourself in the loo...)\n");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(roomMan.getCurrentRoomLong());
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
            roomMan.goRoom(command);
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
