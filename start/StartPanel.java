package start;


import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import global.AllButtons;
import global.AllButtons.size;



public class StartPanel extends JPanel implements ActionListener{
    
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");

    public StartPanel(StartController controller) {
        
        setSize(600, 600);
        Color c = new Color(211,211,211);
        setLayout(new BorderLayout());     

        AllButtons bokaButton = new AllButtons(size.LARGE, "Boka!");
        bokaButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                controller.updateView("LoginPanel"); 
            } 
        });

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
        JPanel adminButtonPanel = new JPanel();
        adminButtonPanel.setBackground(c);
        adminButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        adminButtonPanel.add(adminButton);
        adminButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               controller.updateView("AdminLoginPanel");
            }
        });
        add(adminButtonPanel,BorderLayout.SOUTH);

        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent e){}
}
