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
public class MyMainPane extends JPanel
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
    private Font font;
    private JLabel roomInfo;
    
    private String direction;
    private ActionListener listener;

    /**
     * Constructor for objects of class MyMainPane
     */
    public MyMainPane(Border standardBorder, Border contentBorder, ActionListener listener) 
    {
        super(new GridBagLayout());
        this.standardBorder = standardBorder;//adding the same border style to all titled border components
        this.contentBorder = contentBorder;//adding the same border style to all titled border components
        font = new Font("Serif", Font.ITALIC, 13);
        c = new GridBagConstraints();//to manage layout
        c.fill = GridBagConstraints.BOTH;//stretch both horizontally and vertically
        c.weighty = 1;//sets size of this panel compared with other panels: they all stretch over the whole height
        this.listener = listener;
        
        
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
        statsLabel = new JLabel("Some stats, updated at runtime");/////label displaying game stats
        statsLabel.setBorder(contentBorder);
        statsPanel.add(Box.createRigidArea(new Dimension(0,15)));//some space between the components
        statsPanel.add(statsLabel);
        
        infoLabel = new JLabel("Some info");
        infoLabel.setBorder(contentBorder);
        infoLabel.setOpaque(true);//Otherwise the colour can't be changed
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
        picturePanel = new JPanel();
        picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.Y_AXIS));//this layout is better than gridbag here since the rigid areas prevent the overlapping of the titled borders
        picturePanel.setBackground(Color.white);
        picturePanel.setBorder(new TitledBorder(standardBorder, "Location information"));
        
        picturePanel.add(Box.createRigidArea(new Dimension(0,10)));
        picLabel = new JLabel();//placeholder for now. Will be updated once every move
        picLabel.setBorder(contentBorder);
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);//to appear centered if there is enough space
        picturePanel.add(picLabel);
        
        roomInfo = new JLabel();
        roomInfo.setOpaque(true);//Otherwise the colour can't be changed
        roomInfo.setAlignmentX(Component.CENTER_ALIGNMENT);//to appear centered if there is enough space
        
        
        picturePanel.add(Box.createRigidArea(new Dimension(0,15)));
        picturePanel.add(roomInfo);
        
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
        navigationPanel.setBorder(new TitledBorder(standardBorder, "Choose exit"));
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        
        navigationButton = new JButton("To be updated");
        add(navigationPanel, c);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    protected void updating(int[] stats, BufferedImage img, Set<String> exits, String currentShort, String roomMsg)
    //public void updateGUI(BufferedImage roomPicture, int[] stats)
    {
        
        picLabel.setIcon(new ImageIcon(img));//updating the room image by using image of the current room
        picLabel.setBorder(new TitledBorder(contentBorder, "You are " + currentShort, TitledBorder.RIGHT,TitledBorder.ABOVE_TOP, font, Color.black));//updating border title to indicate current location
        if(!roomMsg.equals("")){//the room message infoboard is optional: it just appears if there is anything of interest
            
            roomInfo.setText(roomMsg);
            roomInfo.setBackground(new Color(255, 77, 77));
            roomInfo.setBorder(contentBorder);
            roomInfo.setVisible(true);
        } else{
            roomInfo.setVisible(false);
        }
        picturePanel.revalidate();
        picturePanel.repaint();
        
        statsLabel.setText("<html><br>&nbsp;Steps: "+stats[0]+"&nbsp;<br>&nbsp;Trust: "+stats[1]+"&nbsp;<br>&nbsp;Lifes: "+stats[2]+"<br>&nbsp;</html>");//updating the stat values
        
        
        navigationPanel.removeAll();
        navigationPanel.revalidate();
        navigationPanel.repaint();
        
        
        for(String exit: exits){//add button and functionalities by looping through the exit set of the current room
            navigationPanel.add(Box.createRigidArea(new Dimension(0,15)));
            navigationButton = new JButton(exit);//each butten will display an exit for the room
            navigationButton.setBorder(contentBorder);
            navigationButton.setBackground(Color.LIGHT_GRAY);//Black By Default
            navigationButton.setForeground(Color.BLACK);//Set as a Gray Colour
            navigationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            navigationButton.setActionCommand(exit);//each button has its exit string as action command
            navigationButton.addActionListener(listener);//adds the action listener
            navigationPanel.add(navigationButton);
        }
        
        
        navigationPanel.revalidate();
        navigationPanel.repaint();
        
        
    }
    
    protected void updateMessages(String s, int importance){
        infoLabel.setText(s);
        
        
        switch (importance) {
            case 1:  infoLabel.setBackground(new Color(255, 77, 77));
                     break;
            case 2:  infoLabel.setBackground(new Color(255, 255, 102));
                     break;
            case 3:  infoLabel.setBackground(new Color(204, 255, 153));
                     break;
                    }
        statsPanel.revalidate();
        statsPanel.repaint();            
    }
    
}
