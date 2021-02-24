package start.welcome;

import javax.swing.JButton;

import global.AllButtons;
import start.StartModel;

import java.awt.event.*;
import java.util.ArrayList;
/**
 * @author William Husar, Simon Länsberg
 * @version 2021-02-18
 */
public class WelcomeController {
    private StartModel model;
    private WelcomeView view;
    private ArrayList<AllButtons> buttons;

    public WelcomeController(StartModel m, WelcomeView v){
        this.model = m;
        this.view = v;
        buttons = new ArrayList<>();
        buttons = view.getButtons();
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }
}