package admin;

import java.awt.LayoutManager;
import java.util.ArrayList;

import global.AllButtons;
import global.AllButtons.size;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class AdminHomeView extends JPanel{
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");

    public AdminHomeView(){
        JPanel contentPanel = new JPanel();

        JPanel menuPanel = new JPanel();
        JPanel sidePanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel inside = new JPanel();

        JLabel date = new JLabel("--date and time--");
        AllButtons homeButton = new AllButtons(size.MEDIUM, "Home");
        AllButtons bookingButton = new AllButtons(size.MEDIUM, "Bookings");
        AllButtons logoutButton = new AllButtons(size.MEDIUM, "Logout");
        AllButtons helpButton = new AllButtons(size.MEDIUM, "Help");
        JLabel logo = new JLabel(logga, JLabel.CENTER);
       
        JLabel ex1 = new JLabel("ex1");
        JLabel ex2 = new JLabel("ex2");
        JLabel ex3 = new JLabel("ex3");
        JLabel ex4 = new JLabel("ex4");

        AllButtons destinations = new AllButtons(size.LARGE, "Destinations");
        AllButtons customers = new AllButtons(size.LARGE, "Clients");
        AllButtons flights = new AllButtons(size.LARGE, "Flights");

        JLabel random = new JLabel("Whatever YOU WANT!");

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

        Border margin = new EmptyBorder(20, 20, 5, 10);
        sidePanel.setBorder(margin);
        sidePanel.add(inside);

        mainPanel.setLayout(new GridLayout(3,1));
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