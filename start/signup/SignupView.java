package start.signup;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import global.*;

public class SignupView extends JPanel{
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private JTextField username;
    private JTextField password;

    public SignupView() {
        setSize(600, 600);
        Color c = new Color(211,211,211);
        setLayout(new BorderLayout());

        JLabel logo = new JLabel(logga, JLabel.CENTER);
        JPanel logoPanel = new JPanel();
        logoPanel.add(logo);
        logoPanel.setBackground(c);
        add(logoPanel,BorderLayout.NORTH);

        username = new JTextField("Username");
        password = new JPasswordField("Password");

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
        
        AllButtons signupButton = new AllButtons(AllButtons.size.MEDIUM, "Signup!");
        buttons.add(signupButton);
        AllButtons cancelButton = new AllButtons(AllButtons.size.SMALL, "cancel");
        buttons.add(cancelButton);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1, 0, 10));
        centerPanel.setBorder(new MatteBorder(0, 200, 100, 200, c));
        centerPanel.setBackground(c);
        centerPanel.add(username);
        centerPanel.add(password);
        centerPanel.add(signupButton);
        centerPanel.add(cancelButton);
        add(centerPanel,BorderLayout.CENTER);
        setVisible(true);
    }

    public void errorPanel(){
        //Liten beep :)
        java.awt.Toolkit.getDefaultToolkit().beep();
        String s = "That username already exist!\n" + "Try " + getUsername() + "1 instead!";
        JOptionPane.showMessageDialog(this, s, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    public void successPanel(){
        String s = "Signup succeeded!\nPress ok to return to login screen:";
        JOptionPane.showMessageDialog(this, s, "Success!", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getPassword(){
        return password.getText();
    }
    public String getUsername(){
        return username.getText();
    }

    public ArrayList<AllButtons> getButtons() { return buttons; }
    public JTextField getPasswordField() { return password; }

}