package start;

import javax.swing.JButton;

import global.AllButtons;

import java.awt.event.*;
import java.util.ArrayList;

public class AdminLoginController {

    private StartModel model;
    private AdminLoginView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public AdminLoginController(StartModel m, AdminLoginView v){
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
