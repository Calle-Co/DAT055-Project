package admin.adminHome;

import java.util.ArrayList;
import global.AllButtons;
import global.AllButtons.size;
import admin.AdminMenuPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Denna klass visar grafiken för admin-hemskärmen.
 * @author Anna Manfredsson
 * @version 2021-03-03
 */
@SuppressWarnings("serial")
public class AdminHomeView extends JPanel{
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    /**
     * Denna metod skapar AdminHomeView panelen och all dess innehåll. Knappar och dylikt.
     */
    public AdminHomeView(){

        JPanel sidePanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        AllButtons destinations = new AllButtons(size.LARGE, "Destinations");
        buttons.add(destinations);
        AllButtons customers = new AllButtons(size.LARGE, "Clients");
        buttons.add(customers);
        AllButtons flights = new AllButtons(size.LARGE, "Flights");
        buttons.add(flights);

        AdminMenuPanel menuPanel = new AdminMenuPanel();
        for (AllButtons b : menuPanel.getButtons()){
            buttons.add(b);
        }

        mainPanel.setLayout(new GridLayout(5,1));
        mainPanel.setBorder(new EmptyBorder(20,50,0,50));
        mainPanel.add(destinations);
        mainPanel.add(customers);
        mainPanel.add(flights);

        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
        setVisible(true); 
    }

    /**
     * @return en lista med alla knappar som ligger i vyn. 
     */
    public ArrayList<AllButtons> getButtons() { return buttons; }
    
}