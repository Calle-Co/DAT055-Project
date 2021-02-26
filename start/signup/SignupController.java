package start.signup;

import javax.swing.JButton;
import javax.swing.JTextField;

import global.AllButtons;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * @author William Husar, Simon Länsberg, Carl Classon
 * @version 2021-02-24
 */
public class SignupController {
    private SignupModel model;
    private SignupView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public SignupController(SignupModel m, SignupView v){
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

    public boolean signUp(String username, String password){
        try{
            model.signUp(username,password); 
        } catch (SQLException e){    
            view.errorPanel();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        view.successPanel();
        return true;
    }
}
