import javax.swing.*;
/**
 * This is an exception designed to handle parsing errors related to the question parsing tasks specific to this game.
 *
 * @205232
 * @08.01.2019
 */
public class QuestionException extends Exception
{
    private static int errorCounter = 1;//static int to indicate how many errors of this type were found
    private String line;//the line with the error
    private int nr;//line nr where error occurred
    /**
     * Constructor for objects of class QuestionException. 
     * @param String The line of the text field where the parsing exception occurred
     * @param int The line number where the exception ocurred
     * 
     */
    public QuestionException(String line, int nr)
    {
        super();
        this.line = line;
        this.nr = nr;
        
    }

    /**
     * This method describes potential errors in the process of parsing a question from the questions input file.
     *
     * @param none
     * @return void
     */
    public void describeError()
    {
        
        JOptionPane.showMessageDialog(null, "<html>Parsing Error "+errorCounter+//displays warning message to user
        "<br>There was a problem while parsing line "+ nr +" from the questions input file:"+
        "<br>>>> "+line +
        "<br>Make sure this line contains exactly 3 delimiters in the form of % signs"+
        "<br>The line with the error was ignored. You can start the game anyway.</html>"
        , 
        "Exception while parsing input pile",
        JOptionPane.WARNING_MESSAGE);//type warning message, could also be error message, etc.
        
        errorCounter++;//increment to print correct number for next exception
    }
}
