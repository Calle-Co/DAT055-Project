package start.login;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import global.*;

/**
 * Klass som ansvarar för grafiken av login-delen av programmet.
 * @author Anna Manfredsson
 * @version 2021-03-03
 */
@SuppressWarnings("serial")
public class LoginView extends JPanel{
    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private JTextField username;
    private JTextField password;

    /**
     * Denna metod skapar panelen med all dess innehåll.
     */
    public LoginView( ) {
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
        
        AllButtons loginButton = new AllButtons(AllButtons.size.MEDIUM, "Login!");
        buttons.add(loginButton);
        

        AllButtons cancelButton = new AllButtons(AllButtons.size.SMALL, "cancel");
        buttons.add(cancelButton);
        
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
        buttons.add(signUpButton);

        JPanel signupButtonPanel = new JPanel();
        signupButtonPanel.setBackground(c);
        signupButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        signupButtonPanel.add(signUpButton);
        add(signupButtonPanel,BorderLayout.SOUTH);
        setVisible(true);

    }

    /**
     * Metod som visar en popup-panel om användare försöker logga in med fel
     * användarnamn eller lösenord.
     */
    public void errorPanel(){
        //Liten beep :)
        java.awt.Toolkit.getDefaultToolkit().beep();
        String s = "The username or password is incorrect!";
        JOptionPane.showMessageDialog(this, s, "Error!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @return Det lösenord som användaren skrivit in.
     */
    public String getPassword(){ return password.getText(); }

    /**
     * @return Det användarnamn som användaren skrivit in.
     */
    public String getUsername(){ return username.getText(); }

    /**
     * @return En arraylist med alla knappar som ligger i view.
     */
    public ArrayList<AllButtons> getButtons() { return buttons; }

    /**
     * @return Ett textfält för lösenordet
     */
    public JTextField getPasswordField() { return password; }
}