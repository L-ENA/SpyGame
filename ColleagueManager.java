import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
/**
 * The Colleague class manages the colleague's interaction with the game. Here the colleagues are interacting
 * with the questions in order to present challenges in the game.
 * Has attributes
 *      - of type ArrayList<Colleague> to store the colleague instances. 
 *      - of type int to store amount of colleagues
 *      - of type Colleague to represent one instance of Colleague to interact
 * @author 21821570
 * @version 0.1, 22.11.18
 */
public class ColleagueManager
{
    private ArrayList<Colleague> colleagueList;//stores instances of colleagues
    private int nrOfColleagues;
    private Colleague colleague;//specific colleague that is used
    private Colleague boss;
    private QuestionManager questionMan;//access to the question list and functionalities
    private Random rand = new Random();

    /**
     * Constructor for objects of class ColleagueManager. Makes the colleagueList and chooses a random colleague to start with.
     */
    public ColleagueManager()
    {
        colleagueList = new ArrayList<Colleague>();
        //calling method to create the colleagues.
        makeColleagues();
        nrOfColleagues = colleagueList.size();
        questionMan = new QuestionManager();
        colleague = colleagueList.get(rand.nextInt(nrOfColleagues));//making sure its never null, since some methods depent on the fact that a colleague was chosen before
        
    }

    /**
     * Private method to instantiate the colleagues and storing them in the colleagueList for further usage
     * @param none
     * @return void
     */
    private void makeColleagues()
    {
        // makes new colleagues by adding new Colleague instances to the List. Params are name and gender
        colleagueList.add(new Colleague("Bill", true));
        colleagueList.add(new Colleague("Rory", false));
        colleagueList.add(new Colleague("Martha", true));
        colleagueList.add(new Colleague("Graham", false));
        colleagueList.add(new Colleague("Clara", true));
        colleagueList.add(new Colleague("Rose", true));
        boss = new Colleague("Your boss", false);//make the boss object that interacts with the player in the future. Could ask some final quiz questions etc or interact in a specific room
        
    }
    
    /**
     * Method that creates a potential encounter with a random colleague from the list
     * @param none
     * @return boolean information whether question was answered correctly. 
     */
    public String encounter(){
            //player meets a colleague and gets interrogated
            //to choose a colleague from list: generates random int in the range of colleagueList's size and 
            //picks the colleague defined by index at this random int.
            nrOfColleagues = colleagueList.size();
            colleague = colleagueList.get(rand.nextInt(nrOfColleagues));
            if (colleague.isFemale()){      //next output is depending on random colleague's gender
                return("Your colleague " +colleague.getName() + " spotted you. She is coming your way and asks:");
            } else {
                return("Your colleague " + colleague.getName() + " spotted you. He is coming your way and asks:");
            }
            
    }
    
    /**
     * Method that returns a random question asked by a colleague.
     * @param none
     * @return String The formatted question String. 
     */
    public String questionAsk(){
        return questionMan.ask();//asks a question, returns boolean true if answered correctly, false if not
    }
    
    /**
     * Method that evaluates the given answer to a question.
     * @param none
     * @return String The formatted question String. 
     */
    public boolean evaluate(String givenAnswer){
        return questionMan.evaluate(givenAnswer);//asks a question, returns boolean true if answered correctly, false if not
    }
    
    /**
     * Method that is called after the player answered some questions correctly
     * @param none
     * @return void 
     */
    public void task(){
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        if (colleague.isFemale()){      //next output is depending on random colleague's gender
                System.out.println(colleague.getName() + " approaches you. She seems to have something on her mind. \nShe says: Hi! I have to leave early today, could you do me a favour and drop these documents in the office of the boss?\nThanks!!!!\nYou take the documents and she leaves...");
            } else {
                System.out.println(colleague.getName() + " approaches you. He seems to have something on his mind. \nHe says: Hi! I have to leave early today, could you do me a favour and drop these documents in the office of the boss?\nThanks!!!!\nYou take the documents and he leaves...");
            }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");    
        ////////////////deleting the colleague that presented this task, since they are leving the office
        Colleague col;
        int delete = 0;
        for(int i =0; i<colleagueList.size();i++){
            col = colleagueList.get(i);
            if(col.getName().equals(colleague.getName())){
                delete = i;//
            }
        }
        colleagueList.remove(delete);//deletes this colleague
    }
    
    /**
     * Method that is called after the player lost their final life. Cover is blown by the colleague who asked last question
     * @param none
     * @return void 
     */
    public void endGame(){
        
        if (colleague.isFemale()){      //next output is depending on random colleague's gender
                System.out.println(colleague.getName() + " looks very suspicious. She walks towards the office of your boss. Your cover is blown! Run!\nGAME OVER");
            } else {
                System.out.println(colleague.getName() + " looks very suspicious. He walks towards the office of your boss. Your cover is blown! Run!\nGAME OVER");
            }
    }
   
}
