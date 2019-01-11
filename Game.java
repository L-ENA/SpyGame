import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *  This class is the main class of the Spy game application. 
 *  It is a very simple, text based adventure game about a spy in a software development company. The player is a spy who has to find some blueprints
 *  while his suspicions colleagues interrogate them.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the GUI instance and starts the game.
 * @205232
 * @11.01.2019
 */
public class Game implements ActionListener 
{
    private RoomManager roomMan; //for access to the map
    private ColleagueManager colleagueMan; // for access to the colleagues
    private Random rand = new Random();//introduce some variation for quiz functions
    private int lifes, trust, rightAnswers, teabreak, timeUntilFinished;    //ints to determine statistics and game cycle
    private boolean isBreak, won, task1;//if it is break, the user won, or if task was given
    private Gui myGui;//The graphical user interface
    private String messageToUser;
    private String roomMessage;
    /**
     * Class constructor
     * Create the game and initialise the room manager(map),the colleague manager (access to the quiz functionalities), the initial game settings such as lifes and displays the objective of the game.
     * @param none
     * @return void
     */
    public Game()
    {
        colleagueMan = new ColleagueManager();
        setGame();
    }
    /**
     * Initialises the room manager(map),the colleague manager (access to the quiz functionalities), the initial game settings such as lifes and displays the objective of the game. This method is used in the constructor and once the game is re-started by the player.
     * @param none
     * @return void
     */
    private void setGame(){
        roomMan = new RoomManager();
        /////////////////////////////////////////////initial setup
        lifes = 3;//lifes a player has left
        trust = 0;//trust level the player gained
        rightAnswers = 0;//correct answers given in quizzes so far
        teabreak = 4;    //counter to determine teabreak cycle
        isBreak = false;//is it breaktime?
        won = false;//did the player satisfy the winning condition?
        timeUntilFinished = 0;//increments every step until game is over
        task1 = false;//task 1 was not given yet
        messageToUser="";//whenever the message oard should contain some info 
        myGui = new Gui(this);//give this as action listener. Therefore, if the direction buttons in the Gui are pressed, the action is performed here. 
        int[] stats = {timeUntilFinished,trust,lifes};//following lines are for updating the GUI. This is the initial setup
        roomMessage = "";
        myGui.updateMessageBoard("Everybody is working", 3);//Some initial message
        String intro = ("<html>&nbsp;You are a secret agent on an undercover mission. "//The info displayed on the first screen
        +"&nbsp;<br>&nbsp;OBJECTIVE:"
        +"&nbsp;<br>&nbsp;Your objective is to steal blueprints for the world's first sonic screwdriver made by humans."
        +"&nbsp;<br>&nbsp;You were hired as a java developer in the company that produces it. "
        +"&nbsp;<br>&nbsp;But your colleagues are suspicios.&nbsp;<br>&nbsp;If you meet one  of them, they might ask questions to check if you really are who you pretend to be.... "
        +"&nbsp;<br>&nbsp;If you get the wrong answer too often, they might raise an alarm and your cover is gone."
        +"&nbsp;<br>&nbsp;At teatime, they swarm out of their offices, so be prepared  (or hide in the loo).");
        myGui.updateInstructionPane(intro, "Welcome to the Spy Game!");
        myGui.switchPanes(3);
    }
    /**
     * Class constructor
     * This is an empty constructor that was used by the gui testing classes during the development and is not of interest
     * for the actual game.
     * @param boolean To call the modified constructor
     */
    public Game(boolean testing){
        roomMan = new RoomManager();//for testing purposes
    }
    /**
     *  This is the main event handler for this game. There are 3 types of action commands in this game and this method 
     *  decides which actions are performed for each command.
     *  @param ActionEvent The event with its associated action command.
     *  @return void
     */
    public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().matches("[abcd]")){//a quiz answer was given
                questionAction(e);//execute quiz actions
           } else if(e.getActionCommand().equals("ok")){//the instruction pane is exited
                if(lifes ==0){//player answered too many questions wrong, the game ends here
                    String addedInfo = ("\n\nYou survived for " + timeUntilFinished + " steps and answered "+ rightAnswers + " questions correctly. Your final trust score is "+trust + ".");
                    boolean ret = myGui.exitMessage(colleagueMan.endGame() + addedInfo, "YOU LOST!", false);
                    if(ret == true){//restart game
                    myGui.killGui();
                    setGame();
                    } else{
                        System.exit(0);//ending the game
                    }
                } else {
                    myGui.updateMain(new int[]{timeUntilFinished,trust,lifes}, roomMan.getCurrentRoomImg(), roomMan.getCurrentExitSet(), roomMan.getCurrentRoomShort(), roomMessage);
                    myGui.switchPanes(1);//switch back to the main pane
                }
            } else {//there is a change in rooms, with the new direction as action command
                roomAction(e);
            }
    }
    /**
     * This method carries out the action in response to a given quiz answer. The given answer, indicated by the ActionCommand, is evaluated and the stats and GUI are updated accordingly.
     * @param  ActionEvent  The action that contains the given answer
     * @return    void
     */
    private void questionAction(ActionEvent e){
        if(!colleagueMan.evaluate(e.getActionCommand())){//evaluating the given answer. If wrong, lifes etc are taken. If right, trust is gained.
                lifes--;//looses both a life and general trust
                trust --;
                myGui.updateInstructionPane(colleagueMan.getNeg(), "Your answer is wrong");//updating the instruction pane according to the quiz outcome
            } else {
                trust++;//incrementing trust
                rightAnswers++;//incrementing number of right answers for storyline
                myGui.updateInstructionPane(colleagueMan.getPos(), "Your answer is right");//updating the instruction pane according to the quiz outcome
            }
        myGui.switchPanes(3);//switching to the instruction pane to communicate quiz outcome  
    }
    /**
     *  This is the substitute of the original play loop. When the player chooses to go to another room, an ActionEvent is passed and its associated String 
     *  tells the updating methods and the GUI how to update the screen. 
     *  @param ActionEvent The event with its associated action command which represents the chosen direction.
     *  @return void
     */
    private void roomAction(ActionEvent e){
            String direction = e.getActionCommand();//the exit associated with the clicked button
            teabreak ++;//increment teabreak counter 
            timeUntilFinished++;//counts steps that the player took
            int[] stats = {timeUntilFinished,trust,lifes};//following lines are for updating the GUI.
            roomMessage = roomMan.goRoom(isBreak, direction);//updating the roomManager by making it try to change the room towards the direction. If barriers are encountered they are described in the message. 
            myGui.updateMain(stats, roomMan.getCurrentRoomImg(), roomMan.getCurrentExitSet(), roomMan.getCurrentRoomShort(), roomMessage);
            storyLine();//applies the storyline method. Actions are performed if the right answers attribute if this class reaches 4
            /////////////////////////////////////////winning condition
            if(winGame() == true){//player won the game. It may end here.
                String addedInfo = ("\nYou took " + timeUntilFinished + " steps and answered "+ rightAnswers + " questions correctly. Your final trust score is "+trust + ".");
                boolean ret = myGui.exitMessage("You take the elevator. There are no buttons...\nThe elevator takes you down and when the door opens you realise that you are in the strongroom.\nThe blueprints are on the table!\nYou grab them and leave the building without attracting any attention."
                    +addedInfo, "YOU WON!", true);
                if(ret == true){//restart game
                    myGui.killGui();
                    setGame();
                }  else{
                    System.exit(0);
                }  
            }
            if (teabreak % 6 == 0){                        // if teabreak counter reaches 6 the user is warned
                myGui.updateMessageBoard("It's teatime soon", 2);//changing the breaktime message board. The int stands for a colour scheme.
                teabreak = 0;                              // resets teabreak counter for new cycle
            } else if (teabreak == 1 || teabreak == 2|| teabreak ==3){ //it is teatime, so there is a risk to encounter a colleague
                myGui.updateMessageBoard("It's teatime now!", 1);
                if (testRoom() == false){//testing if the user is in the saferoom or the loo
                    isBreak = true;//its breaktime, so there are potential encounters + some rooms can be accessed.
                    breaktimeActions();//executes potential quiz activities
                }
            } else{//people are working now, so there is no chance of meeting a colleague
                isBreak = false;
                myGui.updateMessageBoard("Everybody is working", 3);
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
            myGui.updateQuiz(colleagueMan.encounter(), colleagueMan.questionAsk());
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
     * Applying the storyline. Depending on how many questions the player answered right/wrong, actions that change the settings in the game are performed. For now there is only one task, bit more could be added.
     * @param none
     * @return void
     */
    private void storyLine()
    {
        if(task1 == false){//checks if task was given before. 
            if(trust == 3){//This is the task that unlocks the office of the boss.
            task1 = true;//indicates that the task was given
            System.out.println("Task given");
            myGui.updateInstructionPane(colleagueMan.task(), "New mission received");//get the task to leave docs in the office of the boss.
            myGui.switchPanes(3);//switching to the info pane 
            System.out.println("switch completed");
            roomMan.gainAccess();//unlocks the door to the office.
            trust++;//gain some trust.
            }
        }////here, more tasks can be added, or actions triggered by stats, location, item...
    }
    /**
     * Accessor for the roomMan. This method is only used by the GUI test class in order to quickly check how the gui looks like.
     * @param none
     * @return RoomManager
     */
    protected RoomManager getRoomMan(){
        return roomMan;
    }
}