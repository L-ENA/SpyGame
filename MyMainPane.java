import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.image.BufferedImage;
import javax.swing.Box;
import java.util.Set;
import java.awt.event.*;
/**
 * Write a description of class MyMainPane here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyMainPane extends JPanel implements ActionListener
{
    GridBagConstraints c;
    private JPanel statsPanel;
    private JPanel picturePanel;
    private JPanel navigationPanel;
    private Border standardBorder;
    private Border contentBorder;
    private JLabel statsLabel;
    private JLabel infoLabel;
    private JLabel picLabel;
    private JButton navigationButton;
    

    /**
     * Constructor for objects of class MyMainPane
     */
    public MyMainPane(Border standardBorder, Border contentBorder) 
    {
        super(new GridBagLayout());
        this.standardBorder = standardBorder;//adding the same border style to all titled border components
        this.contentBorder = contentBorder;//adding the same border style to all titled border components
        c = new GridBagConstraints();//to manage layout
        c.fill = GridBagConstraints.BOTH;//stretch both horizontally and vertically
        c.weighty = 1;//sets size of this panel compared with other panels: they all stretch over the whole height
        
        makeStats();//setup methods for each of the panels
        makePicture();
        makeNavigation();
        
        
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    private void makeStats(){
        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));//here, a box layout makes sense to display labels below each other
        statsPanel.setBackground(Color.white);
        statsPanel.setBorder(new TitledBorder(standardBorder, "Information"));//uses the border customized before and adds a title
        statsLabel = new JLabel();/////label displaying game stats
        statsLabel.setBorder(contentBorder);
        statsPanel.add(Box.createRigidArea(new Dimension(0,15)));//some space between the components
        statsPanel.add(statsLabel);
        
        infoLabel = new JLabel();
        infoLabel.setBorder(contentBorder);
        statsPanel.add(Box.createRigidArea(new Dimension(0,15)));
        statsPanel.add(infoLabel);
        
        c.weightx = 0.2;//sets size of this panel compared with other panels: this one is slightly small in x direction
        c.gridx = 0;//grid coordinates where the panel will sit
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;//if window is resized
        
        add(statsPanel, c);//add the panel to this panel.
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    protected void makePicture(){
        c.weightx = 0.5;
        picturePanel = new JPanel(new GridBagLayout());
        picturePanel.setBackground(Color.white);
        picturePanel.setBorder(new TitledBorder(standardBorder, "Location: "));
        
        picLabel = new JLabel();//placeholder for now. Will be updated once every move
        c.anchor = GridBagConstraints.CENTER;//if window is resized
        picLabel.setBorder(contentBorder);
        picturePanel.add(picLabel);
        
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        add(picturePanel, c);
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    protected void makeNavigation()
    {
        c.weightx = 0.3;
        navigationPanel = new JPanel();
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));//here, a box layout makes sense to display labels below each other
        navigationPanel.setBackground(Color.white);
        navigationPanel.setBorder(new TitledBorder(standardBorder, "Directions"));
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        
        navigationButton = new JButton("empty");
        add(navigationPanel, c);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    protected void updating(int[] stats, BufferedImage img, Set<String> exits)
    //public void updateGUI(BufferedImage roomPicture, int[] stats)
    {
        
        picLabel.setIcon(new ImageIcon(img));//updating the room image by using image of the current room
        statsLabel.setText("<html><br>&nbsp;Steps: "+stats[0]+"&nbsp;<br>&nbsp;Trust: "+stats[1]+"&nbsp;<br>&nbsp;Lifes: "+stats[2]+"<br>&nbsp;</html>");//updating the stat values
        infoLabel.setText("Some blabla fr ");
        
        //for (Component c: navigationPanel.getComponents()){
            //if(c instanceof JButton) {
                //navigationPanel.remove(c);
               // System.out.println("removed" + c.toString());
               // navigationPanel.revalidate();
               // navigationPanel.repaint();
            //}
        //}
        navigationPanel.removeAll();
        navigationPanel.revalidate();
        navigationPanel.repaint();
        
        System.out.println("Exits3: " +exits.toString());
        for(String exit: exits){//add button and functionalities by looping through the exit set of the current room
            System.out.println("added" + exit);
            navigationPanel.add(Box.createRigidArea(new Dimension(0,15)));
            navigationButton = new JButton(exit);//each butten will display an exit for the room
            navigationButton.setActionCommand(exit);//each button has its exit string as action command
            navigationButton.addActionListener(this);//adds the action listener
            navigationPanel.add(navigationButton);
        }
        
        navigationPanel.revalidate();
        navigationPanel.repaint();
    }
    
    /**
     * Overriding the action performed method to carry out actions related to the mainPane.
     *
     * @param  ActionEvent e
     * @return    void
     */
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        System.out.println("Cliiiiiicked :::: " + s);
    }
}
