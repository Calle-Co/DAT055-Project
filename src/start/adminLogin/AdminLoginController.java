package start.adminLogin;

import javax.swing.JButton;
import javax.swing.JTextField;
import global.AllButtons;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * En klass för att styra knapparna/event i för adminLogin-delen av programmet.
 * @author William Husar, Simon Länsberg, Carl Classon
 * @version 2021-02-24
 */
public class AdminLoginController {
    private AdminLoginModel model;
    private AdminLoginView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    /**
     * Skapar en instans av AdminLoginView och AdminLoginModel och instanserar en lista av alla knappar från view.
     * @param m En AdminLoginModel
     * @param v En AdminLoginView
     */
    public AdminLoginController(AdminLoginModel m, AdminLoginView v) {
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    /**
     * Denna metod lägger till en actionListener på alla knappar som hämtades från view.
     * @param al En actionListener
     */
    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }

    /**
     * Denna metod lägger till en keyListener på lösenordsrutan från view.
     * @param kl En keyListener
     */
    public void addKeyListener(KeyListener kl) {
        JTextField passwordField = view.getPasswordField();
        passwordField.addKeyListener(kl);
    }

    /**
     * Denna metod kallar på den metod i model som jämnför det inskrivna lösenordet mot 
     * det som ligger i databasen.
     * @param password Lösenordet som skrivit in när en admin försöker logga in.
     * @return Returnerar true om lösenordet är korrekt, annars false.
     */
    public boolean adminLogin(String password) {
        try{
            if(model.adminLogin(password)) {
                return true;
            } else {
                view.errorPanel();
                return false;
            }
        } catch (SQLException e) {    
            view.errorPanel();
            return false;
        } catch (ClassNotFoundException e) {
            //Måste tas om hand, men borde aldrig kunna inträffa.
            e.printStackTrace();
            return false;
        }
    }
}