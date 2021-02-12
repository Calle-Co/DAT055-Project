package start;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;

import customer.AllButtons;
import customer.AllButtons.size;


public class StartPanel extends JPanel {
    public StartPanel() {
        setSize(600, 600);
        setBackground(new Color(211,211,211));
        setLayout(new GridBagLayout());
        //JButton bokaButton = new JButton("Boka!");
        //bokaButton.setPreferredSize(new Dimension(80,40));
        AllButtons bokaButton = new AllButtons(size.LARGE, "Boka!");
        add(bokaButton,new GridBagConstraints());
        setVisible(true);
        
    }
}
