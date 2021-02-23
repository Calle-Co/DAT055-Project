package start;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.ArrayList;
import global.AllButtons;
import global.AllButtons.size;

/**
 * En klass för Välkomstpanelen.
 * @author Carl Classon
 * @version 2021-02-16
 */
@SuppressWarnings("serial")
public class WelcomeView extends JPanel{
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public WelcomeView() {
        setSize(600, 600);
        Color c = new Color(211,211,211);
        setLayout(new BorderLayout());     

        AllButtons bokaButton = new AllButtons(size.LARGE, "Boka!");
        buttons.add(bokaButton);
     
        JLabel logo = new JLabel(logga, JLabel.CENTER);
        JPanel logoPanel = new JPanel();
        logoPanel.add(logo);
        logoPanel.setBackground(c);
        add(logoPanel,BorderLayout.NORTH);

        JPanel bokaButtonPanel = new JPanel();
        bokaButtonPanel.setBackground(c);
        bokaButtonPanel.add(bokaButton);
        add(bokaButtonPanel,BorderLayout.CENTER);

        AllButtons adminButton = new AllButtons(size.MEDIUM, "Admin");
        buttons.add(adminButton);
        JPanel adminButtonPanel = new JPanel();
        adminButtonPanel.setBackground(c);
        adminButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        adminButtonPanel.add(adminButton);
        add(adminButtonPanel,BorderLayout.SOUTH);

        setVisible(true);
    }
   
    public ArrayList<AllButtons> getButtons() { return buttons; }
}