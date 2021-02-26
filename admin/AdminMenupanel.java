package admin;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import java.awt.*;
import java.text.*;

import global.AllButtons;
import global.AllButtons.size;
import global.WebFetching;


public class AdminMenuPanel extends JPanel{

    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");
    private JFormattedTextField dateField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));

    public AdminMenuPanel(){

        WebFetching todaysDate = new WebFetching();

        dateField.setText(todaysDate.getDat());
        AllButtons homeButton = new AllButtons(size.MEDIUM, "Home");
        buttons.add(homeButton);
        AllButtons logoutButton = new AllButtons(size.MEDIUM, "Logout");
        buttons.add(logoutButton);
        JLabel logo = new JLabel(logga, JLabel.CENTER);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        add(dateField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.3;
        c.ipadx = 10;
        c.gridx = 1;
        c.gridy = 0;
        add(homeButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        add(logoutButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0;
        c.gridx = 3;
        c.gridy = 0;
        add(logo, c);
        setVisible(true);
    }
    public ArrayList<AllButtons> getButtons(){ return buttons; }
}
