
/**
 * Questions are asked by colleagues. They test the user's java knowledge. Each instance of this class will
 * represent one question.
 * There are 4 attributes to this class: 
 * A String to store the question,
 * A String to store the answer
 * A String comment if answer given is right
 * A String comment if anser given is wrong
 *
 * @author LS
 * @version 30.10.18
 */
public class Question
{
    // instance variables - replace the example below with your own
    private String question;
    
    private String answer;
    private String posComment;
    private String negComment;

    /**
     * Constructor for objects of class Question
     * Attributes:
     *  - String question
     *  - char answer
     *  - String positive comment - if user answers right
     *  - String negative comment - if user answers wrong
     * 
     */
    public Question(String question, String answer, String posComment, String negComment)
    {
        // initialise instance variables
        this.question = question;
        this.answer = answer;
        this.posComment = posComment;
        this.negComment = negComment;
    }
    
    /**
     * prints the question String with its options
     * @return void
     * @param none
     */
    public void printQuestion(){
        String [] questionParts = question.split("[+]");    //splits at + - I have problems reading and displaying newline characters directly from text files
        for(String part : questionParts){
            System.out.println(part);
        }
    }
    public String getQuestion(){
        return question;
    }
    /**
     * Accessor for the answer String
     * @return String the answer
     */
    public String getAnswer(){
        return answer;
    }
    
    /**
     * Accessor for the posComment String
     * @return String the posComment
     */
    public String getPosComment(){
        return posComment;
    }
    
    /**
     * Accessor for the negComment String
     * @return String the negative comment
     */
    public String getNegComment(){
        return negComment;
    }

    
}
