package start;

import javax.swing.JButton;

import global.AllButtons;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author William Husar, Simon Länsberg
 * @version 2021-02-24
 */
public class LoginController {
    private StartModel model;
    private LoginView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public LoginController(StartModel m, LoginView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}
