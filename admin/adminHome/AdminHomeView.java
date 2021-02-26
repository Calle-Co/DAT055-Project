package admin.adminHome;

import java.util.ArrayList;

import global.AllButtons;
import global.AllButtons.size;

import java.awt.BorderLayout;
import java.awt.Color;
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

        JPanel topPanel = new JPanel();
        JPanel menuPanel = new JPanel();
        JPanel sidePanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel inside = new JPanel();

        JLabel date = new JLabel("--date and time--");
        AllButtons homeButton = new AllButtons(size.MEDIUM, "Home");
        AllButtons logoutButton = new AllButtons(size.MEDIUM, "Logout");
        buttons.add(logoutButton);
        JLabel logo = new JLabel(logga, JLabel.CENTER);
       
        JLabel ex1 = new JLabel("ex1");
        JLabel ex2 = new JLabel("ex2");
        JLabel ex3 = new JLabel("ex3");
        JLabel ex4 = new JLabel("ex4");

        AllButtons destinations = new AllButtons(size.LARGE, "Destinations");
        AllButtons customers = new AllButtons(size.LARGE, "Clients");
        AllButtons flights = new AllButtons(size.LARGE, "Flights");

        JLabel random = new JLabel("Whatever YOU WANT!");
        
        topPanel.add(date);
        topPanel.add(menuPanel);
        topPanel.add(logo);

        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(homeButton);
        menuPanel.add(logoutButton);

        inside.setLayout(new GridLayout(4,1));
        inside.add(ex1);
        inside.add(ex2);
        inside.add(ex3);
        inside.add(ex4);

        Border margin = new EmptyBorder(10, 50, 10, 50);
        sidePanel.setBorder(margin);
        sidePanel.setBackground(Color.WHITE);
        sidePanel.add(inside);

        mainPanel.setLayout(new GridLayout(5,1));
        mainPanel.setBorder(new EmptyBorder(20,50,0,50));
        mainPanel.add(destinations);
        mainPanel.add(customers);
        mainPanel.add(flights);


        lowerPanel.setLayout(new FlowLayout());
        lowerPanel.add(random);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
        setVisible(true); 
    }

    public ArrayList<AllButtons> getButtons() { return buttons; }
    
}