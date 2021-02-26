package admin.adminHome;

import javax.swing.JButton;

import global.AllButtons;

import java.awt.event.*;
import java.util.ArrayList;

public class AdminHomeController {
    
    private AdminHomeModel model;
    private AdminHomeView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public AdminHomeController(AdminHomeModel m, AdminHomeView v){
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