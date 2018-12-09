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
 * from a text file and creating/storing question objects from that in a list.
 *
 * @author 21821570
 * @version 0.1, 22.11.18
 */
public class QuestionManager
{
    // instance variables
    private ArrayList<Question> questionList;
    private Question thisQuestion;
    private Pattern p;//for input validation
    private Scanner reader;//to get user input
    
    /**
     * Constructor for objects of class QuestionManager. Parses the question file and adds questions. Initialises the reader and pattern objects. 
     */
    public QuestionManager()
    {
        reader = new Scanner(System.in);//to evaluate the user's answers
        p = Pattern.compile("^[abcd]$");//regex to evaluate input - matches single a,b,c, or d.
        
        String[] parts;//holds input line from text file after it was split in its 4 parts
        int counter = 0;//param for parsing exception
        try {
            questionList = new ArrayList<Question>();                   //List where all parsed questions will be stored
            File f = new File("questions.txt");                         //creates file object from the given link
            BufferedReader b = new BufferedReader(new FileReader(f));   //creates buffered reader object to access the file
            String currentLine = "";                                    //empty String that is going to store a line of the file 
            while ((currentLine = b.readLine()) != null) {              //as long as there are new lines in the file 
                counter++;                                              //counts line that is currently being parsed
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
            System.out.println("Game was interrupted while trying to access questions.txt file in the game's directory. Please make sure it exits.");
        }
    }

    /**
     * Asking a question: random question from question list. Uses evaluate() method to determine if the answer is correct and delets the question after use
     *
     * @param none
     * @return boolean false if question was answered wrong. This is determined by the evaluate() method
     */
    public boolean ask()
    {
        Random rand = new Random();
        
        if(questionList.size()>0){                                              //if there are questions left
            int randomQuestionIndex = rand.nextInt(questionList.size());        //Create random index for questions currently in the list.
            thisQuestion = questionList.get(randomQuestionIndex);               //get random question
            questionList.remove(randomQuestionIndex);
            thisQuestion.printQuestion();                                       //asks question
            return evaluate(10);//param is numbers of times that user is allowed to pass an invalid input
            
        } else{
            System.out.println("Never mind, I forgot what I wanted to ask...");
            return(true);//not loosing a life here
        }
        
    }
    
    /**
     * Evaluating a question: if user input matches the right answer, true is returned. If answer is wrong, false is returned. Recursion is used until the user enters a valid input, and terminated after 10 trys. in this case, false is returned 
     *
     * @param none
     * @return boolean rturns false if question was answered wrong. This is determined by the evaluate() method
     */
    public boolean evaluate(int counter){
        if (counter>0){//user entered invalid input less than 10 times
            String inputLine = reader.nextLine().toLowerCase();//user can enter A instead of a. All converted to lowercase
            Matcher m = p.matcher(inputLine);//to match pattern defined above with input line
            if (m.find()) {//validation of input line
                if(inputLine.equals(thisQuestion.getAnswer())){//answered correctly since input equals answer string
                    System.out.println(thisQuestion.getPosComment());
                    return true;
                } else{//answered wrongly: false is returned to subtract a life
                    System.out.println(thisQuestion.getNegComment());
                    System.out.println("You lost 1 life!");
                    return false;
                }
                
            } else{//recursion until valid input was entered
                System.out.println("Please enter either a, b, c, or d to answer this question!");//user gave invalid input. Therefore, they get instructions
                return evaluate(counter-1);//user gets another chance to enter a valid input
            }
        } else{//entered invalid input 10 times, so now they loose a life
            return false;
        }
        
    }
}
