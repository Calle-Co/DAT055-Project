package customer;

import javax.swing.JButton;

import global.AllButtons;

import java.awt.event.*;
import java.util.ArrayList;

public class HomeController {
    private CustomerModel model;
    private HomeView view;
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public HomeController(CustomerModel m, HomeView v){
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
