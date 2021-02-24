package start.login;

import javax.swing.JButton;

import global.AllButtons;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
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

    public boolean userLogin(String username,String password){
        try{
            if(model.userLogin(username,password)){
                return true;
            } else {
                view.errorPanel();
                return false;
            }
        } catch (SQLException e){    
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
