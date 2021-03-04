package start.login;

import javax.swing.JButton;

import global.AllButtons;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextField;


/**
 * Denna klass sköter kommunikation mellan LoginModel och LoginView.
 * @author William Husar, Simon Länsberg, Carl Classon
 * @version 2021-02-24
 */
public class LoginController {
    private LoginModel model;
    private LoginView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public LoginController(LoginModel m, LoginView v){
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
     * Denna metod kallas på en metod i model som jämnför det inskriva användarnamnet
     * och lösenordet mot de som ligger i databasen.
     * @param username det inskrivna användarnamnet
     * @param password det inskrivna lösenordet
     * @return true om inloggningen lyckades, annars false.
     */
    public boolean userLogin(String username,String password){
        try{
            if(model.userLogin(username,password)){
                return true;
            } else {
                view.errorPanel();
                return false;
            }
        } catch (SQLException e){    
            view.errorPanel();
            return false;
        } catch (ClassNotFoundException e) {
            //Måste tas om hand, men borde aldrig kunna inträffa.
            e.printStackTrace();
            return false;
        }
    }
}
