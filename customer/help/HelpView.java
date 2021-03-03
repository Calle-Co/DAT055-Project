package customer.help;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import customer.MenuPanel;
import global.AllButtons;
import global.AllButtons.size;

public class HelpView extends JPanel {
    private ArrayList<JButton> menuButtons;
    private JScrollPane helpScrollPane;
    private ImageIcon logga2 = new ImageIcon("global/Resources/logga2.JPG");

    public HelpView(){
        setLayout(null);

        menuButtons = new ArrayList<>();

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, 1200, 100);
        for(AllButtons b : menuPanel.getButtons()) {
            menuButtons.add(b);
        }
        add(menuPanel);

        JPanel helpmenuPanel = new JPanel();
        helpmenuPanel.setLayout(null);
        helpmenuPanel.setBounds(20, 120, 300, 500);
        helpmenuPanel.setBackground(Color.white);
        add(helpmenuPanel);

        JLabel manualLabel = new JLabel("Manual");
        manualLabel.setBounds(20, 20, 150, 35);
        manualLabel.setFont(new Font("Verdana", 1, 20));
        helpmenuPanel.add(manualLabel);

        JLabel desthelpLabel = new JLabel("1. Book flight");
        desthelpLabel.setBounds(20, 90, 260, 20);
        desthelpLabel.setFont(new Font("Arial", 1, 18));
        helpmenuPanel.add(desthelpLabel);
        
        AllButtons dest1helpButton = new AllButtons(size.BORDERLESSWHITE, "1.1 destination");
        dest1helpButton.setBounds(20, 115, 260, 30);
        dest1helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        dest1helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(dest1helpButton);
        
        AllButtons dest2helpButton = new AllButtons(size.BORDERLESSWHITE, "1.2 flight");
        dest2helpButton.setBounds(20, 150, 260, 30);
        dest2helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        dest2helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(dest2helpButton);

        AllButtons dest3helpButton = new AllButtons(size.BORDERLESSWHITE, "1.3 seat");
        dest3helpButton.setBounds(20, 185, 260, 30);
        dest3helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        dest3helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(dest3helpButton);

        
        
        JLabel bookinghelpLabel = new JLabel("2. Bookings");
        bookinghelpLabel.setBounds(20, 230, 260, 20);
        bookinghelpLabel.setFont(new Font("Arial", 1, 18));
        helpmenuPanel.add(bookinghelpLabel);

        AllButtons booked1helpButton = new AllButtons(size.BORDERLESSWHITE, "2.1 check booked flights");
        booked1helpButton.setBounds(20, 255, 260, 30);
        booked1helpButton.setHorizontalAlignment(SwingConstants.LEFT);
        booked1helpButton.setFont(new Font("Arial", 0, 15));
        helpmenuPanel.add(booked1helpButton);

        //JLabel profilehelpLabel = new JLabel();

        
        
        /*JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(null);
        instructionPanel.setBounds(340, 120, 830, 500);
        instructionPanel.setBackground(Color.white);*/

        JPanel componentPanel = new JPanel();
        componentPanel.setBackground(Color.WHITE);
        componentPanel.setLayout(new BoxLayout(componentPanel, BoxLayout.Y_AXIS));
        
        /*AllButtons bigassbutton = new AllButtons(size.CUSTOM, "hehe");
        bigassbutton.setMinimumSize(new Dimension(500, 300));
        componentPanel.add(bigassbutton);

        AllButtons bigassbutton2 = new AllButtons(size.CUSTOM, "hehe");
        bigassbutton2.setMinimumSize(new Dimension(300, 500));
        componentPanel.add(bigassbutton2);*/

        //add(componentPanel);
        JLabel label2 = new JLabel(logga2);
        componentPanel.add(label2);

        
        
        helpScrollPane = new JScrollPane(componentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        helpScrollPane.setBounds(340, 120, 830, 500);
        add(helpScrollPane);

        //add(instructionPanel);


    }

    public ArrayList<JButton> getMenuButtons() { return menuButtons; }
}
