import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import java.awt.image.BufferedImage;
import javax.swing.Box;
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
    private JLabel statsLabel;
    private JLabel infoLabel;
    private JLabel picLabel;
    

    /**
     * Constructor for objects of class MyMainPane
     */
    public MyMainPane(Border standardBorder) 
    {
        super(new GridBagLayout());
        this.standardBorder = standardBorder;//adding the same border style to all components
        c = new GridBagConstraints();//to manage layout
        c.fill = GridBagConstraints.BOTH;//stretch both horizontally and vertically
        c.weighty = 1;//sets size of this panel compared with other panels: they all stretch over the whole height
        
        
        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));//here, a box layout makes sense to display labels below each other
        statsPanel.setBackground(Color.white);
        statsPanel.setBorder(new TitledBorder(standardBorder, "Information"));//uses the border customized before and adds a title
        statsLabel = new JLabel();/////label displaying game stats
        statsLabel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.BLACK));
        statsPanel.add(Box.createRigidArea(new Dimension(0,15)));//some space between the components
        statsPanel.add(statsLabel);
        
        infoLabel = new JLabel();
        infoLabel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.BLACK));
        statsPanel.add(Box.createRigidArea(new Dimension(0,15)));
        statsPanel.add(infoLabel);
        
        
        c.weightx = 0.2;//sets size of this panel compared with other panels: this one is slightly small in x direction
        c.gridx = 0;//grid coordinates where the panel will sit
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;//if window is resized
        
        add(statsPanel, c);//add the panel to this panel.
        ///////////////////////////////////////////////////////////////////////////////////////////
        c.weightx = 0.5;
        picturePanel = new JPanel(new GridBagLayout());
        picturePanel.setBackground(Color.white);
        picturePanel.setBorder(new TitledBorder(standardBorder, "Location: "));
        
        picLabel = new JLabel();
        c.anchor = GridBagConstraints.CENTER;//if window is resized
        picLabel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.BLACK));
        picturePanel.add(picLabel);
        
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        add(picturePanel, c);
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        c.weightx = 0.3;
        navigationPanel = new JPanel();
        navigationPanel.setBackground(Color.white);
        navigationPanel.setBorder(new TitledBorder(standardBorder, "Directions"));
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(navigationPanel, c);
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    protected void updating(int[] stats, BufferedImage img)
    //public void updateGUI(BufferedImage roomPicture, int[] stats)
    {
        
        picLabel.setIcon(new ImageIcon(img));//updating the room image
        statsLabel.setText("<html><br>&nbsp;Steps: "+stats[0]+"&nbsp;<br>&nbsp;Trust: "+stats[1]+"&nbsp;<br>&nbsp;Lifes: "+stats[2]+"<br>&nbsp;</html>");//updating the stat values
        infoLabel.setText("Some blabla fr ");
    }
    
    
}
