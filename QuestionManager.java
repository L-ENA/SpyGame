import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.IllegalArgumentException;
import java.util.Iterator;
/**
 * Questions represent the challenges in this game. This class is responsible for asking questions by reading info 
 * from a text file and creating/storing question objects from that in a list. It is responsible for supplying and deleting questions from
 * the list and for re-filling the list once it has run out.
 *
 * @205232
 * @11.01.2019
 */
public class QuestionManager
{
    private ArrayList<Question> questionList;
    private ArrayList<Question> backupList;
    private Question thisQuestion;
    
    /**
     * Constructor for objects of class QuestionManager. Parses the question file and adds questions. 
     */
    public QuestionManager()
    {
        questionList = new ArrayList<Question>();//the ArrayList for the question objects
        questionParsing();//parses questions from the file and stores them in the backup list
        useQuestionBackup();//clones the question backup list for use in the game
    }
    
    /**
     * This method accesses the questions backup list and clones all the questions into the actual question list that is used
     * throughout the game. Since questions get deleted once they were asked there is a potential for the question list to be empty. If this
     * happens the game can't be won in the worst case, and parsing the file again would be a clumsy solution. Therefore, this method can be used to
     * simply re-fill the question list once it is created or reaches 0 elements.
     * @param none
     * @return void
     */
    private void useQuestionBackup(){
        Iterator<Question> iterator = backupList.iterator();//to traverse the backup list
        while(iterator.hasNext()){
            questionList.add(iterator.next().clone());//creating a clone so that changes on the clone don't affect the original List
        }
    }
    
    /**
     * This method parses questions from the questions file. It is called in the constructor of this class.
     * @param none
     * @return void
     */
    private void questionParsing(){
        String[] parts;//holds input line from text file after it was split in its 4 parts
        int counter = 0;//param for parsing exception
        try {
            backupList = new ArrayList<Question>();                   //List where all parsed questions will be stored
            File f = new File("questions.txt");                         //creates file object from the given link
            BufferedReader b = new BufferedReader(new FileReader(f));   //creates buffered reader object to access the file
            String currentLine = "";                                    //empty String that is going to store a line of the file 
            while ((currentLine = b.readLine()) != null) {              //as long as there are new lines in the file 
                counter++;                                              //counts line that is currently being parsed
                try{
                    parts = currentLine.split("%");                         //make a question from each line by first splitting info in 4 parts (% is used in txt file to divide)
                    
                    if(parts.length != 4){
                        throw new QuestionException(currentLine, counter);//throws potential question parsing exception and describes line number with error plus the content of the line
                    } else{
                        backupList.add(new Question(parts[0],parts[1],parts[2],parts[3]));//builds new question and adds it to the list
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
     * Asking a question: random question from question list and removing it to avoid duplication. 
     * If the questionList is empty, the exception is dealt with.
     * @param none
     * @return String the question
     */
    public String ask()
    {
        Random rand = new Random();
        try{
            int randomQuestionIndex = rand.nextInt(questionList.size());        //Create random index for questions currently in the list.
            thisQuestion = questionList.get(randomQuestionIndex);               //get random question
            questionList.remove(randomQuestionIndex);                           //remove the question so that it can't be asked again
        } catch(IllegalArgumentException iA){//the questionList is empty. The backup is used and a question from the refilled list is picked.
            System.out.println("------- Exception caught: Question list is empty, old questions are re-used. Continue to play or add more questions to the question file and re-start the game.");
            useQuestionBackup();//refill the list by cloning the questions
            int randomQuestionIndex = rand.nextInt(questionList.size());        //Create random index for questions currently in the list in order to set a current question.
            thisQuestion = questionList.get(randomQuestionIndex);               //get random question
            questionList.remove(randomQuestionIndex);
        }
        thisQuestion.printQuestion();                                       //prints question to terminal for the log
        return thisQuestion.formatQuestion();//param is numbers of times that user is allowed to pass an invalid input
    }
    
    /**
     * Evaluating a question: if the given answer matches, true is returned. Otherwise, false.
     *
     * @param String given answer
     * @return boolean rturns false if question was answered wrong
     */
    public boolean evaluate(String givenAnswer){
        if(givenAnswer.equals(thisQuestion.getAnswer())){//answered correctly since input equals answer string
            return true;
        } else{//answered wrongly: false is returned to subtract a life
            return false;
        }
    } 
        
    /**
     * Method that returns the postitve comment for the current question.
     * @param none
     * @return String The comment. 
     */
    public String getPos(){
        return thisQuestion.getPosComment();
    }    
    
    /**
     * Method that returns the negative comment for the current question.
     * @param none
     * @return String The comment. 
     */
    public String getNeg(){
        return thisQuestion.getNegComment();
    } 
}
