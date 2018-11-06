import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 * Questions represent the challenges in this game. This class is responsible for creating the questions list by reading info 
 * from a text file and creating/storing question objects from that in a list. Instructions for file reading from: https://www.mkyong.com/java/java-read-a-text-file-line-by-line/
 *
 * @author LS
 * @version 30.10.18
 * @throws IOException
 */
public class QuestionManager
{
    // instance variables - replace the example below with your own
    private ArrayList<Question> questionList;
    private Question thisQuestion;

    /**
     * Constructor for objects of class QuestionManager
     */
    public QuestionManager() throws IOException
    {
        String[] parts = new String[4];
        try {

            questionList = new ArrayList<Question>();
            File f = new File("questions.txt");                         //creates file object from the given link
            BufferedReader b = new BufferedReader(new FileReader(f));   //creates buffered reader object to access the file
            String currentLine = "";                                    //empty String that is going to store a line of the file 
            while ((currentLine = b.readLine()) != null) {              //as long as there are new lines in the file 
                parts = currentLine.split("%");                         //make a question from each line by first splitting info in 4 parts (% is used in txt file to divide)
                questionList.add(new Question(parts[0],parts[1],parts[2],parts[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void ask()
    {
        Random rand = new Random();
        int randomQuestionIndex = rand.nextInt(questionList.size());        //Create random index for questions currently in the list.
        thisQuestion = questionList.get(randomQuestionIndex);               //get random question
        thisQuestion.printQuestion();
        
    }
}
