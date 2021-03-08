package start;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import global.*;

public class LoginPanel extends JPanel implements ActionListener{

    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");

    public LoginPanel(StartController controller) {


        setSize(600, 600);
        Color c = new Color(211,211,211);
        setLayout(new BorderLayout());

        JLabel logo = new JLabel(logga, JLabel.CENTER);
        JPanel logoPanel = new JPanel();
        logoPanel.add(logo);
        logoPanel.setBackground(c);
        add(logoPanel,BorderLayout.NORTH);

        JTextField username = new JTextField("Username");
        JTextField password = new JPasswordField("Password");

        username.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(username.getText().equalsIgnoreCase("Username")){
                    username.setText("");
                }
            }
        
            public void focusLost(FocusEvent e) {
                if(username.getText().isEmpty()){
                    username.setText("Username");
                }
            }
        });

        password.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                password.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                if(password.getText().isEmpty()){
                    password.setText("Password");
                }
            }
        });
        
        AllButtons loginButton = new AllButtons(AllButtons.size.MEDIUM, "Login!");
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               controller.bridge();
            }
        });
        

        AllButtons cancelButton = new AllButtons(AllButtons.size.SMALL, "cancel");
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               controller.updateView("StartPanel");
            }
        });
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 0, 10));
        centerPanel.setBorder(new MatteBorder(0, 200, 100, 200,c));
        centerPanel.setBackground(c);
        centerPanel.add(username);
        centerPanel.add(password);
        centerPanel.add(loginButton);
        centerPanel.add(cancelButton);
        add(centerPanel,BorderLayout.CENTER);
   
        AllButtons signUpButton = new AllButtons(AllButtons.size.MEDIUM, "Signup!");

        signUpButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               controller.updateView("SignupPanel");
            }
        });

        JPanel signupButtonPanel = new JPanel();
        signupButtonPanel.setBackground(c);
        signupButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        signupButtonPanel.add(signUpButton);
        add(signupButtonPanel,BorderLayout.SOUTH);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){}
}
