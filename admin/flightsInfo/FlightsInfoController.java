package admin.flightsInfo;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.*;

import global.AllButtons;

public class FlightsInfoController {
        private FlightsInfoModel model;
        private FlightsInfoView view;
        private ArrayList<AllButtons> buttons = new ArrayList<>();
        private boolean noFlight;
        
    public FlightsInfoController(FlightsInfoModel m, FlightsInfoView v){

        this.model = m;
        this.view = v;
        buttons = view.getButtons();
        
    }

    public void addButtonListener(ActionListener al) {
        for(JButton b : buttons) {
            b.addActionListener(al);
        }
    }

    public void getDestinations(){
        try {
            view.setDestinations(model.getDestinations());
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public boolean addFlight() {
        try{
            model.addFlight(view.getFrom(), view.getTo(), view.getDate(), view.getTime(), view.getModel()); 
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
