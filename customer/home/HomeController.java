package customer.home;

import javax.swing.JButton;

import global.AllButtons;
import global.Destination;
import global.WebFetching;

import java.awt.event.*;
import java.util.ArrayList;

public class HomeController {
    private HomeModel model;
    private HomeView view;
    
    
    private ArrayList<AllButtons> buttons = new ArrayList<>();

    public HomeController(HomeModel m, HomeView v){
        this.model = m;
        this.view = v;
        buttons = view.getButtons();
    }

    public void getDestinations(){
        try {
            ArrayList<Destination> destinations = model.getDestinations();
            view.setDestinations(destinations);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void addButtonListener(ActionListener al) {
        for(AllButtons b : buttons) {
            b.addActionListener(al);
        }
    }
}
