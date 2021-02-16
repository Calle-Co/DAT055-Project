package start;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import customer.*;

public class LoginPanel extends JPanel {
    public LoginPanel() {


        setSize(600, 600);
        Color c = new Color(211,211,211);
        setBorder(new MatteBorder(200, 0, 0, 0,c));   
        setLayout(new BorderLayout());

        JTextField username = new JTextField();
        JTextField password = new JTextField();
        AllButtons loginButton = new AllButtons(AllButtons.size.MEDIUM, "Login!");
        AllButtons cancelButton = new AllButtons(AllButtons.size.SMALL, "cancel");
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 0, 10));
        centerPanel.setBorder(new MatteBorder(0, 200, 100, 200,c));
        centerPanel.setBackground(c);
        centerPanel.add(username);
        centerPanel.add(password);
        centerPanel.add(loginButton);
        centerPanel.add(cancelButton);
        add(centerPanel,BorderLayout.CENTER);
   
        AllButtons signUpButton = new AllButtons(AllButtons.size.SMALL, "SignUp!");
        JPanel signupButtonPanel = new JPanel();
        signupButtonPanel.setBackground(c);
        signupButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        signupButtonPanel.add(signUpButton);
        add(signupButtonPanel,BorderLayout.SOUTH);
        
        setVisible(true);

       
        
    }
}
