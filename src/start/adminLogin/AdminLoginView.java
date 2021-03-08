package start;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import global.*;

<<<<<<< Updated upstream:src/start/AdminLoginPanel.java
public class AdminLoginPanel extends JPanel implements ActionListener{

    private ImageIcon logga = new ImageIcon("global/Resources/logga.PNG");
=======
/**
 * Denna klass ansvarar för grafiken av admin-login delen av programmet.
 * @author Carl Classon @ Anna Manfredsson
 * @version 2021-03-03
 */
@SuppressWarnings("serial")
public class AdminLoginView extends JPanel {
    //private ImageIcon logga = new ImageIcon("src/global/resources/logga.PNG");
    private ImageIcon logga = new ImageIcon(getClass().getClassLoader().getResource("logga.PNG"));
    private ArrayList<AllButtons> buttons = new ArrayList<>();
    private JTextField password;
>>>>>>> Stashed changes:src/start/adminLogin/AdminLoginView.java

    public AdminLoginPanel(StartController controller) {


        setSize(600, 600);
        Color c = new Color(211,211,211);
        setLayout(new BorderLayout());

        JLabel logo = new JLabel(logga, JLabel.CENTER);
        JPanel logoPanel = new JPanel();
        logoPanel.add(logo);
        logoPanel.setBackground(c);
        add(logoPanel,BorderLayout.NORTH);

        JLabel username = new JLabel("Admin",JLabel.CENTER);
        username.setFont(new Font("Basic", Font.PLAIN,22));
        JTextField password = new JPasswordField("Password");
        password.setFont(new Font("Basic", Font.PLAIN,14));

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
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e){}
}
