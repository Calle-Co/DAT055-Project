package start;

import javax.swing.JButton;

import global.AllButtons;

import java.awt.event.*;
import java.util.ArrayList;

public class WelcomeController {
    private StartModel model;
    private WelcomeView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public WelcomeController(StartModel m, WelcomeView v){
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