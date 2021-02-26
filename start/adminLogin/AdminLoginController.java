package start.adminLogin;

import javax.swing.JButton;
import javax.swing.JTextField;

import global.AllButtons;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * En klass för att styra knapparna/event i för adminLogin-delen av programmet.
 * @author William Husar & Simon Länsberg & Carl Classon
 * @version 2021-02-24
 */
public class AdminLoginController {

    private AdminLoginModel model;
    private AdminLoginView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public AdminLoginController(AdminLoginModel m, AdminLoginView v) {
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }

    public void addKeyListener(KeyListener kl) {
        JTextField passwordField = view.getPasswordField();
        passwordField.addKeyListener(kl);
    }

    /**
     * 
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
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
