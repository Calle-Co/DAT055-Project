package start.signup;

import javax.swing.JButton;
import javax.swing.JTextField;
import global.AllButtons;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Denna klass sköter kommunkationen mellan SignupModel och -view.
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

    /**
     * Denna metod kallar på signup metoden i model med parametrarna
     * username och password. Om metoden i model skulle misslyckas såhanterar denna 
     * metod även dessa exceptions. Den kallar även på lite popup fönster i view.
     * @param username användarnamnet på den nya användaren.
     * @param password lösenordet till använder.
     * @return
     */
    public boolean signUp(String username, String password){
        try{
            model.signUp(username,password); 
        } catch (SQLException e){    
            view.errorPanel();
            return false;
        } catch (ClassNotFoundException e) {
            //Måste tas om hand, men borde aldrig kunna inträffa.
            e.printStackTrace();
            return false;
        }
        view.successPanel();
        return true;
    }
}
