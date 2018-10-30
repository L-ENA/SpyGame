import java.util.ArrayList;
import java.util.Random;
/**
 * The Colleague class manages the colleague's interaction with the game. Here the colleagues are interacting
 * with the questions in order to present challenges in the game.
 * Has attributes
 *      - of type ArrayList<Colleague> to store the colleague instances. 
 *      - of type int to store amount of colleagues
 *      - of type Colleague to represent one instance of Colleague to interact
 * @author LS
 * @version 30.10.18
 */
public class ColleagueManager
{
    // declaration of colleagues
    
    private ArrayList<Colleague> colleagueList;
    private int nrOfColleagues;
    private Colleague colleague;
    
    private Random rand = new Random();

    /**
     * Constructor for objects of class ColleagueManager
     */
    public ColleagueManager()
    {
        colleagueList = new ArrayList<Colleague>();
        //calling method to create the colleagues.
        makeColleagues();
        nrOfColleagues = colleagueList.size();
        
    }

    /**
     * Method to instantiate the colleagues and storing them in the colleagueList for further usage
     * The more colleagues, the easier the game!
     * @param none
     * @return void
     */
    public void makeColleagues()
    {
        // makes new colleagues by adding new Colleague instances to the List
        colleagueList.add(new Colleague("Tom", false));
        colleagueList.add(new Colleague("Mr. Brown", false));
        colleagueList.add(new Colleague("Lucy", true));
        colleagueList.add(new Colleague("Emily", true));
        
        
    }
    
    public void interrogate(){
        
        //to choose a colleague from list: generates random int in the range of colleagueList's size and 
        //picks the colleague defined by index at this random int.
        colleague = colleagueList.get(rand.nextInt(nrOfColleagues)); 
        
        
        if (colleague.isFemale()){
            System.out.println("----Your colleague " +colleague.getName() + " spotted you. She is coming your way...----");
        } else {
            System.out.println("----Your colleague " + colleague.getName() + " spotted you. He is coming your way...----");
        }
        
        
    }
}