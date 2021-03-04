package customer.booking;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * En klass vars instanser skapar en inforuta där man kan fylla i bokningsinformation för ett specifikt säte. 
 * @author Simon Länsberg, William Husar
 * @version 2021-02-22
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
    private JTextField name = new JTextField();
    private JTextField age = new JTextField();
    
    /**
     * En konstruktor som initsierar en InfoPanel tillhörade säte "nr".
     * @param nr Sätes numert.
     */
    public InfoPanel(String nr){
        Border blackline = BorderFactory.createLineBorder(Color.GRAY);
        this.setBorder(blackline);
        JPanel centerPanel = new JPanel();
        setPreferredSize(new Dimension(200,120));
        JLabel seatLabel = new JLabel("Seat " + nr, JLabel.CENTER);
        JLabel nameLabel = new JLabel("Name: " , JLabel.RIGHT);
        JLabel ageLabel = new JLabel("Age: ", JLabel.RIGHT);
        seatLabel.setBackground(Color.WHITE);
        centerPanel.setBackground(Color.WHITE);
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
        setBackground(Color.WHITE);
        setVisible(true);
    }

    /**
     * @return Returnerar namnet som fyllts i.
     */
    public String getName(){
        return name.getText();
    }

    /**
     * @return Returnerar åldern som fyllts i.
     */
    public String getAge(){
        return age.getText();
    }
}
