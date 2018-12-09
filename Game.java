
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
 *  It is based on code from Michael Kolling and David J. Barnes
 *  
 * @author 21821570
 * @version 0.1, 22.11.18
 * 
 * 
 */
//test
public class Game 
{
    private Parser parser;//parsing user input
    private RoomManager roomMan; //for access to the map
    private ColleagueManager colleagueMan; // for access to the colleagues
    private Random rand = new Random();//introduce some variation for quiz functions
    private int lifes;////////////////////////////game stats and loop contols
    private int trust;
    private int rightAnswers;
    private int teabreak;    //counter to determine teabreak cycle
    private boolean isBreak;//is it breaktime?
    private boolean won;//did the player satisfy the winninc condition?
    private int timeUntilFinished;//increments every step until game is over
    private boolean task1;////////////////////////////////for task status
    /**
     * Class constructor
     * Create the game and initialise the room manager(map),the colleague manager (access to the quiz functionalities), and the parser instance.
     * @param none
     */
    public Game()
    {
        //instantiates objects used to control gameflow
        parser = new Parser();
        roomMan = new RoomManager();
        colleagueMan = new ColleagueManager();
        /////////////////////////////////////////////initial setup
        lifes = 3;//lifes a player has left
        trust = 0;//trust level the player gained
        rightAnswers = 0;//correct answers given in quizzes so far
        teabreak = 4;    //counter to determine teabreak cycle
        isBreak = false;//is it breaktime?
        won = false;//did the player satisfy the winning condition?
        timeUntilFinished = 0;//increments every step until game is over
        task1 = false;//task 1 was not given yet
    }
    
    /**
     *  Main play routine. Has main play loop and makes changes to game statistics.  
     *  @param none
     *  @return void
     */
    public void play() 
    {            
        printWelcome();//prints some introduction info to terminal
        
        /////////////////////////////////////resetting game stats in case someone plays twice with the same game instance
        lifes = 3;//lifes a player has left
        trust = 0;//trust level
        rightAnswers = 0;//correct answers
        teabreak = 4;    //counter to determine teabreak cycle
        isBreak = false;//is it breaktime?
        won = false;//did the player satisfy the winninc condition?
        timeUntilFinished = 0;//increments every step until game is over
        boolean finished = false;//booolean to see if user wants to exit game
        task1 = false;//not given yet
        //other tasks are entered here
        
        
       
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        
        while (!finished) {//Loops until end of play: breaks if all lifes are lost, if the player wins, or if they quit.
            storyLine();//applies the storyline method. Actions are performed if the right answers attribute if this class reaches 4
            /////////////////////////////////////////winning condition
            if(winGame() == true){
                won = true;
                break;
            }
            /////////////////////////////////////////
            if (teabreak % 6 == 0){                        // if teabreak counter reaches 6 the user is warned
                System.out.println("----Soon everybody will come out of their offices for a tea break! Beware!----");
                teabreak = 0;                              // resets teabreak counter for new cycle
                
            } else if (teabreak == 1 || teabreak == 2|| teabreak ==3){ //it is teatime, so there is a risk to encounter a colleague
                if (testRoom() == false){
                    isBreak = true;//its breaktime, so there are potential encounters + some rooms can be accessed.
                    breaktimeActions();//executes potential quiz activities
                }
                    
                if(lifes == 0){//game over
                    break;
                }
            } else{//people are working now, so there is no chance of meeting a colleague
                isBreak = false;
            }                                   
            
            Command command = parser.getCommand();//new move
            finished = processCommand(command);//analyse input
            teabreak ++;//increment teabreak counter 
            timeUntilFinished++;//counts steps that the player took
        }
        
        if(lifes ==0){//player answered too many questions wrong
            colleagueMan.endGame();//some terminal output. Cover is blown by last colleague that player was in contact with
            System.out.println("You survived for " + timeUntilFinished + " steps and answered "+ rightAnswers + " questions correctly. Your final trust score is "+trust + ".");
        } else if(won ==true){//winning condition was satisfied
            System.out.println("You took " + timeUntilFinished + " steps and answered "+ rightAnswers + " questions correctly. Your final trust score is "+trust+ ".");
            ///potential for expansion: add method that writes timeUntilFinished, questions correctly answered and trust to some
            ///highscore text file.
        } else{//the player just quit the game without winning or loosing
            System.out.println("Thank you for playing. Good bye.");
        }
        
        
    }
    
    /**
     * This method is called at breaktime, it returns true if the player is in the safe room or the loo.
     * @param none
     * @return boolean The information if player is in one of the spcified rooms or not
     */
    private boolean testRoom(){
        if (roomMan.getCurrentRoomShort().equals(roomMan.getLooShort()) || //comparing room descriptions 
            roomMan.getCurrentRoomShort().equals(roomMan.getSafeShort())){
        return true;//descriptions match, therefore it returns true
        } else {
            return false;//player is somewhere else
        }
    }
    
    /**
     * This method is called at breaktime, it introduces a potential for meeting colleagues who ask quiz questions.
     * @param none
     * @return void
     */
    private void breaktimeActions()
    {
        if (rand.nextInt(3) == 1){//there is a potential interaction with a colleague before the next move defined in this method
            if(colleagueMan.encounter()==false){//random colleague asks a question in the encounter method. If user answers wrong they loose a life
                lifes--;//looses both a life and general trust
                trust --;
                System.out.println("You have " + lifes + " left.");
                            
            } else {
                trust++;//incrementing trust
                rightAnswers++;//incrementing number of right answers for storyline
            }
            System.out.println(roomMan.getCurrentRoomLong());//for re-orientation after the questioning. will be unncecessary once there is a GUI
            }
    }
    
    /**
     * Checking if the player won.
     * @param none
     * @return boolean true if player wins.
     */
    private boolean winGame()
    {
        if(roomMan.getCurrentRoomShort().equals(roomMan.getSafeShort())){//if the user is in the secret lab they win
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Applying the storyline. Depending on how many questions the player answered right/wrong, actions that change the settings in the game are performed. 
     * @param none
     * @return void
     */
    private void storyLine()
    {
        if(task1 == false){//checks if task was given before. 
            if(trust == 3){//This is the task that unlocks the office of the boss.
            task1 = true;//indicates that the task was given
            colleagueMan.task();//get the task to leave docs in the office of the boss.
            roomMan.gainAccess();//unlocks the door to the office.
            trust++;//gain some trust.
        }
        }
        
        ////here, more tasks can be added, or actions triggered by items, once items are added to the game
    }
    
    /**
     * Print out the opening message for the player.
     * @param none
     * @return void
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
        System.out.println("At teatime, they swarm out of their offices, so be prepared  ( or hide in the loo).\n");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(roomMan.getCurrentRoomLong());//orientation for the player: where are they and what are exit options
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();//to determine which action needs to be taken. 

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean... ");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {//player wants to change rooms. Since access depends on both locked doors and
            roomMan.goRoom(command, isBreak);   //time within the game, the teabreak boolean is passed.
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
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
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param Command to evaluate if player wants to quit the game
     * @return boolean true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {//since there is no second word expected for quitting the game.
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that they want to quit
        }
    }
}
