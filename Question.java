
/**
 * Questions are asked by colleagues. They test the user's java knowledge. Each instance of this class will
 * represent one question.
 * There are 4 attributes to this class: 
 * A String to store the question,
 * a String to store the answer,
 * a String comment if answer given is right,
 * a String comment if anser given is wrong.
 *
 * @205232
 * @08.01.2019
 */
public class Question implements Cloneable
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
        System.out.println(">>>The following question was asked:");
        String [] questionParts = question.split("[+]");    //splits at + for linebreaks - 
        for(String part : questionParts){
            System.out.println(part);
        }
    }
    
    /**
     * Returns the question String in a JLabel friendly form.
     * @return String The formatted question String
     * @param none
     */
    public String formatQuestion(){
        String [] questionParts = question.split("[+]");    //splits at + for linebreaks - 
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        for(String part : questionParts){
            sb.append("<br>&nbsp;"+part+"&nbsp;<br>");
        }
        sb.append("</html>");
        return sb.toString();
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

    /**
     * 
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    @Override
    protected Question clone() {
        Question clone = null;
        try{
            clone = (Question) super.clone();//casting question and using objects clone method in it
           
        }catch(CloneNotSupportedException e){// catching related exception
            throw new RuntimeException(e); 
        }
       
        return clone;
       
    }



}
