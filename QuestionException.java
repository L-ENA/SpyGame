
/**
 * Write a description of class QuestionException here.
 *
 * @author Lena Schmidt
 * @version 12.11.2018
 */
public class QuestionException extends Exception
{
    private static int errorCounter = 1;//static int to indicate how many errors of this type were found
    private String line;//the line with the error
    private int nr;//line nr where error occurred

    /**
     * Constructor for objects of class QuestionException
     */
    public QuestionException(String line, int nr)
    {
        this.line = line;
        this.nr = nr;
        
    }

    /**
     * This method describes potential errors in the process of parsing a question from the questions input file
     *
     * @param  none
     * @return    void
     */
    public void describeError()
    {
         System.out.println("------------------------------Parsing Error nr "+errorCounter+"---------------------------------------");
        System.out.println("There was a problem while parsing line "+ nr +" from the questions input file:");
        System.out.println(">>> "+line);
        System.out.println("Make sure this line contains exactly 3 delimiters in the form of % signs");
        System.out.println("The line with the error was ignored. You can start the game anyway. ");
        System.out.println("-----------------------------------------------------------------------------------");
        errorCounter++;
    }
}
