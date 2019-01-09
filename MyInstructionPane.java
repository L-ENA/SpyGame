import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This extended JPanel class is used to focus the players attention on one single piece of information. It has a
 * relatively simple design and only displays two Strings: a title and a messsage. The only possible action is a  click on the ok button.
 * It is used to display the initial objective of the game, the solution to the quiz questions and to display the final statistics
 *
 * @205232
 * @08.01.2019
 */
public class MyInstructionPane extends JPanel
{
    // instance variables - replace the example below with your own
    private Border standardBorder;
    private Border contentBorder;
   
    private Font font;
    private ActionListener listener;
    
    private JLabel infoText;
    private JButton okButton;
    private JPanel contentPanel;
   
    /**
     * Constructor for objects of class MyInstructionPane
     */
    public MyInstructionPane(Border standardBorder, Border contentBorder, ActionListener listener)
    {
        super();
        this.standardBorder = standardBorder;//adding the same border style to all titled border components
        this.contentBorder = contentBorder;//adding the same border style to all titled border components
        font = new Font("Serif", Font.ITALIC, 13);
        
        this.listener = listener;
        
        this.setBackground(new Color(153, 255, 153));
        
       
        makeContent();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private void makeContent()
    {
        contentPanel= new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));//here, a box layout makes sense to display labels below each other
        contentPanel.setBackground(Color.white);
        contentPanel.setBorder(new TitledBorder(standardBorder, "To be updated at runtime", TitledBorder.RIGHT,TitledBorder.BELOW_TOP, font, Color.black));
        contentPanel.add(Box.createRigidArea(new Dimension(0,15)));
        infoText = new JLabel("Some info, to be updated at runtime.");
        contentPanel.add(infoText);
        contentPanel.add(Box.createRigidArea(new Dimension(0,15)));
        okButton = new JButton("Got it!");
        okButton.setBorder(contentBorder);
        okButton.setBackground(Color.LIGHT_GRAY);//Black By Default
        okButton.setForeground(Color.BLACK);//Set as a Gray Colour
        okButton.setActionCommand("ok");//each button has a string as action command
        okButton.addActionListener(listener);//adds the action listener
        contentPanel.add(okButton);
        add(contentPanel);
    }
    
    /**
     * 
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    protected void updateContent(String title, String message){//setting message and title
        contentPanel.setBorder(new TitledBorder(standardBorder, title, TitledBorder.RIGHT,TitledBorder.BELOW_TOP, font, Color.black));
        infoText.setText(message);
    }
}
