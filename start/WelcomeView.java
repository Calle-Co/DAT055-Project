package start;


import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.LinkedList;

import global.AllButtons;
import global.AllButtons.size;

@SuppressWarnings("serial")
public class WelcomeView extends JPanel{
    
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");
    private LinkedList<AllButtons> buttons = new LinkedList<AllButtons>();

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

    public LinkedList getButtons(){
        return buttons;
    }
}


  /*  bokaButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                controller.updateView("LoginPanel"); 
            } 
        });*/


  /*  adminButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               controller.updateView("AdminLoginPanel");
            }
        });*/