import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This extended JPanel class is used to focus the players attention on one single piece of information. It adopts the standard design set in the GUI class and only displays two Strings: a title and a messsage. The only possible action is a  click on the ok button.
 * It is used to display the initial objective of the game and the solution to the quiz questions.
 *
 * @205232
 * @11.01.2019
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
    
    private static final int wide = 1000;
    private static final int high = 500;
   
    /**
     * Constructor for objects of class MyInstructionPane. The standard design and the listener are set from the parameters and a method for the content setup is called.
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
     * Creating this panel's content so that in the future it only needs some very simple content updating.
     *
     * @param  none
     * @return    void
     */
    private void makeContent()
    {
        contentPanel= new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));//here, a box layout makes sense to display contents below each other
        contentPanel.setBackground(Color.white);
        contentPanel.setBorder(new TitledBorder(standardBorder, "To be updated at runtime", TitledBorder.RIGHT,TitledBorder.BELOW_TOP, font, Color.black));
        contentPanel.add(Box.createRigidArea(new Dimension(0,15)));
        infoText = new JLabel("Some info, to be updated at runtime.");
        contentPanel.add(infoText);
        contentPanel.add(Box.createRigidArea(new Dimension(0,15)));
        okButton = new JButton("Got it!");//some custom jbutton
        okButton.setBorder(contentBorder);
        okButton.setBackground(Color.LIGHT_GRAY);//button design
        okButton.setForeground(Color.BLACK);
        okButton.setActionCommand("ok");//each button has a string as action command
        okButton.addActionListener(listener);//adds the action listener
        contentPanel.add(okButton);
        add(contentPanel);
    }
    
    /**
     * Updating the panel's content with a title and a message
     * @param  String  title
     * @param  String  message
     * @return    void
     */
    protected void updateContent(String title, String message){//setting message and title
        contentPanel.setBorder(new TitledBorder(standardBorder, title, TitledBorder.RIGHT,TitledBorder.BELOW_TOP, font, Color.black));
        infoText.setText(message);
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
