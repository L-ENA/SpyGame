import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Questions represent the challenges in this game. This class is responsible for asking questions by reading info 
 * from a text file and creating/storing question objects from that in a list. Instructions for file reading from: https://www.mkyong.com/java/java-read-a-text-file-line-by-line/
 *
 * @author LS
 * @version 30.10.18
 * @throws IOException
 */
public class QuestionManager
{
    // instance variables - replace the example below with your own
    public static int lifes = 3;//in case that question lists to different topics are introduced this allows to deduce from same pool of lifes
    private ArrayList<Question> questionList;
    private Question thisQuestion;
    private Pattern p;
    private Scanner reader;
    
    /**
     * Constructor for objects of class QuestionManager
     */
    public QuestionManager()
    {
        reader = new Scanner(System.in);//to evaluate the user's answers
        p = Pattern.compile("[abcd]");//regex to evaluate input
        
        String[] parts;
        int counter = 0;
        try {

            questionList = new ArrayList<Question>();
            File f = new File("questions.txt");                         //creates file object from the given link
            BufferedReader b = new BufferedReader(new FileReader(f));   //creates buffered reader object to access the file
            String currentLine = "";                                    //empty String that is going to store a line of the file 
            while ((currentLine = b.readLine()) != null) {              //as long as there are new lines in the file 
                counter++;
                try{
                    parts = currentLine.split("%");                         //make a question from each line by first splitting info in 4 parts (% is used in txt file to divide)
                    
                    if(parts.length != 4){
                        throw new QuestionException(currentLine, counter);//throws potential question parsing exception and describes line with error plus line number
                    } else{
                        questionList.add(new Question(parts[0],parts[1],parts[2],parts[3]));//builds new question and adds it to the list
                    }
                    
                } catch (QuestionException qestEx){//catches custom question exception
                    qestEx.describeError();
                }
                
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Game was interrupted while trying to access questions file.");
        }
    }

    /**
     * Asking a question: random question from question list. Uses evaluate() method to determine if the answer is correct and delets the question after use
     *
     * @param  none
     * @return   boolean false if question was answered wrong. This is determined by the evaluate() method
     */
    public boolean ask()
    {
        Random rand = new Random();
        
        if(questionList.size()>0){
            int randomQuestionIndex = rand.nextInt(questionList.size());        //Create random index for questions currently in the list.
            thisQuestion = questionList.get(randomQuestionIndex);               //get random question
            questionList.remove(randomQuestionIndex);
            thisQuestion.printQuestion();                                       //asks question
            return evaluate(10);
            
        } else{
            System.out.println("Never mind");
            return(true);
        }
        
    }
    
    /**
     * Evaluating a question: if user input matches the right answer, true is returned. If answer is wrong, false is returned. Recursion is used until the user enters a valid input, and terminated after 10 trys. in this case, false is returned 
     *
     * @param  none
     * @return   boolean false if question was answered wrong. This is determined by the evaluate() method
     */
    public boolean evaluate(int counter){
        if (counter>0){
            String inputLine = reader.nextLine().toLowerCase();
            Matcher m = p.matcher(inputLine);
            if (m.find()) {
                if(inputLine.equals(thisQuestion.getAnswer())){
                    System.out.println(thisQuestion.getPosComment());
                    return true;
                } else{
                    System.out.println(thisQuestion.getNegComment());
                    System.out.println("You lost 1 life!");
                    return false;
                }
                
                
            } else{
                System.out.println("Please enter either a, b, c, or d to answer this question!");//user gave invalid input. Therefore, they get instructions
                return evaluate(counter-1);//user gets another chance to enter a valid input
            }
        } else{
            return false;
        }
        
    }
}
