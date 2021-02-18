package customer.Bookings;

import javax.swing.*;

import java.awt.*;

public class infoPopup extends JPanel {
    
    public infoPopup(String nr){
        JPanel centerPanel = new JPanel();
        setPreferredSize(new Dimension(200,120));
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JLabel seatLabel = new JLabel("Seat " + nr, JLabel.CENTER);
        JLabel nameLabel = new JLabel("Name: " , JLabel.RIGHT);
        JLabel ageLabel = new JLabel("Age: ", JLabel.RIGHT);
        setLayout(new GridLayout(2, 1, 0, 0));
        //Cover your eyes and keep moving :)
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0; 
        c.gridx = 0;
        c.gridy = 0;
        centerPanel.add(nameLabel,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 10; 
        c.gridx = 1;
        c.gridy = 0;
        centerPanel.add(name,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0; 
        c.gridx = 0;
        c.gridy = 1;
        centerPanel.add(ageLabel,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 10; 
        c.gridx = 1;
        c.gridy = 1;
        centerPanel.add(age,c);
        add(seatLabel);
        add(centerPanel);
        setVisible(true);            
    }

    public static void main(String args[]){
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(200,120));
        frame.add(new infoPopup("1a"));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
