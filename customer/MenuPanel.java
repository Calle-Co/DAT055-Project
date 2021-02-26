package customer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import global.AllButtons;
import global.WebFetching;
import global.AllButtons.size;

public class MenuPanel extends JPanel {
    private ArrayList<AllButtons> buttons;
    private ImageIcon logga = new ImageIcon("global/resources/smalllogga.PNG");
    private final static int ONE_SECOND = 1000;
    private final SimpleDateFormat clockFormat = new SimpleDateFormat("H:mm:ss");
    private WebFetching todaysDat;

    public MenuPanel() {
        super();
        buttons = new ArrayList<>();
        todaysDat = new WebFetching();
        
        setLayout(null);
        // TOP PANEL
        // centerpanel = 100, 150, 1000, 500

        JPanel topPanel = new JPanel();
        topPanel.setBounds(200, 0, 800, 100);
        topPanel.setBackground(Color.white);
        topPanel.setLayout(null);
        add(topPanel);

        AllButtons homeButton = new AllButtons(size.BORDERLESSWHITE, "Home");
        homeButton.setBounds(50, 20, 160, 60);
        homeButton.setFont(new Font("", Font.BOLD, 25));
        topPanel.add(homeButton);

        AllButtons bkButton = new AllButtons(size.BORDERLESSWHITE, "Bookings");
        bkButton.setBounds(230, 20, 160, 60);
        bkButton.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(bkButton);

        AllButtons hButton = new AllButtons(size.BORDERLESSWHITE, "Help");
        hButton.setBounds(410, 20, 160, 60);
        hButton.setFont(new Font("Arial", Font.BOLD, 25));
        topPanel.add(hButton);

        AllButtons pButton = new AllButtons(size.BORDERLESSWHITE, "Profile");
        pButton.setBounds(590, 20, 160, 28);
        pButton.setFont(new Font("Arial", Font.BOLD, 15));
        topPanel.add(pButton);

        AllButtons logoutButton = new AllButtons(size.BORDERLESSWHITE, "Logout");
        buttons.add(logoutButton);
        logoutButton.setBounds(590, 52, 160, 28);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 15));
        topPanel.add(logoutButton);

        // TIME PANEL (TOPLEFT)
        // top pan = 200, 0, 800, 100

        JPanel timePanel = new JPanel();
        timePanel.setLayout(null);
        timePanel.setBounds(0, 0, 180, 100);
        timePanel.setBackground(Color.white);
        add(timePanel);

        JLabel clockLabel = new JLabel();
        clockLabel.setFont(new Font(clockLabel.getFont().getName(), Font.PLAIN, 25));
        timePanel.add(clockLabel);
        clockLabel.setBounds(40, 45, 120, 40);

        Timer timer = new Timer(ONE_SECOND, new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                clockLabel.setText(clockFormat.format(new Date()));
                clockLabel.repaint();
            }
        });
        clockLabel.setText(clockFormat.format(new Date()));
        timer.start();

        JLabel dateLabel = new JLabel();
        dateLabel.setText(todaysDat.getDat());
        dateLabel.setFont(new Font(dateLabel.getFont().getName(), Font.BOLD, 18));
        dateLabel.setBounds(37, 10, 120, 40);
        timePanel.add(dateLabel);
            
        // logo (TOPRIGHT)
        // top pan = 200, 0, 800, 100

        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(1000, 0, 180, 100);
        logoPanel.setBackground(Color.white);
            
        JLabel logo = new JLabel(logga);
        logo.setBounds(0, 5, 180, 100);
        logoPanel.add(logo);
        add(logoPanel);
        setVisible(true);
    }

    public ArrayList<AllButtons> getButtons() {
        return buttons;
    }

}
