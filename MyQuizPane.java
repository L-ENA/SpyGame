import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The quiz pane provides the GUI for the quiz functionalities of the game. If the player is interrogated, this JPanel is
 * visible and updated with a random quiz question. The player interacts by clicking the answer buttons.  
 *
 * @205232
 * @11.01.2019
 */
public class MyQuizPane extends JPanel
{
    private GridBagConstraints c;
    private Border standardBorder;
    private Border contentBorder;
    private JButton quizButton;
    private Font font;
    private ActionListener listener;
    private JPanel questionPanel;
    private JPanel answerPanel;
    private JLabel questionLabel;
    private JFrame mainFrame;
    
    private static final int wide = 1000;
    private static final int high = 500;

    /**
     * Constructor for objects of class MyQuizPane. The basic layout and style is set up and methods to create sub-panels are called.
     * 
     * @param  Border  the border for the panels
     * @param  Border  the border for the contents
     * @param  ActionListener  the listener instance to connect actions from the quiz panel to the main class
     */
    public MyQuizPane(Border standardBorder, Border contentBorder, ActionListener listener)
    {
        super(new GridBagLayout());
        this.standardBorder = standardBorder;//adding the same border style to all titled border components
        this.contentBorder = contentBorder;//adding the same border style to all titled border components
        font = new Font("Serif", Font.ITALIC, 13);
        c = new GridBagConstraints();//to manage layout
        c.fill = GridBagConstraints.BOTH;//stretch both horizontally and vertically
        c.weighty = 1;//sets size of this panel compared with other panels: they all stretch over the whole height
        this.listener = listener;
        makeQuestionPanel();
        makeAnswerPanel();
       
    }

    /**
     * Method to create the question space of this panel.
     *
     * @param  none
     * @return    void
     */
    private void makeQuestionPanel()
    {
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));//here, a box layout makes sense to display labels below each other
        questionPanel.setBackground(Color.white);
        questionPanel.setBorder(new TitledBorder(standardBorder, "Question"));//uses the border customized before and adds a title
        questionLabel = new JLabel( "Some question, to be updated at runtime");/////label displaying the Question
        questionLabel.setBorder(contentBorder);
        questionPanel.add(Box.createRigidArea(new Dimension(0,15)));//some space between the components
        questionPanel.add(questionLabel);
        
        c.weightx = 0.7;//sets size of this panel compared with other panels: this one is slightly bigger in x direction
        c.gridx = 0;//grid coordinates where the panel will sit
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;//if window is resized
        
        add(questionPanel, c);//add the panel to this container panel.
        
    }
    
    /**
     * Method to create the answer space of this panel.
     *
     * @param  none
     * @return    void
     */
    private void makeAnswerPanel()
    {
        answerPanel = new JPanel();
        answerPanel.setLayout(new GridLayout(0,2, 10, 10));//This gives spaces of even sizes, since there will be 4 answer buttons this is perfect.
        answerPanel.setBackground(Color.white);
        answerPanel.setBorder(new TitledBorder(standardBorder, "Answers"));//uses the border customized before and adds a title
        
        
        String[] options = {"a","b","c","d"};//////creating the answer buttons
        for(String option: options){//add button and functionalities by looping through the exit set of the current room
            quizButton = new JButton(option);//each butten will display a possible answer.
            quizButton.setBorder(contentBorder);
            quizButton.setBackground(Color.LIGHT_GRAY);//Black By Default
            quizButton.setForeground(Color.BLACK);//Set as a Gray Colour
            quizButton.setActionCommand(option);//each button has its exit string as action command
            quizButton.addActionListener(listener);//adds the action listener
            answerPanel.add(quizButton);
        }
        answerPanel.add(new JLabel());//for spacing
        answerPanel.add(new JLabel());
        answerPanel.add(new JLabel());
        answerPanel.add(new JLabel());
        
        c.weightx = 0.3;//sets size of this panel compared with other panels: this one is slightly bigger in x direction
        c.gridx = 1;//grid coordinates where the panel will sit
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;//if window is resized
        add(answerPanel, c);
    }
    
    /**
     * Method to update this panel.
     * @param  String  the String describing the encounter.
     * @param  String  the question.
     * @return    void
     */
    protected void updateQuiz(String encounterWith, String quest){
        questionLabel.setText(quest);//updating the question and the title below.
        questionLabel.setBorder(new TitledBorder(contentBorder, encounterWith, TitledBorder.RIGHT,TitledBorder.ABOVE_TOP, font, Color.black));//updating border title to indicate current location
        
        questionPanel.revalidate();//finalising the changes
        questionPanel.repaint();
    }
    
    /**
     * Overwriting the preferred size method.
     * @param  none
     * @return    void
     */
    @Override
    public Dimension getPreferredSize() {
       return new Dimension(wide, high);
    }
    
}
