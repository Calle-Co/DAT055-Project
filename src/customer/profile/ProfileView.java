package customer.profile;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import customer.MenuPanel;
import global.AllButtons;

/**
 * Denna klass sköter grafiken för profil-delen av programmet.
 * @author Anna Manfredsson
 * @version 2021-03-05
 */
@SuppressWarnings("serial")
public class ProfileView extends JPanel {

    private ArrayList<AllButtons> buttons;
    private JPanel content;

    /**
     * Skapar Profil-panelen och all dess innehåll.
     */
    public ProfileView(){
        setLayout(null);
        buttons = new ArrayList<>();

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(0, 0, 1200, 100);
        for(AllButtons b : menuPanel.getButtons()) {
            buttons.add(b);
        }
        add(menuPanel);

        content = new JPanel();
        content.setLayout(null);
        content.setBounds(300, 120, 650, 450);
        content.setBackground(Color.white);
        add(content);

        AllButtons removeButton = new AllButtons(AllButtons.size.SMALL,"Delete my profile");
        removeButton.setBounds(225, 300, 200, 60);
        buttons.add(removeButton);
        content.add(removeButton);
        
        setVisible(true);

    }

    /**
     * Metod som lägger till text i panelen med användarens uppgifter.
     * @param username Användarnamnet på den användaren som är inloggad
     * @param password Lösenordet på den användaren som är inloggad
     */
    public void setUser(String username, String password){
        JLabel userLabel = new JLabel("Username: " + username);
        userLabel.setBounds(20, 20, 170, 25);
        userLabel.setFont(new Font("Verdana", 0, 20));
        content.add(userLabel);

        JLabel passLabel = new JLabel("Password: " + password);
        passLabel.setBounds(20, 80, 170, 25);
        passLabel.setFont(new Font("Verdana", 0, 20));
        content.add(passLabel);
    }

    /**
     * Denna metod har till uppgift att starta en popup ruta som frågar
     * användaren om man vill radera sin profil eller inte.
     * @return
     */
    public boolean deletePopup(){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        String s = "Are you sure you want to delete your profile?";
        int dialogResult = JOptionPane.showConfirmDialog (null, s,"Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            return true;
        } else {
            return false;
        } 
    }

    /**
     * @return En lista med alla knappar som ligger i home-view.
     */
    public ArrayList<AllButtons> getButtons() { return buttons; }
    
}
