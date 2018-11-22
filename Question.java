
/**
 * Questions are asked by colleagues. They test the user's java knowledge. Each instance of this class will
 * represent one question.
 * There are 4 attributes to this class: 
 * A String to store the question,
 * a String to store the answer,
 * a String comment if answer given is right,
 * a String comment if anser given is wrong.
 *
 * @author 21821570
 * @version 0.1, 22.11.18
 */
public class Question
{
    
    private String question;
    private String answer;
    private String posComment;
    private String negComment;

    /**
     * Constructor for objects of class Question. Assigning the parameters to the attributes of this class.
     * 
     * @param String question
     * @param String answer
     * @param String positive comment - if user answers right
     * @param String negative comment - if user answers wrong
     * 
     */
    public Question(String question, String answer, String posComment, String negComment)
    {
        this.question = question;//question String with answer options
        this.answer = answer;//correct ansewer: a,b,c, or d
        this.posComment = posComment;//comment user gets when they answer correctly
        this.negComment = negComment;//comment they get if they answer wrong
    }
    
    /**
     * prints the question String with its options
     * @return void
     * @param none
     */
    public void printQuestion(){
        String [] questionParts = question.split("[+]");    //splits at + for linebreaks - 
        for(String part : questionParts){
            System.out.println(part);
        }
    }
    
    /**
     * Access to a queston object
     * @return void
     * @param none
     */
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
