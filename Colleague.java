
/**
 * Colleagues are the enemies in this game. They are used in the challenges by 'asking' quiz questions, and the game
 * is ended if too many answers are wrong.
 * 
 * Its attributes are:
 *      - String name for identification
 *      - boolean gender for gender
 * 
 *
 * @205232
 * @08.01.2019
 * 
 * 
 */
public class Colleague
{
    
    private String name;
    private boolean gender;
    
    /**
     * Constructor for objects of class Colleague. Assigns parameters to attributes.
     * @param String  a String object representing this colleague's name
     * @param boolean represents the colleague's gender -> true for femle, false for male
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
