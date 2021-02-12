package start;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;

import customer.AllButtons;
import customer.AllButtons.size;


public class StartPanel extends SuperPanel {
    public StartPanel() {
        setSize(600, 600);
        Color c = new Color(211,211,211);
        setBorder(new MatteBorder(200, 0, 0, 0,c));   
        setLayout(new BorderLayout());

        AllButtons bokaButton = new AllButtons(size.LARGE, "Boka!");
        JPanel bokaButtonPanel = new JPanel();
        bokaButtonPanel.setBackground(c);
        bokaButtonPanel.add(bokaButton);
        add(bokaButtonPanel,BorderLayout.CENTER);
   
        AllButtons adminButton = new AllButtons(size.MEDIUM, "Admin");
        JPanel adminButtonPanel = new JPanel();
        adminButtonPanel.setBackground(c);
        adminButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        adminButtonPanel.add(adminButton);
        add(adminButtonPanel,BorderLayout.SOUTH);

        setVisible(true);
        
    }
}
