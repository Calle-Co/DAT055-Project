package admin;

import java.awt.LayoutManager;
import java.util.ArrayList;

import global.*;
import global.AllButtons;
import global.AllButtons.size;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class AdminHomeView extends JPanel{
    //private JFrame f;

    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");

    private JPanel contentPanel;
    private JPanel menuPanel;
    private JPanel sidePanel;
    private JPanel mainPanel;
    private JPanel lowerPanel;
    private JPanel inside;
    private JLabel date;
    private AllButtons homeButton;
    private AllButtons bookingButton;
    private AllButtons logoutButton;
    private AllButtons helpButton;

    private AllButtons destinations;
    private AllButtons customers;
    private AllButtons flights;

    private JLabel ex1;
    private JLabel ex2;
    private JLabel ex3;
    private JLabel ex4;
    private JLabel random;


    public AdminHomeView(){
        contentPanel = new JPanel();

        menuPanel = new JPanel();
        sidePanel = new JPanel();
        mainPanel = new JPanel();
        lowerPanel = new JPanel();
        inside = new JPanel();

        date = new JLabel("--date and time--");
        homeButton = new AllButtons(size.MEDIUM, "Home");
        bookingButton = new AllButtons(size.MEDIUM, "Bookings");
        logoutButton = new AllButtons(size.MEDIUM, "Logout");
        helpButton = new AllButtons(size.MEDIUM, "Help");
        JLabel logo = new JLabel(logga, JLabel.CENTER);
       
        ex1 = new JLabel("ex1");
        ex2 = new JLabel("ex2");
        ex3 = new JLabel("ex3");
        ex4 = new JLabel("ex4");

        destinations = new AllButtons(size.LARGE, "Destinations");
        customers = new AllButtons(size.LARGE, "Clients");
        flights = new AllButtons(size.LARGE, "Flights");

        random = new JLabel("Whatever YOU WANT!");

        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        menuPanel.add(date);
        menuPanel.add(homeButton);
        menuPanel.add(bookingButton);
        menuPanel.add(logoutButton);
        menuPanel.add(helpButton);
        menuPanel.add(logo);

        inside.setLayout(new GridLayout(4,1));
        inside.add(ex1);
        inside.add(ex2);
        inside.add(ex3);
        inside.add(ex4);
        
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        //sidePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //sidePanel.add(Box.createRigidArea(new Dimension(5,0)));
        //sidePanel.setPreferredSize(new Dimension(400, 100));
        //sidePanel.setMaximumSize(new Dimension(400, 100));

        //Border margin = new EmptyBorder(10, 10, 5, 10);
        //sidePanel.setBorder(margin);
        //sidePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sidePanel.add(inside);
 
        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.add(destinations);
        mainPanel.add(customers);
        mainPanel.add(flights);

        lowerPanel.setLayout(new FlowLayout());
        lowerPanel.add(random);

        
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(menuPanel, BorderLayout.NORTH);
        contentPanel.add(sidePanel, BorderLayout.WEST);
        contentPanel.add(mainPanel, BorderLayout.CENTER);
        contentPanel.add(lowerPanel, BorderLayout.SOUTH);
        
        add(contentPanel);
        setVisible(true);
       
    }

    public ArrayList<AllButtons> getButtons() { return buttons; }
    
}