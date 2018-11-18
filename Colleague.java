
/**
 * Colleagues are the enemies in this game. They present challenges in form of quiz questions, and the game
 * is ended if too many answers are wrong.
 * 
 * Its attributes are:
 *      - String name for identification
 *      - boolean gender for gender
 * 
 *
 * @author LS
 * @version 30.10.18
 * 
 * @param String name of colleague
 * @param boolean gender for printing output describing  the colleague's actions
 * 
 * 
 */
public class Colleague
{
    
    private String name;
    private boolean gender;
    
    /**
     * Constructor for objects of class Colleague. 
     * @param name - a String object representing this colleague's name
     * @param female - represents the colleague's gender -> true for femle, false for male
     */
    public Colleague(String name, boolean gender)
    {
        this.name = name;
        this.gender = gender;
        
    }
    
    /**
     * The following method is an accessor used for printing information about thie colleague in the 
     * ColleagueManager. It returns a String objct containing the name of this colleague.
     * @return String name
     * @param none
     */
    public String getName(){
        return name;
    }

    /**
     * The following method is an accessor used for printing information about thie colleague in the 
     * ColleagueManager. It returns a boolean containing the info if this colleague is female (it counts as
     * female when true; as male when false).
     * @return boolean gender
     * @param none
     */
    public boolean isFemale(){
        return gender;
    }    
    
}
